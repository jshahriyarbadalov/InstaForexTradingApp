package com.kotizm.instaforex.Data.Spinner;

public interface ISpinnerReceiveData {

    interface ISpinnerOnFinishedListener {
        void onSpinnerFinished(String pair);
        void onSpinnerFailure(String pair);
    }
    void getSpinnerData(ISpinnerOnFinishedListener onSpinnerFinishedListener);
}