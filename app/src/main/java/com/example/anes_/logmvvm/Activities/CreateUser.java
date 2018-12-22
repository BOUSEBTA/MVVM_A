package com.example.anes_.logmvvm.Activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.anes_.logmvvm.DatabaseHelper;
import com.example.anes_.logmvvm.R;
import com.example.anes_.logmvvm.commands.UserLogin;
import com.example.anes_.logmvvm.databinding.ActivityMainBinding;
import com.example.anes_.logmvvm.databinding.CreateUserBinding;
import com.example.anes_.logmvvm.model.User;
import com.example.anes_.logmvvm.viewmodel.UserModel;

public class CreateUser extends AppCompatActivity
{
    public DatabaseHelper dbh;

    private CreateUserBinding createUserBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        dbh =  new DatabaseHelper(this);
        createUserBinding = DataBindingUtil.setContentView(this, R.layout.create_user);
        User user = new User("","");
        UserModel usermodel = new UserModel("","");


        createUserBinding.setCreate(usermodel);
        createUserBinding.setCreateEvent(new UserLogin(){

            @Override
            public void onClickLogin() {
                //activityMainBinding.getLogin().login(activityMainBinding.email.getText().toString(),activityMainBinding.password.getText().toString());
                //activityMainBinding.getLogin().Log(dbh,activityMainBinding.email.getText().toString(),activityMainBinding.password.getText().toString());
            }

            @Override
            public void onClickCreate() {
                createUserBinding.getCreate().insertUser(dbh,createUserBinding.email1.getText().toString(),createUserBinding.password1.getText().toString());
            }


        });


    }
}
