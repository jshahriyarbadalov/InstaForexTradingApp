package com.kotizm.instaforex.Data.Spinner;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.kotizm.instaforex.Model.Pairs;

@SuppressLint("Registered")
public class SpinnerReceiveData extends AppCompatActivity implements ISpinnerReceiveData {

    private Spinner spinner;
    private String pairs;

    public SpinnerReceiveData(Spinner spinner) {
        this.spinner = spinner;
    }

    @Override
    public void getSpinnerData(final ISpinnerOnFinishedListener onSpinnerFinishedListener) {
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pairs = parent.getItemAtPosition(position).toString();
                onSpinnerFinishedListener.onSpinnerFinished(pairs);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pairs = String.valueOf(Pairs.EURUSD);
                onSpinnerFinishedListener.onSpinnerFailure(pairs);
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }
}