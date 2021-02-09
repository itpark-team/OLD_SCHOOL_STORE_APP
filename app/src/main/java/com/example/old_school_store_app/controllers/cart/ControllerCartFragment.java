package com.example.old_school_store_app.controllers.cart;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.CartItem;
import com.example.old_school_store_app.models.entities.Product;
import com.example.old_school_store_app.models.entities.ProductPicture;
import com.example.old_school_store_app.models.entities.User;

import java.util.ArrayList;

public class ControllerCartFragment
{
    private View view;
    private DbManager db;
    private User user;

    public ControllerCartFragment(View view)
    {
        this.view = view;

        Context context = (Context) DataStorage.Get("context");
        db = DbManager.GetInstance(context);

        if(DataStorage.ExistKey("authorizedUser")==true)
        {
            user = (User)DataStorage.Get("authorizedUser");
        }
        else
        {
            user = null;
        }
    }

    public void InitializeButtonsClick()
    {
        Button buttonCartMakeOrder = view.findViewById(R.id.buttonCartMakeOrder);
        buttonCartMakeOrder.setOnClickListener(null);

        Button buttonCartResetOrder = view.findViewById(R.id.buttonCartResetOrder);
        buttonCartResetOrder.setOnClickListener(null);
    }

    public void FillUserFields()
    {
        TextView textViewCartUserName = view.findViewById(R.id.textViewCartUserName);
        TextView textViewCartUserPhone = view.findViewById(R.id.textViewCartUserPhone);

        if(user!=null)
        {
            textViewCartUserName.setText(user.getName());
            textViewCartUserPhone.setText(user.getPhone());
        }
        else
        {
            textViewCartUserName.setText("Пользователь не авторизован");
            textViewCartUserPhone.setText("Пользователь не авторизован");
        }
    }

    public void ShowCartProducts()
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

        RecyclerView recyclerViewCartList = view.findViewById(R.id.recyclerViewCartList);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        recyclerViewCartList.setLayoutManager(llm);

        RvAdapterCart adapter = new RvAdapterCart(userProducts, db);
        recyclerViewCartList.setAdapter(adapter);
    }


}
