package com.example.old_school_store_app.controllers.user;

import android.app.FragmentTransaction;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.User;
import com.example.old_school_store_app.views.main.MainActivity;
import com.example.old_school_store_app.views.user.InitialUserFragment;
import com.example.old_school_store_app.views.user.ShowUserFragment;

public class  ControllerAuthUserFragment
{
    private View view;
    private DbManager db;

    public ControllerAuthUserFragment(View view)
    {
        this.view = view;
        Context context = (Context) DataStorage.Get("context");
        db = DbManager.GetInstance(context);
    }

    public void InitializeButtonsClick()
    {
        Button buttonAuthUserEnter = view.findViewById(R.id.buttonAuthUserEnter);
        buttonAuthUserEnter.setOnClickListener(OnButtonAuthUserEnterClickListener);

        Button buttonAuthUserBack = view.findViewById(R.id.buttonAuthUserBack);
        buttonAuthUserBack.setOnClickListener(OnButtonAuthUserBackClickListener);
    }

    private View.OnClickListener OnButtonAuthUserEnterClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            AuthorizeUser();
        }
    };

    private void AuthorizeUser()
    {
        EditText editTextAuthUserLogin = view.findViewById(R.id.editTextAuthUserLogin);
        EditText editTextAuthUserPassword = view.findViewById(R.id.editTextAuthUserPassword);

        String login = editTextAuthUserLogin.getText().toString();
        String password = editTextAuthUserPassword.getText().toString();

        Context context = (Context) DataStorage.Get("context");

        User user = db.GetTableUsers().GetByLoginAndPassword(login, password);

        if(user != null)
        {
            DataStorage.Add("authorizedUser", user);
            db.GetTableSettingsApp().Add("authorizedUser", Integer.toString(user.getId()));

            ShowUserFragment showUserFragment = new ShowUserFragment();

            MainActivity mainActivity = (MainActivity) DataStorage.Get("mainActivity");

            FragmentTransaction fragmentTransaction;
            fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentsContainerMain, showUserFragment);
            fragmentTransaction.commit();
        }
        else
        {
            Toast.makeText(context,"Неверный Логин или Пароль", Toast.LENGTH_SHORT).show();
        }
    }

    private View.OnClickListener OnButtonAuthUserBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            GoToBack();
        }
    };

    private void GoToBack()
    {
        InitialUserFragment initialUserFragment = new InitialUserFragment();

        MainActivity mainActivity = (MainActivity) DataStorage.Get("mainActivity");

        FragmentTransaction fragmentTransaction;
        fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentsContainerMain, initialUserFragment);
        fragmentTransaction.commit();
    }


}
