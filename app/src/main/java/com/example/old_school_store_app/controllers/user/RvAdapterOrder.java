package com.example.old_school_store_app.controllers.user;

import android.app.FragmentTransaction;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.Order;
import com.example.old_school_store_app.models.entities.OrderProduct;
import com.example.old_school_store_app.models.tables.TableProducts;
import com.example.old_school_store_app.views.catalog.CatalogItemsFragment;
import com.example.old_school_store_app.views.main.MainActivity;
import com.example.old_school_store_app.views.user.UserOrderInfoFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class RvAdapterOrder extends RecyclerView.Adapter<RvAdapterOrder.OrderViewHolder> {

    public static class OrderViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewOrderItemId;
        public TextView textViewOrderItemDt;
        public TextView textViewOrderItemTotalPrice;
        public Button buttonOrderItemGoToInfo;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewOrderItemId = itemView.findViewById(R.id.textViewOrderItemId);
            textViewOrderItemDt = itemView.findViewById(R.id.textViewOrderItemDt);
            textViewOrderItemTotalPrice = itemView.findViewById(R.id.textViewOrderItemTotalPrice);
            buttonOrderItemGoToInfo = itemView.findViewById(R.id.buttonOrderItemGoToInfo);
        }
    }

    private ArrayList<Order> orders;

    public RvAdapterOrder(ArrayList<Order> orders){
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_item_view, viewGroup, false);
        RvAdapterOrder.OrderViewHolder cvh = new RvAdapterOrder.OrderViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder orderViewHolder, int i) {
        Order order = orders.get(i);

        orderViewHolder.textViewOrderItemId.setText("ИД заказа: "+order.getId());

        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(order.getDt() * 1000L);
        String dt = DateFormat.format("dd-MM-yyyy HH:mm:ss", cal).toString();

        orderViewHolder.textViewOrderItemDt.setText("Дата и время заказа:"+dt);
        orderViewHolder.textViewOrderItemTotalPrice.setText("Стоимость заказа: "+order.getTotalPrice());

        orderViewHolder.buttonOrderItemGoToInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataStorage.Add("selectedOrderId",order.getId());

                UserOrderInfoFragment userOrderInfoFragment = new UserOrderInfoFragment();

                MainActivity mainActivity = (MainActivity) DataStorage.Get("mainActivity");

                FragmentTransaction fragmentTransaction;
                fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentsContainerMain, userOrderInfoFragment);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }




}
