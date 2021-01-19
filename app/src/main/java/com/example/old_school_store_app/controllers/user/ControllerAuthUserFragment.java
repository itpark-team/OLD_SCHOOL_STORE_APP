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
import com.example.old_school_store_app.views.main.MainActivity;
import com.example.old_school_store_app.views.user.ShowUserFragment;

public class ControllerAuthUserFragment
{
    private View view;
    private DbManager db;

    private EditText editTextAuthUserLogin = view.findViewById(R.id.editTextAuthUserLogin);
    private EditText editTextAuthUserPassword = view.findViewById(R.id.editTextAuthUserPassword);

    public ControllerAuthUserFragment(View view)
    {
        this.view = view;
        Context context = (Context) DataStorage.Get("context");
        db = DbManager.GetInstance(context);
    }

    public void InitializeButtonsClick()
    {
        Button buttonAuthUserEnter = view.findViewById(R.id.buttonUserEnter);
        buttonAuthUserEnter.setOnClickListener(OnButtonAuthUserEnterClickListener);
    }

    View.OnClickListener OnButtonAuthUserEnterClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            String login = editTextAuthUserLogin.getText().toString();
            String password = editTextAuthUserPassword.getText().toString();

            Context context = (Context) DataStorage.Get("context");

            if(db.GetTableUsers().GetByLoginAndPassword(login, password)!=null)
            {
                DataStorage.Add("authorizedUser", editTextAuthUserLogin.getText().toString());

                ShowUserFragment showUserFragment = new ShowUserFragment();

                MainActivity mainActivity = (MainActivity) DataStorage.Get("mainActivity");

                FragmentTransaction fragmentTransaction;
                fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentsContainerMain, showUserFragment);
                fragmentTransaction.commit();
            }
            else
            {
                Toast.makeText(context,"Неверный Логин", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
