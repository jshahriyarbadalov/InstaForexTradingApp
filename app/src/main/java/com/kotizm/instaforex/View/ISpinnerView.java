package com.kotizm.instaforex.View;

public interface ISpinnerView {

    void onSpinnerResponseSuccess(String pair);
    void onSpinnerResponseFailure(String pair);
}