package com.example.anes_.logmvvm.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anes_.logmvvm.DatabaseHelper;
import com.example.anes_.logmvvm.R;
import com.example.anes_.logmvvm.commands.UserLogin;

import com.example.anes_.logmvvm.databinding.ActivityMainBinding;
import com.example.anes_.logmvvm.model.User;
import com.example.anes_.logmvvm.viewmodel.UserModel;


public class MainActivity extends AppCompatActivity {
    public DatabaseHelper dbh;

    private ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        dbh =  new DatabaseHelper(this);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User user = new User("","");
        UserModel usermodel = new UserModel("","");


        activityMainBinding.setLogin(usermodel);
        activityMainBinding.setLoginEvent(new UserLogin(){

            @Override
            public void onClickLogin() {
                    //activityMainBinding.getLogin().login(activityMainBinding.email.getText().toString(),activityMainBinding.password.getText().toString());
                //activityMainBinding.getLogin().Log(dbh,activityMainBinding.email.getText().toString(),activityMainBinding.password.getText().toString());
            }

            @Override
            public void onClickCreate() {

            }


        });



    }


}
