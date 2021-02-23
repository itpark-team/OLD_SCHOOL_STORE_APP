package com.example.old_school_store_app.controllers.user;

import android.app.FragmentTransaction;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.Order;
import com.example.old_school_store_app.models.entities.OrderProduct;
import com.example.old_school_store_app.models.entities.User;
import com.example.old_school_store_app.views.main.MainActivity;
import com.example.old_school_store_app.views.user.UserOrdersFragment;

import java.util.ArrayList;

public class    ControllerUserOrderInfoFragment {
    private View view;
    private DbManager db;
    private User user;
    private RecyclerView recyclerViewOrderProductsList;
    private ArrayList<Order> orders;
    private ArrayList<OrderProduct> orderProducts;

    public ControllerUserOrderInfoFragment(View view)
    {
        this.view = view;

        Context context = (Context) DataStorage.Get("context");
        db = DbManager.GetInstance(context);

        orders = db.GetTableOrders().GetByUserId(user.getId());


        recyclerViewOrderProductsList = view.findViewById(R.id.recyclerViewOrderProductsList);
    }



    public void InitializeButtonsClick()
    {
        Button buttonGoBackToOrders = view.findViewById(R.id.buttonGoBackToOrders);
        buttonGoBackToOrders.setOnClickListener(OnButtonGoBackToOrdersClickListener);
    }

    public void InitializeViewProductOrderList(){

        Context context = (Context) DataStorage.Get("context");

        LinearLayoutManager llm = new LinearLayoutManager(context);
        recyclerViewOrderProductsList.setLayoutManager(llm);

        RvAdapterOrderProducts adapter = new RvAdapterOrderProducts(orders,db);
        recyclerViewOrderProductsList.setAdapter(adapter);
    }

    private View.OnClickListener OnButtonGoBackToOrdersClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view)
        {
            UserOrdersFragment userOrdersFragment = new UserOrdersFragment();

            MainActivity mainActivity = (MainActivity) DataStorage.Get("mainActivity");

            FragmentTransaction fragmentTransaction;
            fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentsContainerMain, userOrdersFragment);
            fragmentTransaction.commit();
        }
    };
}
