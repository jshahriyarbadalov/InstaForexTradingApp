package com.kotizm.instaforex.Presenter.Login;

import com.kotizm.instaforex.Model.User.User;
import com.kotizm.instaforex.View.ILoginView;

public class LoginPresenter implements ILoginPresenter {

    private ILoginView iLoginView;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
    }

    @Override
    public void onLogin(String login, String password) {

        User user = new User(login, password);

        int isLoginSuccess = user.validationLogin();
        int isPasswordSuccess = user.validationPassword();

        if (isLoginSuccess == -1)
            iLoginView.onLoginError("You must enter your login!");
        else if (isPasswordSuccess == -1)
            iLoginView.onLoginError("You must enter your password!");
        else
            iLoginView.onLoginSuccess("Good job!", login, password);
    }
}