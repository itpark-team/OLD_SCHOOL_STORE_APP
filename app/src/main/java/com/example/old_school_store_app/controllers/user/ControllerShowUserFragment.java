package com.example.old_school_store_app.controllers.user;

import android.app.FragmentTransaction;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.User;
import com.example.old_school_store_app.views.main.MainActivity;
import com.example.old_school_store_app.views.user.InitialUserFragment;

import java.util.Calendar;
import java.util.Locale;

public class ControllerShowUserFragment
{
    private View view;
    private DbManager db;



    public ControllerShowUserFragment(View view)
    {
        this.view = view;
        Context context = (Context) DataStorage.Get("context");
        db = DbManager.GetInstance(context);
    }

    public void ShowUserFields()
    {
        TextView textViewShowUserLogin = view.findViewById(R.id.textViewShowUserLogin);
        TextView textViewShowUserName = view.findViewById(R.id.textViewShowUserName);
        TextView textViewShowUserBdate = view.findViewById(R.id.textViewShowUserBdate);
        TextView textViewShowUserPhone = view.findViewById(R.id.textViewShowUserPhone);
        TextView textViewShowUserEmail = view.findViewById(R.id.textViewShowUserEmail);

        User user = (User)DataStorage.Get("authorizedUser");


        textViewShowUserLogin.setText(user.getLogin());
        textViewShowUserName.setText(user.getName());

        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(user.getbDate() * 1000L);
        String bDate = DateFormat.format("dd-MM-yyyy", cal).toString();

        textViewShowUserBdate.setText(bDate);


        textViewShowUserPhone.setText(user.getPhone());
        textViewShowUserEmail.setText(user.getEmail());
    }

    public void InitializeButtonsClick()
    {
        Button buttonExitFromAcc = view.findViewById(R.id.buttonExitFromAcc);
        buttonExitFromAcc.setOnClickListener(OnButtonExitFromAccClickListener);

        Switch switchShowTutorial = view.findViewById(R.id.switchShowTutorial);
        switchShowTutorial.setOnCheckedChangeListener(OnSwitchShowTutorialChangeListener);
    }

    View.OnClickListener OnButtonExitFromAccClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            DataStorage.Delete("authorizedUser");
            db.GetTableSettingsApp().Delete("authorizedUser");

            InitialUserFragment initialUserFragment = new InitialUserFragment();

            MainActivity mainActivity = (MainActivity) DataStorage.Get("mainActivity");

            FragmentTransaction fragmentTransaction;
            fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentsContainerMain, initialUserFragment);
            fragmentTransaction.commit();
        }
    };


    CompoundButton.OnCheckedChangeListener OnSwitchShowTutorialChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
        {
            if (isChecked)
            {
                db.GetTableSettingsApp().Update("showTutorial", "1");
            }
            else
            {
                db.GetTableSettingsApp().Update("showTutorial", "0");
            }
        }
    };
}
