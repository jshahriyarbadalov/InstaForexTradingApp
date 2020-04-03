package com.kotizm.instaforex.View;

public interface IMainView {

    void onResponseSuccess(String token);
    void onResponseFailure(Throwable throwable);
}