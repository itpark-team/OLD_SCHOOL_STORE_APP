package com.example.old_school_store_app.controllers.cart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.CartItem;
import com.example.old_school_store_app.models.entities.Product;
import com.example.old_school_store_app.models.entities.ProductPicture;
import com.example.old_school_store_app.models.entities.User;
import com.example.old_school_store_app.models.tables.TableCart;
import com.example.old_school_store_app.models.tools.DbHelper;

import java.util.ArrayList;

public class ControllerCartFragment
{
    private View view;
    private DbManager db;
    private DbHelper dbHelper;
    private User user;
    private LinearLayout linearLayoutCart;
    private TextView textViewShowWarning;

    public ControllerCartFragment(View view)
    {
        this.view = view;

        Context context = (Context) DataStorage.Get("context");
        db = DbManager.GetInstance(context);

        linearLayoutCart = view.findViewById(R.id.linearLayoutCart);
        textViewShowWarning = view.findViewById(R.id.textViewShowWarning);

        dbHelper = new DbHelper(context);

        if(DataStorage.ExistKey("authorizedUser")==true)
        {
            user = (User)DataStorage.Get("authorizedUser");
        }
        else
        {
            user = null;
        }
    }

    private ArrayList<CartItem> GetUserProducts()
    {
        Context context = (Context) DataStorage.Get("context");

        ArrayList<CartItem> userProducts = db.GetTableCart().GetRecordsByUser(user.getId());

        for (int i = 0; i < userProducts.size(); i++)
        {
            CartItem userProduct = userProducts.get(i);

            Product product = db.GetTableProducts().GetById(userProduct.getProductId());

            ProductPicture picture = db.GetTableProductsPictures().GetMainProductPicture(userProduct.getProductId());

            int mainPicture = context.getResources().getIdentifier(picture.getPicturePath(),"drawable", context.getPackageName());

            product.setMainPictureId(mainPicture);

            userProduct.setProduct(product);
        }

        return userProducts;
    }

    public void LoadingCart()
    {
        if (user!=null)
        {
            linearLayoutCart.setVisibility(View.VISIBLE);
            textViewShowWarning.setVisibility(View.GONE);
            InitializeButtonsClick();
            FillUserFields();
            ShowCartProducts();
        }
        else
        {
            linearLayoutCart.setVisibility(View.GONE);
            textViewShowWarning.setVisibility(View.VISIBLE);
            ShowWarning();
        }
    }

    public int GetTotalOrderSum()
    {
        int sum = 0;

        ArrayList<CartItem> userProducts = GetUserProducts();

        for (int i = 0; i < userProducts.size(); i++)
        {
            CartItem currentProduct = userProducts.get(i);
            sum += currentProduct.getProduct().getPrice()*currentProduct.getCountProducts();
        }

        return sum;
    }

    public void UpdateTotalOrderSum()
    {
        int totalSum = GetTotalOrderSum();
        TextView textViewCartEmptyMessage = view.findViewById(R.id.textViewCartEmptyMessage);

        if(totalSum==0)
        {
            textViewCartEmptyMessage.setVisibility(View.VISIBLE);
        }
        else
        {
            textViewCartEmptyMessage.setVisibility(View.GONE);
        }

        TextView textViewTotalOrderPrice = view.findViewById(R.id.textViewTotalOrderPrice);
        textViewTotalOrderPrice.setText("?????????? ?????????? ??????????: "+Integer.toString(totalSum)+" ??????.");
    }

    public void InitializeButtonsClick()
    {
        Button buttonCartMakeOrder = view.findViewById(R.id.buttonCartMakeOrder);
        buttonCartMakeOrder.setOnClickListener(buttonCartMakeOrderOnClick);

        Button buttonCartResetOrder = view.findViewById(R.id.buttonCartResetOrder);
        buttonCartResetOrder.setOnClickListener(buttonCartResetOrderOnClick);
    }

    private View.OnClickListener buttonCartResetOrderOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            db.GetTableCart().ClearCartByUser(user.getId());
            UpdateTotalOrderSum();
            ShowCartProducts();
        }
    };

    private View.OnClickListener buttonCartMakeOrderOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            db.GetTableOrders().AddNew(user.getId(),(int)(System.currentTimeMillis()/1000), GetTotalOrderSum());

            int orderId = db.GetTableOrders().GetLastInsertId();

            ArrayList<CartItem> userProducts = GetUserProducts();

            db.GetTableOrdersProducts().AddNewOrder(orderId,userProducts);

            db.GetTableProducts().RecalculateCountProducts(userProducts);

            db.GetTableCart().ClearCartByUser(user.getId());
            UpdateTotalOrderSum();
            ShowCartProducts();

            Context context = (Context) DataStorage.Get("context");
            db = DbManager.GetInstance(context);

            Toast.makeText(context,"?????? ?????????? ?????????????? ????????????!", Toast.LENGTH_LONG).show();
        }
    };


    public void FillUserFields()
    {
        TextView textViewCartUserName = view.findViewById(R.id.textViewCartUserName);
        TextView textViewCartUserPhone = view.findViewById(R.id.textViewCartUserPhone);
        TextView textViewTotalOrderPrice = view.findViewById(R.id.textViewTotalOrderPrice);

        if(user!=null)
        {
            textViewCartUserName.setText(user.getName());
            textViewCartUserPhone.setText(user.getPhone());
            UpdateTotalOrderSum();
        }
        else
        {
            textViewCartUserName.setText("???????????????????????? ???? ??????????????????????");
            textViewCartUserPhone.setText("???????????????????????? ???? ??????????????????????");
            textViewTotalOrderPrice.setText("???????????????????????? ???? ??????????????????????");
        }
    }

    public void ShowCartProducts()
    {
        ArrayList<CartItem> userProducts = GetUserProducts();
        Context context = (Context) DataStorage.Get("context");

        RecyclerView recyclerViewCartList = view.findViewById(R.id.recyclerViewCartList);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        recyclerViewCartList.setLayoutManager(llm);

        RvAdapterCart adapter = new RvAdapterCart(userProducts, db, this);
        recyclerViewCartList.setAdapter(adapter);
    }

    public void ShowWarning()
    {
        TextView textViewShowWarning = view.findViewById(R.id.textViewShowWarning);
        textViewShowWarning.setText("???????????????????? ?????????????????????????????????? ?????? ?????????????????????????? ?????? ???????????????????? ??????????????");
    }
}
