package com.kotizm.instaforex.Presenter.Signals;

import com.kotizm.instaforex.Data.Signals.IReceiveData;
import com.kotizm.instaforex.Model.Message;
import com.kotizm.instaforex.View.ISignalsView;

import java.util.List;

public class SignalsPresenter implements ISignalsPresenter, IReceiveData.IOnFinishedListener {

    private ISignalsView iSignalsView;
    private IReceiveData getNoticeIntractor;

    public SignalsPresenter(ISignalsView iSignalsView, IReceiveData getNoticeIntractor) {
        this.iSignalsView = iSignalsView;
        this.getNoticeIntractor = getNoticeIntractor;
    }

    @Override
    public void onDestroy() {
        iSignalsView = null;
    }

    @Override
    public void requestData() {
        if(iSignalsView != null){
            iSignalsView.showProgress();
        }
        getNoticeIntractor.getReceiveData(this);
    }

    @Override
    public void onFinished(List<Message> listSignals) {
        if(iSignalsView != null){
            iSignalsView.onResponseSuccess(listSignals);
            iSignalsView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        if(iSignalsView != null){
            iSignalsView.onResponseFailure(throwable);
            iSignalsView.hideProgress();
        }
    }
}