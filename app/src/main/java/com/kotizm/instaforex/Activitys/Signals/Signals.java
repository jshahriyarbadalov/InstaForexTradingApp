package com.kotizm.instaforex.Activitys.Signals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kotizm.instaforex.Activitys.Main.Main;
import com.kotizm.instaforex.Adapter.SignalsAdapter;
import com.kotizm.instaforex.Data.Calendar.CalendarReceiveData;
import com.kotizm.instaforex.Data.Signals.ReceiveData;
import com.kotizm.instaforex.Data.Spinner.SpinnerReceiveData;
import com.kotizm.instaforex.Model.Message;
import com.kotizm.instaforex.Model.Pairs;
import com.kotizm.instaforex.Presenter.Calendar.CalendarPresenter;
import com.kotizm.instaforex.Presenter.Calendar.ICalendarPresenter;
import com.kotizm.instaforex.Presenter.Signals.ISignalsPresenter;
import com.kotizm.instaforex.Presenter.Spinner.ISpinnerPresenter;
import com.kotizm.instaforex.Presenter.Signals.SignalsPresenter;
import com.kotizm.instaforex.Presenter.Spinner.SpinnerPresenter;
import com.kotizm.instaforex.R;
import com.kotizm.instaforex.View.ICalendarView;
import com.kotizm.instaforex.View.ISignalsView;
import com.kotizm.instaforex.View.ISpinnerView;

import java.util.List;

public class Signals extends AppCompatActivity implements ISignalsView, ISpinnerView, ICalendarView {

    private ISpinnerPresenter iSpinnerPresenter;
    private ICalendarPresenter iCalendarPresenter;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ISignalsPresenter iSignalsPresenter;

    private String token;
    private String login;
    private static final String TOKEN = "token";
    private static final String LOGIN = "login";

    private String pair;
    private long startDate = 1520217807;
    private long endDate = 1525916607;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signals);

        if (savedInstanceState == null) {
            token = getIntent().getStringExtra(TOKEN);
            login = getIntent().getStringExtra(LOGIN);
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this) );

        progressBar = findViewById(R.id.progressBar);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<Pairs> adapter = new ArrayAdapter<>(this, R.layout.view_spinner_item, Pairs.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        TextView startDatePicker = findViewById(R.id.textView);
        TextView endDatePicker = findViewById(R.id.textView2);
        FragmentManager fragmentManager = this.getSupportFragmentManager();

        recyclerView.setAdapter(null);
        iSignalsPresenter = new SignalsPresenter(this,
                new ReceiveData(token, Pairs.EURUSD.toString(), login, startDate, endDate));
        iSignalsPresenter.requestData();

        iSpinnerPresenter = new SpinnerPresenter(this,
                new SpinnerReceiveData(spinner));
        iSpinnerPresenter.requestSpinnerData();

        iCalendarPresenter = new CalendarPresenter(this,
                new CalendarReceiveData(this, fragmentManager, startDatePicker, endDatePicker, startDate, endDate));
        iCalendarPresenter.requestCalendarData();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSpinnerResponseSuccess(String pair) {
        this.pair = pair;
        showNewData();
    }

    @Override
    public void onSpinnerResponseFailure(String pair) {
        this.pair = pair;
        showNewData();
    }

    @Override
    public void onCalendarResponseSuccess(long startDate, long endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        showNewData();
    }

    @Override
    public void onResponseSuccess(List<Message> listSignals) {
        recyclerView.setAdapter( new SignalsAdapter(listSignals, getLayoutInflater()) );
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(this, throwable.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iSignalsPresenter != null) iSignalsPresenter.onDestroy();
        if (iSpinnerPresenter != null) iSpinnerPresenter.onDestroy();
        if (iCalendarPresenter != null) iCalendarPresenter.onDestroy();
    }

    private void showNewData() {
        if (startDate > endDate) {
            Toast.makeText(this, "Enter the correct date range!", Toast.LENGTH_SHORT).show();
        } else {
            recyclerView.setAdapter(null);
            iSignalsPresenter = new SignalsPresenter(this,
                    new ReceiveData(token, pair, login, startDate, endDate));
            iSignalsPresenter.requestData();
        }
    }
}