package com.kotizm.instaforex.Model.User;

public interface IUser {

    String getLogin();
    String getPassword();

    int validationLogin();
    int validationPassword();
}