package com.example.old_school_store_app.controllers.catalog;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.CartItem;
import com.example.old_school_store_app.models.entities.Product;
import com.example.old_school_store_app.models.entities.ProductPicture;
import com.example.old_school_store_app.models.entities.User;
import com.example.old_school_store_app.views.catalog.CatalogItemsFragment;
import com.example.old_school_store_app.views.main.MainActivity;
import com.example.old_school_store_app.views.search.SearchFragment;

import java.util.ArrayList;

public class ControllerCatalogProductInfoFragment
{
    private View view;
    private DbManager db;
    private ViewFlipper viewFlipperImages;
    private float fromPosition, toPosition;
    private Product product;
    private int counter;
    private User user;

    public ControllerCatalogProductInfoFragment(View view)
    {
        this.view = view;
        Context context = (Context) DataStorage.Get("context");
        db = DbManager.GetInstance(context);

        int productId = (int)DataStorage.Get("productId");
        DataStorage.Delete("productId");

        product = db.GetTableProducts().GetById(productId);

        if(DataStorage.ExistKey("authorizedUser")==true)
        {
            user = (User)DataStorage.Get("authorizedUser");
        }
        else
        {
            user = null;
        }
    }

    public void InitializeViewFlipper()
    {
        viewFlipperImages = view.findViewById(R.id.viewFlipperCatalogImages);
        viewFlipperImages.setOnTouchListener(viewFlipperImagesOnTouch);
    }

    public void InitializeButtonsClick()
    {
        Button buttonGoBackToCatalogItems = view.findViewById(R.id.buttonGoBackToCatalogItems);
        buttonGoBackToCatalogItems.setOnClickListener(buttonGoBackToCatalogItemsOnClick);

        Button buttonAddToCart = view.findViewById(R.id.buttonCatalogAddToCart);
        buttonAddToCart.setOnClickListener(buttonAddToCartOnClick);
    }

    private View.OnClickListener buttonGoBackToCatalogItemsOnClick = new View.OnClickListener()  {
        @Override
        public void onClick(View view)
        {
            GoBackToCatalogItemsFragment();
        }
    };

    private void GoBackToCatalogItemsFragment()
    {
        CatalogItemsFragment catalogItemsFragment = new CatalogItemsFragment();

        MainActivity mainActivity = (MainActivity) DataStorage.Get("mainActivity");

        FragmentTransaction fragmentTransaction;
        fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentsContainerMain, catalogItemsFragment);
        fragmentTransaction.commit();
    }

    private View.OnClickListener buttonAddToCartOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(user!=null)
            {
                AddProductToCart();
            }
            else
            {
                Context context = (Context) DataStorage.Get("context");

                Toast.makeText(context,"????????????. ???????????????????? ?????????????????????????????????? ?????? ??????????????????????????", Toast.LENGTH_LONG).show();
            }
        }
    };

    private void AddProductToCart()
    {
        counter=1;

        MainActivity mainActivity = (MainActivity) DataStorage.Get("mainActivity");

        AlertDialog.Builder dialogWindow  = new AlertDialog.Builder(mainActivity);
        dialogWindow.setTitle(product.getName());

        LayoutInflater inflater = mainActivity.getLayoutInflater();
        View alertDialogView = inflater.inflate(R.layout.alert_dialog_add_to_cart_choose, null);
        dialogWindow.setView(alertDialogView);

        Button buttonAlertDialogMinus = alertDialogView.findViewById(R.id.buttonAlertDialogMinus);
        Button buttonAlertDialogPlus = alertDialogView.findViewById(R.id.buttonAlertDialogPlus);

        TextView textViewAlertDialogCountProduct = alertDialogView.findViewById(R.id.textViewAlertDialogCountProduct);
        textViewAlertDialogCountProduct.setText(Integer.toString(counter));

        buttonAlertDialogMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter>1) {counter--;}
                textViewAlertDialogCountProduct.setText(Integer.toString(counter));
            }
        });

        buttonAlertDialogPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter<product.getCountLeft()) {counter++;}
                textViewAlertDialogCountProduct.setText(Integer.toString(counter));
            }
        });

        dialogWindow.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                CartItem cartItem = new CartItem(user.getId(),product.getId(),counter);
                db.GetTableCart().AddToCart(cartItem);

                Context context = (Context) DataStorage.Get("context");

                Toast.makeText(context,"?????????????? ?????????????? ????????????????", Toast.LENGTH_LONG).show();
            }
        });

        dialogWindow.setNegativeButton("????????????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        dialogWindow.setCancelable(true);
        dialogWindow.create().show();
    }

    public void FillInfoFields()
    {
        ArrayList<ProductPicture> productPictures = db.GetTableProductsPictures().GetProductPictures(product.getId());

        Context context = (Context) DataStorage.Get("context");

        for (int i = 0; i < productPictures.size(); i++)
        {
            ProductPicture productPicture = productPictures.get(i);

            int pictureId = context.getResources().getIdentifier(productPicture.getPicturePath(),"drawable", context.getPackageName());

            productPicture.setPictureId(pictureId);
        }

        TextView textViewProductName = view.findViewById(R.id.textViewCatalogProductName);
        TextView textViewProductPrice = view.findViewById(R.id.textViewCatalogProductPrice);
        TextView textViewProductCountPurchases = view.findViewById(R.id.textViewCatalogProductCountPurchases);
        TextView textViewProductCountLeft = view.findViewById(R.id.textViewCatalogProductCountLeft);
        TextView textViewProductCategoryName = view.findViewById(R.id.textViewCatalogProductCategoryName);
        TextView textViewProductDescription = view.findViewById(R.id.textViewCatalogProductDescription);
        
        textViewProductName.setText(product.getName());
        textViewProductPrice.setText("????????: "+product.getPrice()+" ??????.");
        textViewProductCountPurchases.setText("?????????? ??????????????: "+product.getCountPurchases()+" ????.");
        textViewProductCountLeft.setText("???????????????? ???? ????????????: "+product.getCountLeft()+" ????.");
        String categoryName = db.GetTableCategories().GetById(product.getCategoryId()).getName();
        textViewProductCategoryName.setText("??????????????????: "+categoryName);
        textViewProductDescription.setText(product.getDescription());

        for (int i = 0; i < productPictures.size(); i++)
        {
            ImageView image = new ImageView(context);
            image.setBackgroundResource(productPictures.get(i).getPictureId());
            viewFlipperImages.addView(image);
        }
    }

    private View.OnTouchListener viewFlipperImagesOnTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent)
        {
            switch (motionEvent.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    fromPosition = motionEvent.getX();
                    break;

                case MotionEvent.ACTION_UP:
                    toPosition = motionEvent.getX();

                    Context context = (Context) DataStorage.Get("context");

                    if(toPosition<fromPosition)
                    {
                        viewFlipperImages.setInAnimation(AnimationUtils.loadAnimation(context,R.anim.go_next_in));
                        viewFlipperImages.setOutAnimation(AnimationUtils.loadAnimation(context,R.anim.go_next_out));

                        viewFlipperImages.showNext();
                    }
                    else if (toPosition>fromPosition)
                    {
                        viewFlipperImages.setInAnimation(AnimationUtils.loadAnimation(context,R.anim.go_prev_in));
                        viewFlipperImages.setOutAnimation(AnimationUtils.loadAnimation(context,R.anim.go_prev_out));

                        viewFlipperImages.showPrevious();
                    }
                    break;
            }
            return true;
        }
    };
}
