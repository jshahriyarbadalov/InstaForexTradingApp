package com.kotizm.instaforex.Data.Signals;

import com.kotizm.instaforex.Model.Message;

import java.util.List;

public interface IReceiveData {

    interface IOnFinishedListener {
        void onFinished(List<Message> listSignals);
        void onFailure(Throwable throwable);
    }
    void getReceiveData(IOnFinishedListener onFinishedListener);
}