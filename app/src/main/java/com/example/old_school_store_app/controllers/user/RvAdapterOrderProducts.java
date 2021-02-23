package com.example.old_school_store_app.controllers.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.Order;
import com.example.old_school_store_app.models.entities.OrderProduct;

import java.util.ArrayList;

public class RvAdapterOrderProducts extends RecyclerView.Adapter<RvAdapterOrderProducts.OrderViewHolder> {

    private DbManager db;
    private ArrayList<Order> order;
    private ArrayList<OrderProduct> orderProducts;



    public RvAdapterOrderProducts(ArrayList<Order> order, DbManager db) {
        this.order = order;
        this.db = db;
        orderIndex = 0;
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_user_orders, viewGroup, false);
        RvAdapterOrderProducts.OrderViewHolder cvh = new RvAdapterOrderProducts.OrderViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(OrderViewHolder orderViewHolder, int i) {
        Order currentOrder = order.get(i);
        orderProducts = db.GetTableOrdersProducts().getByOrderId(currentOrder.getId());
        OrderProduct currentProduct = orderProducts.get(i);


        orderViewHolder.orderProductPicture.setImageResource(db.GetTableProducts().GetById(currentProduct.getOrderProductId()).getMainPictureId());
        orderViewHolder.orderProductCount.setText("Количество:"+currentProduct.getCountProduct());
        orderViewHolder.orderProductName.setText(db.GetTableProducts().GetById(currentProduct.getOrderProductId()).getName());
        orderViewHolder.orderProductPrice.setText("Цена:"+db.GetTableProducts().GetById(currentProduct.getOrderProductId()).getPrice());
        orderViewHolder.orderProductTotalPrice.setText("Итого:"+(currentProduct.getCountProduct()*db.GetTableProducts().GetById(currentProduct.getOrderProductId()).getPrice()));
    }

    @Override
    public int getItemCount() {
        return order.size();
    }
}
