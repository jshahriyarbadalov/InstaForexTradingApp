package com.kotizm.instaforex.View;

public interface ILoginView {

    void onLoginSuccess(String message, String login, String password);
    void onLoginError(String message);
}