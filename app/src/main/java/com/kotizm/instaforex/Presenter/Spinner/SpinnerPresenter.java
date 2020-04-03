package com.kotizm.instaforex.Presenter.Spinner;

import com.kotizm.instaforex.Data.Spinner.ISpinnerReceiveData;
import com.kotizm.instaforex.View.ISpinnerView;

public class SpinnerPresenter implements ISpinnerPresenter, ISpinnerReceiveData.ISpinnerOnFinishedListener {

    private ISpinnerView iSpinnerView;
    private ISpinnerReceiveData getNoticeIntractor;

    public SpinnerPresenter(ISpinnerView iSpinnerView, ISpinnerReceiveData getNoticeIntractor) {
        this.iSpinnerView = iSpinnerView;
        this.getNoticeIntractor = getNoticeIntractor;
    }

    @Override
    public void onDestroy() {
        iSpinnerView = null;
    }

    @Override
    public void requestSpinnerData() {
        getNoticeIntractor.getSpinnerData(this);
    }

    @Override
    public void onSpinnerFinished(String pair) {
        if(iSpinnerView != null){
            iSpinnerView.onSpinnerResponseSuccess(pair);
        }
    }

    @Override
    public void onSpinnerFailure(String pair) {
        if(iSpinnerView != null){
            iSpinnerView.onSpinnerResponseFailure(pair);
        }
    }
}