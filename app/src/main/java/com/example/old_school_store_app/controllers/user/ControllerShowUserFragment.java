package com.example.old_school_store_app.controllers.user;

import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.User;
import com.example.old_school_store_app.views.main.MainActivity;
import com.example.old_school_store_app.views.user.InitialUserFragment;

public class ControllerShowUserFragment
{
    private View view;
    private DbManager db;

    private TextView textViewShowUserLogin = view.findViewById(R.id.textViewShowUserLogin);
    private TextView textViewShowUserName = view.findViewById(R.id.textViewShowUserName);
    private TextView textViewShowUserBdate = view.findViewById(R.id.textViewShowUserBdate);
    private TextView textViewShowUserPhone = view.findViewById(R.id.textViewShowUserPhone);
    private TextView textViewShowUserEmail = view.findViewById(R.id.textViewShowUserEmail);

    public ControllerShowUserFragment(View view)
    {
        this.view = view;
        Context context = (Context) DataStorage.Get("context");
        db = DbManager.GetInstance(context);
        User user = db.GetTableUsers().GetById(0);
        textViewShowUserLogin.setText(user.getLogin());
        textViewShowUserName.setText(user.getName());
        textViewShowUserBdate.setText(user.getbDate());
        textViewShowUserPhone.setText(user.getPhone());
        textViewShowUserEmail.setText(user.getEmail());
    }

    public void InitializeButtonsClick()
    {
        Button buttonExitFromAcc = view.findViewById(R.id.buttonExitFromAcc);
        buttonExitFromAcc.setOnClickListener(OnButtonExitFromAccClickListener);
    }

    View.OnClickListener OnButtonExitFromAccClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            DataStorage.Delete("authorizedUser");

            InitialUserFragment initialUserFragment = new InitialUserFragment();

            MainActivity mainActivity = (MainActivity) DataStorage.Get("mainActivity");

            FragmentTransaction fragmentTransaction;
            fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentsContainerMain, initialUserFragment);
            fragmentTransaction.commit();
        }
    };
}
