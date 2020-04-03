package com.kotizm.instaforex.Data.Main;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.kotizm.instaforex.Model.Constans.BASE_URL;

class RetrofitInstance {

    private static Retrofit retrofit;

    static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}