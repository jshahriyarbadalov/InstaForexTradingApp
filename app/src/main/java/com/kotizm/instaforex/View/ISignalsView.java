package com.kotizm.instaforex.View;

import com.kotizm.instaforex.Model.Message;

import java.util.List;

public interface ISignalsView {

    void onResponseSuccess(List<Message> listSignals);
    void onResponseFailure(Throwable throwable);

    void showProgress();
    void hideProgress();
}