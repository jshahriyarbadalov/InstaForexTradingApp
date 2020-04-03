package com.kotizm.instaforex.Data.Main;

public interface IReceiveData {

    interface IOnFinishedListener {
        void onFinished(String token);
        void onFailure(Throwable throwable);
    }
    void getReceiveData(IOnFinishedListener onFinishedListener);
}