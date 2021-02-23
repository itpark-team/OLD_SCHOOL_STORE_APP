package com.example.old_school_store_app.controllers.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.Order;
import com.example.old_school_store_app.models.entities.OrderProduct;
import com.example.old_school_store_app.models.entities.Product;
import com.example.old_school_store_app.models.entities.ProductPicture;

import java.util.ArrayList;

public class RvAdapterOrderProducts extends RecyclerView.Adapter<RvAdapterOrderProducts.OrderViewHolder> {

    private DbManager db;
    private ArrayList<OrderProduct> orderProducts;

    public RvAdapterOrderProducts(ArrayList<OrderProduct> orderProducts, DbManager db) {
        this.orderProducts = orderProducts;
        this.db = db;

    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder{
        public ImageView orderProductPicture;
        public TextView orderProductName;
        public TextView orderProductPrice;
        public TextView orderProductCount;
        public TextView orderProductTotalPrice;

        public OrderViewHolder(View itemView) {
            super(itemView);

            orderProductPicture = itemView.findViewById(R.id.orderProductPicture);
            orderProductName = itemView.findViewById(R.id.orderProductName);
            orderProductPrice = itemView.findViewById(R.id.orderProductPrice);
            orderProductCount = itemView.findViewById(R.id.orderProductCount);
            orderProductTotalPrice = itemView.findViewById(R.id.orderProductTotalPrice);
        }
    }



    public RvAdapterOrderProducts.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_producs_view, viewGroup, false);
        RvAdapterOrderProducts.OrderViewHolder cvh = new RvAdapterOrderProducts.OrderViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(OrderViewHolder orderViewHolder, int i) {

        Product currentProduct = db.GetTableProducts().GetById(orderProducts.get(i).getOrderProductId());

        Context context = (Context) DataStorage.Get("context");

        ProductPicture picture = db.GetTableProductsPictures().GetMainProductPicture(currentProduct.getId());

        int mainPicture = context.getResources().getIdentifier(picture.getPicturePath(),"drawable", context.getPackageName());

        currentProduct.setMainPictureId(mainPicture);

        orderViewHolder.orderProductPicture.setImageResource(currentProduct.getMainPictureId());

        orderViewHolder.orderProductCount.setText("Количество:"+orderProducts.get(i).getCountProduct());
        orderViewHolder.orderProductName.setText(currentProduct.getName());

        orderViewHolder.orderProductPrice.setText("Цена:"+currentProduct.getPrice());

        orderViewHolder.orderProductTotalPrice.setText("Итого:"+currentProduct.getPrice()*orderProducts.get(i).getCountProduct());
    }

    @Override
    public int getItemCount() {
        return orderProducts.size();
    }
}
