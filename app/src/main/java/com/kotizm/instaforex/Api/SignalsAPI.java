package com.kotizm.instaforex.Api;

import com.kotizm.instaforex.Model.Message;

import java.util.LinkedHashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface SignalsAPI {

    @GET("/clientmobile/GetAnalyticSignals/{login}")
    Call<List<Message>> getMessagesData(@Path("login") String login, @QueryMap LinkedHashMap<String, String> params);
}