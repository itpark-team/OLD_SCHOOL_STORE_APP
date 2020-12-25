package com.example.old_school_store_app.controllers.search;

import android.app.FragmentTransaction;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.Product;
import com.example.old_school_store_app.models.entities.ProductPicture;
import com.example.old_school_store_app.views.main.MainActivity;
import com.example.old_school_store_app.views.search.SearchFragment;
import com.example.old_school_store_app.views.search.SearchProductInfoFragment;

import java.util.ArrayList;

public class ControllerSearchProductInfoFragment
{
    private View view;
    private DbManager db;
    private ViewFlipper viewFlipperImages;
    private float fromPosition, toPosition;

    public ControllerSearchProductInfoFragment(View view)
    {
        this.view = view;
        Context context = (Context) DataStorage.Get("context");
        db = DbManager.GetInstance(context);

        viewFlipperImages = view.findViewById(R.id.viewFlipperImages);
        viewFlipperImages.setOnTouchListener(viewFlipperImagesOnTouch);
    }

    public void InitializeButtonsClick()
    {
        Button buttonGoBackToSearch = view.findViewById(R.id.buttonGoBackToSearch);
        buttonGoBackToSearch.setOnClickListener(buttonGoBackToSearchOnClick);

        Button buttonAddToCart = view.findViewById(R.id.buttonAddToCart);
        buttonAddToCart.setOnClickListener(null);
    }

    private View.OnClickListener buttonGoBackToSearchOnClick = new View.OnClickListener()  {
        @Override
        public void onClick(View view)
        {
            GoBackToSearchFragment();
        }
    };

    private void GoBackToSearchFragment()
    {
        SearchFragment searchFragment = new SearchFragment();

        MainActivity mainActivity = (MainActivity) DataStorage.Get("mainActivity");

        FragmentTransaction fragmentTransaction;
        fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentsContainerMain, searchFragment);
        fragmentTransaction.commit();
    }

    public void FillInfoFields()
    {
        int productId = (int)DataStorage.Get("productId");
        DataStorage.Delete("productId");

        Product product = db.GetTableProducts().GetById(productId);

        ArrayList<ProductPicture> productPictures = db.GetTableProductsPictures().GetProductPictures(productId);

        Context context = (Context) DataStorage.Get("context");

        for (int i = 0; i < productPictures.size(); i++)
        {
            ProductPicture productPicture = productPictures.get(i);

            int pictureId = context.getResources().getIdentifier(productPicture.getPicturePath(),"drawable", context.getPackageName());

            productPicture.setPictureId(pictureId);
        }

        TextView textViewProductName = view.findViewById(R.id.textViewProductName);
        TextView textViewProductPrice = view.findViewById(R.id.textViewProductPrice);
        TextView textViewProductCountPurchases = view.findViewById(R.id.textViewProductCountPurchases);
        TextView textViewProductCountLeft = view.findViewById(R.id.textViewProductCountLeft);
        TextView textViewProductCategoryName = view.findViewById(R.id.textViewProductCategoryName);
        TextView textViewProductDescription = view.findViewById(R.id.textViewProductDescription);
        
        textViewProductName.setText(product.getName());
        textViewProductPrice.setText("Цена: "+product.getPrice()+" руб.");
        textViewProductCountPurchases.setText("Всего куплено: "+product.getCountPurchases()+" шт.");
        textViewProductCountLeft.setText("Осталось на складе: "+product.getCountLeft()+" шт.");
        String categoryName = db.GetTableCategories().GetById(product.getCategoryId()).getName();
        textViewProductCategoryName.setText("Категория: "+categoryName);
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
