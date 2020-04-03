package com.kotizm.instaforex.Model.User;

import android.text.TextUtils;

public class User implements IUser {

    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int validationLogin() {
        return TextUtils.isEmpty(getLogin())? -1:1;
    }

    @Override
    public int validationPassword() {
        return TextUtils.isEmpty(getPassword())? -1:1;
    }
}