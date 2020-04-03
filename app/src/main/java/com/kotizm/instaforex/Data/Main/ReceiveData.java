package com.kotizm.instaforex.Data.Main;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.kotizm.instaforex.Api.PasskeyAPI;

import java.io.IOException;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceiveData implements IReceiveData {

    private Context context;
    private String login;
    private String password;

    public ReceiveData(Context context, String login, String password) {
        this.context = context;
        this.login = login;
        this.password = password;
    }

    @Override
    public void getReceiveData(final IOnFinishedListener onFinishedListener) {

        PasskeyAPI api = RetrofitInstance.getInstance().create(PasskeyAPI.class);
        RegistrationBody registrationBody = new RegistrationBody(login, password);

        Call<ResponseBody> call = api.registerUser(registrationBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                if (response.body() != null) {
                    try {
                        String token = Objects.requireNonNull(response.body()).string();
                        onFinishedListener.onFinished(token);
                    } catch (IOException exception) {
                        onFinishedListener.onFailure(exception);
                    }
                } else {
                    Toast.makeText(context, "User with such data does not exist!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable) {
                onFinishedListener.onFailure(throwable);
            }
        });
    }
}