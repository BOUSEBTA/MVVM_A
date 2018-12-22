package com.example.anes_.logmvvm.model;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;

public class User {
    @NonNull
    public String email ,password ;

    public User() {
    }

    public User(@NonNull String email, @NonNull String password) {
        this.email = email;
        this.password = password;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public boolean isValidData(){
        return !TextUtils.isEmpty(getEmail()) &&
                Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches() &&
                getPassword().length() > 6;
    }
}
