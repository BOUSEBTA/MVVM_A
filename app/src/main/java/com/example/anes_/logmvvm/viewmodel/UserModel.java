package com.example.anes_.logmvvm.viewmodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.BaseObservable;
import android.util.Log;

import com.example.anes_.logmvvm.DatabaseHelper;
import com.example.anes_.logmvvm.R;

public class UserModel extends BaseObservable  {
    String email;
    String password;
    Context context;
    //DatabaseHelper dbh = new DatabaseHelper(context);
    //User user = new User("email", "password") ;

    public UserModel(String email,String password) {
        this.email = email;
        this.password = password;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(R.id.email);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(R.id.password);
    }

    /*
    public void login(String email , String password){
        Cursor cr = dbh.Login(email,password);
        if(cr.getCount() == 0){
            Log.d("============", "nothing found");
        }
    }*/



    public Cursor Log(DatabaseHelper dbh,String email , String pass)
    {
        SQLiteDatabase db = dbh.getWritableDatabase();
        Cursor Cr = db.rawQuery("Select * from users where username = ? and password = ? " , new String[] { email , pass});
        if(Cr.getCount() == 0){
            Log.d("============", "nothing found");
        }

        return Cr ;
    }

    public boolean insertUser(DatabaseHelper dbh,String user , String pass){
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",pass);
        long res = db.insert("users",null,contentValues);
        if (res == -1)
            return false;
        else
            Log.d("RESULTAT", "insertUser: INSERTEEEEEEEEED");
            return true;
    }



}


