package com.kotizm.instaforex.Presenter.Calendar;

import com.kotizm.instaforex.Data.Calendar.ICalendarReceiveData;
import com.kotizm.instaforex.View.ICalendarView;

public class CalendarPresenter implements ICalendarPresenter, ICalendarReceiveData.ICalendarOnFinishedListener {

    private ICalendarView iCalendarView;
    private ICalendarReceiveData getNoticeIntractor;

    public CalendarPresenter(ICalendarView iCalendarView, ICalendarReceiveData getNoticeIntractor) {
        this.iCalendarView = iCalendarView;
        this.getNoticeIntractor = getNoticeIntractor;
    }

    @Override
    public void onDestroy() {
        iCalendarView = null;
    }

    @Override
    public void requestCalendarData() {
        getNoticeIntractor.getCalendarData(this);
    }

    @Override
    public void onCalendarFinished(long startDate, long endDate) {
        if(iCalendarView != null){
            iCalendarView.onCalendarResponseSuccess(startDate, endDate);
        }
    }
}