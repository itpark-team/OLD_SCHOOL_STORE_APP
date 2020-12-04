package com.example.old_school_store_app.controllers.search;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.DbManager;

public class ControllerSearchFragment
{
    private View view;
    private DbManager db;

    public ControllerSearchFragment(View view)
    {
        this.view = view;
        Context context = (Context) DataStorage.Get("context");
        db = DbManager.GetInstance(context);
    }

    public void InitializeButtonsClick()
    {
        Button b = view.findViewById(R.id.buttonTestMethod);
        b.setOnClickListener(OnButtonTextClickListener);
    }

    private View.OnClickListener OnButtonTextClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            TextMethod();
        }
    };

    private void TextMethod()
    {
        TextView textView = view.findViewById(R.id.textView4);
        String text = db.GetTableProducts().GetAll().get(0).getName();
        textView.setText(text);
    }
}
