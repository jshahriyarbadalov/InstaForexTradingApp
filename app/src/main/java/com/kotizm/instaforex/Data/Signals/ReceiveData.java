package com.kotizm.instaforex.Data.Signals;

import android.annotation.SuppressLint;
import android.util.Log;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.kotizm.instaforex.Model.Constans;
import com.kotizm.instaforex.Model.Message;
import com.kotizm.instaforex.Api.SignalsAPI;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint("Registered")
public class ReceiveData extends AppCompatActivity implements IReceiveData {

    private String token;
    private String pairs;
    private String login;

    private long startDate;
    private long endDate;
    private static final String TAG = "Error";

    public ReceiveData(String token, String pairs, String login, long startDate, long endDate) {
        this.token = token;
        this.pairs = pairs;
        this.login = login;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void getReceiveData(final IOnFinishedListener onFinishedListener) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("passkey", token).build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constans.BASE_URL )
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        SignalsAPI service = retrofit.create(SignalsAPI.class);
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("tradingsystem", "3");
        parameters.put("pairs", pairs);
        parameters.put("from", String.valueOf(startDate));
        parameters.put("to", String.valueOf(endDate));

        Call<List<Message>> call = service.getMessagesData(login, parameters);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(@NonNull Call<List<Message>> call, @NonNull Response<List<Message>> response) {
                if (response.body() != null) {
                    onFinishedListener.onFinished(response.body());
                } else Log.e(TAG, "Error loading data!");
            }

            @Override
            public void onFailure(@NonNull Call<List<Message>> call, @NonNull Throwable throwable) {
                onFinishedListener.onFailure(throwable);
            }
        });
    }
}