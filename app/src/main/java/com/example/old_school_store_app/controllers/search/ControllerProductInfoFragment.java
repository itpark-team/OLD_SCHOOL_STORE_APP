package com.example.old_school_store_app.controllers.search;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.Product;
import com.example.old_school_store_app.models.entities.ProductPicture;

import java.util.ArrayList;

public class ControllerProductInfoFragment
{
    private View view;
    private DbManager db;


    public ControllerProductInfoFragment(View view)
    {
        this.view = view;
        Context context = (Context) DataStorage.Get("context");
        db = DbManager.GetInstance(context);
    }

    public void InitializeButtonsClick()
    {
        Button buttonGoBackToSearch = view.findViewById(R.id.buttonGoBackToSearch);
        buttonGoBackToSearch.setOnClickListener(null);

        Button buttonAddToCart = view.findViewById(R.id.buttonAddToCart);
        buttonAddToCart.setOnClickListener(null);
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

        ViewFlipper viewFlipperImages = view.findViewById(R.id.viewFlipperImages);;

        for (int i = 0; i < productPictures.size(); i++)
        {
            ImageView image = new ImageView(context);
            image.setBackgroundResource(productPictures.get(i).getPictureId());
            viewFlipperImages.addView(image);
        }
    }
}
