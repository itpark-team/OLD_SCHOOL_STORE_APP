package com.example.old_school_store_app.controllers.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.CartItem;

import java.util.ArrayList;

public class RvAdapterCart extends RecyclerView.Adapter<RvAdapterCart.CartViewHolder>
{
    public static class CartViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView productCartPicture;
        public TextView productCartName;
        public TextView productCartPrice;
        public TextView productCartCount;
        public TextView productCartTotalPrice;
        public Button buttonCartMinus;
        public Button buttonCartPlus;
        public Button buttonDeleteCartProduct;

        CartViewHolder(View itemView)
        {
            super(itemView);

            productCartPicture = itemView.findViewById(R.id.productCartPicture);
            productCartName = itemView.findViewById(R.id.productCartName);
            productCartPrice = itemView.findViewById(R.id.productCartPrice);
            productCartCount = itemView.findViewById(R.id.productCartCount);
            productCartTotalPrice = itemView.findViewById(R.id.productCartTotalPrice);

            buttonCartMinus = itemView.findViewById(R.id.buttonCartMinus);
            buttonCartPlus = itemView.findViewById(R.id.buttonCartPlus);
            buttonDeleteCartProduct = itemView.findViewById(R.id.buttonDeleteCartProduct);
        }
    }

    private ArrayList<CartItem> userProducts;
    private int totalPrice;
    private DbManager db;

    public RvAdapterCart (ArrayList<CartItem> userProducts, DbManager db)
    {
        this.userProducts = userProducts;
        this.totalPrice = 0;
        this.db = db;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RvAdapterCart.CartViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item_view, viewGroup, false);
        RvAdapterCart.CartViewHolder cvh = new RvAdapterCart.CartViewHolder(v);
        return cvh;
    }

    @Override
    public int getItemCount() {
        return userProducts.size();
    }

    @Override
    public void onBindViewHolder(RvAdapterCart.CartViewHolder cartViewHolder, int i)
    {
        CartItem currentProduct = userProducts.get(i);

        cartViewHolder.productCartPicture.setImageResource(currentProduct.getProduct().getMainPictureId());

        cartViewHolder.productCartName.setText(currentProduct.getProduct().getName());
        cartViewHolder.productCartPrice.setText(Integer.toString(currentProduct.getProduct().getPrice())+" руб.");

        cartViewHolder.productCartCount.setText(Integer.toString(currentProduct.getCountProducts()));

        totalPrice = currentProduct.getCountProducts() * currentProduct.getProduct().getPrice();

        cartViewHolder.productCartTotalPrice.setText("Итого: "+Integer.toString(totalPrice)+" руб.");

        cartViewHolder.buttonCartMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentProduct.MinusCountProducts();

                cartViewHolder.productCartCount.setText(Integer.toString(currentProduct.getCountProducts()));

                totalPrice = currentProduct.getCountProducts() * currentProduct.getProduct().getPrice();

                cartViewHolder.productCartTotalPrice.setText("Итого: "+Integer.toString(totalPrice)+" руб.");
            }
        });

        cartViewHolder.buttonCartPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentProduct.PlusCountProducts();

                cartViewHolder.productCartCount.setText(Integer.toString(currentProduct.getCountProducts()));

                totalPrice = currentProduct.getCountProducts() * currentProduct.getProduct().getPrice();

                cartViewHolder.productCartTotalPrice.setText("Итого: "+Integer.toString(totalPrice)+" руб.");
            }
        });

        cartViewHolder.buttonDeleteCartProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1 удалить из списка  private ArrayList<CartItem> userProducts; продукт с нужным ИД
                //2 удалить из db продукт который по ИДпродукта и по ИДюзера совпадает

                //3 написать процедуру удаления в таблице TableCart

                userProducts.remove(0);

                notifyItemRemoved(0);
                notifyItemRangeChanged(0, userProducts.size());
            }
        });



    }
}
