package com.example.old_school_store_app.controllers.user;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import android.app.FragmentTransaction;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.Order;
import com.example.old_school_store_app.models.entities.User;
import com.example.old_school_store_app.models.tools.DbHelper;
import com.example.old_school_store_app.views.main.MainActivity;
import com.example.old_school_store_app.views.user.ShowUserFragment;

import java.util.ArrayList;

public class ControllerUserOrderFragment {
    private View view;
    private DbManager db;
    private DbHelper dbHelper;
    private ArrayList<Order> orders;
    private TextView textViewOrderEmptyMessage;
    private RecyclerView recyclerViewOrderList;
    private User user;

    public ControllerUserOrderFragment(View view)
    {
        this.view = view;
        Context context = (Context) DataStorage.Get("context");
        db = DbManager.GetInstance(context);
        dbHelper = new DbHelper(context);

        textViewOrderEmptyMessage = view.findViewById(R.id.textViewOrderEmptyMessage);

        recyclerViewOrderList = view.findViewById(R.id.recyclerViewOrderList);

        user = (User)DataStorage.Get("authorizedUser");
    }

    private void InitializeViewOrderList(){

        Context context = (Context) DataStorage.Get("context");

        LinearLayoutManager llm = new LinearLayoutManager(context);
        recyclerViewOrderList.setLayoutManager(llm);

        RvAdapterOrder adapter = new RvAdapterOrder(orders);
        recyclerViewOrderList.setAdapter(adapter);
    }

    public void ShowOrdersView(){

        orders = db.GetTableOrders().GetByUserId(user.getId());

        if (orders.size() == 0)
        {
            textViewOrderEmptyMessage.setVisibility(View.VISIBLE);
            recyclerViewOrderList.setVisibility(View.GONE);
        }
        else
        {

            textViewOrderEmptyMessage.setVisibility(View.GONE);
            recyclerViewOrderList.setVisibility(View.VISIBLE);

            InitializeViewOrderList();
        }
    }


    public void InitializeButtonsClick()
    {
        Button buttonGoToShowUser = view.findViewById(R.id.buttonGoToShowUser);
        buttonGoToShowUser.setOnClickListener(OnButtonGoToShowUser);
    }

    View.OnClickListener OnButtonGoToShowUser = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            ShowUserFragment showUserFragment = new ShowUserFragment();

            MainActivity mainActivity = (MainActivity) DataStorage.Get("mainActivity");

            FragmentTransaction fragmentTransaction;
            fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentsContainerMain, showUserFragment);
            fragmentTransaction.commit();
        }
    };
}
