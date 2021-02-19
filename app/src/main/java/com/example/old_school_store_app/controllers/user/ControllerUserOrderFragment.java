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
import com.example.old_school_store_app.models.entities.OrderProduct;
import com.example.old_school_store_app.models.tables.TableOrdersProducts;
import com.example.old_school_store_app.models.tables.TableProducts;
import com.example.old_school_store_app.models.tools.DbHelper;
import com.example.old_school_store_app.views.main.MainActivity;
import com.example.old_school_store_app.views.user.RvAdapterOrder;
import com.example.old_school_store_app.views.user.ShowUserFragment;

import java.util.ArrayList;

public class ControllerUserOrderFragment {
    private View view;
    private DbManager db;
    private DbHelper dbHelper;
    private ArrayList<OrderProduct> orderProducts;
    private TextView textViewOrderEmptyMessage;
    private RecyclerView recyclerViewOrderList;

    public ControllerUserOrderFragment(View view)
    {
        this.view = view;
        Context context = (Context) DataStorage.Get("context");
        db = DbManager.GetInstance(context);
        dbHelper = new DbHelper(context);
        orderProducts = db.GetTableOrdersProducts().getAll();
    }

    private void InitializeViewOrderList(){

        textViewOrderEmptyMessage = view.findViewById(R.id.textViewOrderEmptyMessage);

        Context context = (Context) DataStorage.Get("context");

        recyclerViewOrderList = view.findViewById(R.id.recyclerViewOrderList);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        recyclerViewOrderList.setLayoutManager(llm);

        RvAdapterOrder adapter = new RvAdapterOrder(orderProducts, db);
        recyclerViewOrderList.setAdapter(adapter);
    }

    public void ShowOrdersView(){
        InitializeViewOrderList();
        if (orderProducts.size() == 0){
            textViewOrderEmptyMessage.setVisibility(View.VISIBLE);
            recyclerViewOrderList.setVisibility(View.GONE);
        }
        else {
            textViewOrderEmptyMessage.setVisibility(View.GONE);
            recyclerViewOrderList.setVisibility(View.VISIBLE);
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
