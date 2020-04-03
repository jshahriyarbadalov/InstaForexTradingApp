package com.kotizm.instaforex.Data.Calendar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.kotizm.instaforex.Utils.DatePickerDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

@SuppressLint("Registered")
public class CalendarReceiveData extends AppCompatActivity implements ICalendarReceiveData {

    private Context context;
    private FragmentManager fragmentManager;
    private TextView startDatePicker;
    private TextView endDatePicker;
    private long startDate;
    private long endDate;

    public CalendarReceiveData(Context context, FragmentManager fragmentManager, TextView startDatePicker, TextView endDatePicker, long startDate, long endDate) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.startDatePicker = startDatePicker;
        this.endDatePicker = endDatePicker;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void getCalendarData(final ICalendarOnFinishedListener onCalendarFinishedListener) {

        final DatePickerDialog.OnDateSetListener startListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                startDate = calendarDate(startDatePicker, "From:", year, monthOfYear, dayOfMonth);
                if (startDate > endDate+5000) Toast.makeText(context, "The start date cannot be greater than the end date!", Toast.LENGTH_SHORT).show();
                onCalendarFinishedListener.onCalendarFinished(startDate, endDate);
            }
        };

        final DatePickerDialog.OnDateSetListener endListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                endDate = calendarDate(endDatePicker, "To:",year, monthOfYear, dayOfMonth);
                if (endDate < startDate) Toast.makeText(context, "The end date cannot be greater than the start date!", Toast.LENGTH_SHORT).show();
                onCalendarFinishedListener.onCalendarFinished(startDate, endDate);
            }
        };

        startDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialogFragment pickerDialogFragment = new DatePickerDialogFragment();
                pickerDialogFragment.setListener(startListener);
                pickerDialogFragment.show(fragmentManager, "startDatePicker");
            }
        });

        endDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialogFragment pickerDialogFragment = new DatePickerDialogFragment();
                pickerDialogFragment.setListener(endListener);
                pickerDialogFragment.show(fragmentManager, "endDatePicker");
            }
        });
    }

    public long calendarDate(TextView textView, String message, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "dd.MM.yyyy", Locale.getDefault());
        String dateString = simpleDateFormat.format(calendar.getTime());
        textView.setText(String.format(message + " %s (click to change)", dateString));

        return calendar.getTimeInMillis()/1000;
    }
}