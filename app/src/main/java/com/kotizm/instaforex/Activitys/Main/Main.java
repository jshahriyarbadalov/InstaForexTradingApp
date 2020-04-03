package com.kotizm.instaforex.Activitys.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kotizm.instaforex.Data.Main.ReceiveData;
import com.kotizm.instaforex.Presenter.Main.IMainPresenter;
import com.kotizm.instaforex.Presenter.Main.MainPresenter;
import com.kotizm.instaforex.R;
import com.kotizm.instaforex.Presenter.Login.ILoginPresenter;
import com.kotizm.instaforex.Presenter.Login.LoginPresenter;
import com.kotizm.instaforex.Activitys.Signals.Signals;
import com.kotizm.instaforex.View.ILoginView;
import com.kotizm.instaforex.View.IMainView;

public class Main extends AppCompatActivity implements ILoginView, IMainView {

    private EditText editLogin;
    private EditText editPassword;

    private IMainPresenter iMainPresenter;
    private ILoginPresenter iLoginPresenter;

    private String login;
    private static final String TOKEN = "token";
    private static final String LOGIN = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iLoginPresenter = new LoginPresenter(this);

        editLogin = findViewById(R.id.edit_login);
        editPassword = findViewById(R.id.edit_password);

        Button bottonLogin = findViewById(R.id.button_login);
        bottonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iLoginPresenter.onLogin(editLogin.getText().toString(), editPassword.getText().toString());
            }
        });
    }

    @Override
    public void onLoginSuccess(String message, String login, String password) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        this.login = login;
        iMainPresenter = new MainPresenter(this, new ReceiveData(this, login, password));
        iMainPresenter.requestData();
    }

    @Override
    public void onLoginError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponseSuccess(String token) {
        Intent intent = new Intent(this, Signals.class);
        intent.putExtra(TOKEN, token);
        intent.putExtra(LOGIN, login);
        startActivity(intent);
        finish();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(this, throwable.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iMainPresenter != null) iMainPresenter.onDestroy();
    }
}