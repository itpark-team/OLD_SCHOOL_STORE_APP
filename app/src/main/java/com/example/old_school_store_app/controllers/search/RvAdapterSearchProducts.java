package com.example.old_school_store_app.controllers.search;

import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.entities.Product;
import com.example.old_school_store_app.views.main.MainActivity;
import com.example.old_school_store_app.views.search.SearchProductInfoFragment;

import java.util.ArrayList;

public class RvAdapterSearchProducts extends RecyclerView.Adapter<RvAdapterSearchProducts.ProductViewHolder>
{
    public static class ProductViewHolder extends RecyclerView.ViewHolder
    {
        public CardView cvSearch;
        public TextView productName;
        public TextView productPrice;
        public ImageView productPicture;
        public Button buttonGoToProductInfo;

        ProductViewHolder(View itemView) {
            super(itemView);
            cvSearch = itemView.findViewById(R.id.cvCatalog);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productPicture = itemView.findViewById(R.id.productPicture);
            buttonGoToProductInfo = itemView.findViewById(R.id.buttonGoToProductInfo);
        }
    }

    private ArrayList<Product> products;

    public RvAdapterSearchProducts(ArrayList<Product> products)
    {
        this.products = products;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item_view, viewGroup, false);
        ProductViewHolder pvh = new ProductViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder productViewHolder, int i)
    {
        productViewHolder.productName.setText(products.get(i).getName());
        productViewHolder.productPrice.setText(Integer.toString(products.get(i).getPrice())+" ??????.");
        productViewHolder.productPicture.setImageResource(products.get(i).getMainPictureId());

        productViewHolder.buttonGoToProductInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                DataStorage.Add("productId",products.get(i).getId());

                SearchProductInfoFragment searchProductInfoFragment = new SearchProductInfoFragment();

                MainActivity mainActivity = (MainActivity) DataStorage.Get("mainActivity");

                FragmentTransaction fragmentTransaction;
                fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentsContainerMain, searchProductInfoFragment);
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
