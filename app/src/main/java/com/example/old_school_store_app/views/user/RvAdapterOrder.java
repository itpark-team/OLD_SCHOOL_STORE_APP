package com.example.old_school_store_app.views.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.controllers.cart.RvAdapterCart;
import com.example.old_school_store_app.controllers.user.ControllerUserOrderFragment;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.CartItem;
import com.example.old_school_store_app.models.entities.Order;
import com.example.old_school_store_app.models.entities.OrderProduct;
import com.example.old_school_store_app.models.tables.TableProducts;

import java.util.ArrayList;

public class RvAdapterOrder extends RecyclerView.Adapter<RvAdapterOrder.OrderViewHolder> {

    public static class OrderViewHolder extends RecyclerView.ViewHolder{
        public ImageView productOrderPicture;
        public TextView productOrderName;
        public TextView productOrderPrice;
        public TextView productOrderCount;
        public TextView productOrderTotalPrice;


        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);


            productOrderPicture = itemView.findViewById(R.id.productOrderPicture);
            productOrderName = itemView.findViewById(R.id.productOrderName);
            productOrderPrice = itemView.findViewById(R.id.productOrderPrice);
            productOrderTotalPrice = itemView.findViewById(R.id.productOrderTotalPrice);
            productOrderCount = itemView.findViewById(R.id.productOrderCount);
        }
    }

    private ArrayList<OrderProduct> orderProducts;
    private DbManager db;
    private TableProducts tableProducts;

    public RvAdapterOrder(ArrayList<OrderProduct> orderProducts, DbManager db){
        this.orderProducts = orderProducts;
        this.db = db;
        tableProducts = db.GetTableProducts();
    }


    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_user_orders, viewGroup, false);
        RvAdapterOrder.OrderViewHolder cvh = new RvAdapterOrder.OrderViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder orderViewHolder, int i) {
        OrderProduct currentProduct = orderProducts.get(i);

        orderViewHolder.productOrderPicture.setImageResource(tableProducts.GetById(currentProduct.getOrderProductId()).getMainPictureId());
        orderViewHolder.productOrderCount.setText("Количество:"+currentProduct.getCountProduct());
        orderViewHolder.productOrderName.setText(tableProducts.GetById(currentProduct.getOrderProductId()).getName());
        orderViewHolder.productOrderPrice.setText("Цена:"+tableProducts.GetById(currentProduct.getOrderProductId()).getPrice());
        orderViewHolder.productOrderTotalPrice.setText("Итого:"+(currentProduct.getCountProduct()*tableProducts.GetById(currentProduct.getOrderProductId()).getPrice()));
    }

    @Override
    public int getItemCount() {
        return orderProducts.size();
    }




}
