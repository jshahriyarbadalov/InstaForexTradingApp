package com.kotizm.instaforex.Data.Calendar;

public interface ICalendarReceiveData {

    interface ICalendarOnFinishedListener {
        void onCalendarFinished(long startDate, long endDate);
    }
    void getCalendarData(ICalendarOnFinishedListener onCalendarFinishedListener);
}