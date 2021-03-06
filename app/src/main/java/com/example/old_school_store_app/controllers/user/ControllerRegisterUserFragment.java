package com.example.old_school_store_app.controllers.user;

import android.app.FragmentTransaction;
import android.content.Context;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.User;
import com.example.old_school_store_app.views.main.MainActivity;
import com.example.old_school_store_app.views.user.AuthUserFragment;
import com.example.old_school_store_app.views.user.InitialUserFragment;
import com.example.old_school_store_app.views.user.ShowUserFragment;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControllerRegisterUserFragment
{
    private View view;
    private DbManager db;

    public ControllerRegisterUserFragment(View view)
    {
        this.view = view;
        Context context = (Context) DataStorage.Get("context");
        db = DbManager.GetInstance(context);
    }

    public void InitializeButtonsClick()
    {
        Button buttonRegisterUserRegister = view.findViewById(R.id.buttonRegisterUserRegister);
        buttonRegisterUserRegister.setOnClickListener(OnButtonRegisterUserRegisterClickListener);

        Button buttonRegisterUserBack = view.findViewById(R.id.buttonRegisterUserBack);
        buttonRegisterUserBack.setOnClickListener(OnButtonRegisterUserBackClickListener);
    }

    private View.OnClickListener OnButtonRegisterUserRegisterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RegisterUser();
        }
    };

    private void RegisterUser()
    {
        EditText editTextRegisterUserLogin = view.findViewById(R.id.editTextRegisterUserLogin);
        EditText editTextRegisterUserPassword = view.findViewById(R.id.editTextRegisterUserPassword);
        EditText editTextRegisterUserName = view.findViewById(R.id.editTextRegisterUserName);
        EditText editTextRegisterUserBdate = view.findViewById(R.id.editTextRegisterUserBdate);
        EditText editTextRegisterUserPhone = view.findViewById(R.id.editTextRegisterUserPhone);
        EditText editTextRegisterUserEmail = view.findViewById(R.id.editTextRegisterUserEmail);

        String login = editTextRegisterUserLogin.getText().toString();
        String password = editTextRegisterUserPassword.getText().toString();
        String name = editTextRegisterUserName.getText().toString();
        String bDate = editTextRegisterUserBdate.getText().toString();
        String phone = editTextRegisterUserPhone.getText().toString();
        String email = editTextRegisterUserEmail.getText().toString();

        Context context = (Context) DataStorage.Get("context");

        if(login.length()==0)
        {
            Toast.makeText(context,"?????????????????? ???????? ??????????", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length()==0)
        {
            Toast.makeText(context,"?????????????????? ???????? ????????????", Toast.LENGTH_SHORT).show();
            return;
        }
        if(name.length()==0)
        {
            Toast.makeText(context,"?????????????????? ???????? ??????", Toast.LENGTH_SHORT).show();
            return;
        }
        if(bDate.length()==0)
        {
            Toast.makeText(context,"?????????????????? ???????? ???????? ????????????????", Toast.LENGTH_SHORT).show();
            return;
        }
        if(phone.length()==0)
        {
            Toast.makeText(context,"?????????????????? ???????? ??????????????", Toast.LENGTH_SHORT).show();
            return;
        }
        if(email.length()==0)
        {
            Toast.makeText(context,"?????????????????? ???????? E-mail", Toast.LENGTH_SHORT).show();
            return;
        }

        if(db.GetTableUsers().ExistUserByLogin(login)==true)
        {
            Toast.makeText(context,"???????????????????????? ?? ?????????? ?????????? ?????? ????????????????????", Toast.LENGTH_SHORT).show();
            return;
        }

        int timestampBdate = 0;

        bDate+=" 12:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try
        {
            Date date = dateFormat.parse(bDate);
            timestampBdate = (int)(date.getTime()/1000);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        User userToDb = new User(0, login, password, name, timestampBdate, phone, email);

        db.GetTableUsers().InsertNewUser(userToDb);

        User userFromDb = db.GetTableUsers().GetByLoginAndPassword(login, password);

        db.GetTableSettingsApp().Add("authorizedUser",Integer.toString(userFromDb.getId()));

        DataStorage.Add("authorizedUser",userFromDb);

        MainActivity mainActivity = (MainActivity) DataStorage.Get("mainActivity");

        ShowUserFragment showUserFragment = new ShowUserFragment();

        FragmentTransaction fragmentTransaction;
        fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentsContainerMain, showUserFragment);
        fragmentTransaction.commit();
    }

    private View.OnClickListener OnButtonRegisterUserBackClickListener = new View.OnClickListener() {
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
