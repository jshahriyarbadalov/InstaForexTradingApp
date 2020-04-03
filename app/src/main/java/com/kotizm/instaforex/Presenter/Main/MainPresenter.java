package com.kotizm.instaforex.Presenter.Main;

import com.kotizm.instaforex.Data.Main.IReceiveData;
import com.kotizm.instaforex.View.IMainView;

public class MainPresenter implements IMainPresenter, IReceiveData.IOnFinishedListener {

    private IMainView iMainActivityView;
    private IReceiveData getNoticeIntractor;

    public MainPresenter(IMainView iMainActivityView, IReceiveData getNoticeIntractor) {
        this.iMainActivityView = iMainActivityView;
        this.getNoticeIntractor = getNoticeIntractor;
    }

    @Override
    public void onDestroy() {
        iMainActivityView = null;
    }

    @Override
    public void requestData() {
        getNoticeIntractor.getReceiveData(this);
    }

    @Override
    public void onFinished(String token) {
        if(iMainActivityView != null){
            iMainActivityView.onResponseSuccess(token);
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        if(iMainActivityView != null){
            iMainActivityView.onResponseFailure(throwable);
        }
    }
}