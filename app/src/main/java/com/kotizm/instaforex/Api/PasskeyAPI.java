package com.kotizm.instaforex.Api;

import com.kotizm.instaforex.Data.Main.RegistrationBody;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PasskeyAPI {

    @POST("/api/Authentication/RequestMoblieCabinetApiToken/")
    Call<ResponseBody> registerUser(@Body RegistrationBody body);
}