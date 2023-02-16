package com.numberONE.maryfarm.Retrofit.Calendar;

import com.prolificinteractive.materialcalendarview.CalendarDay;

public class WidgetModel {
    public String userId = null;
    public Integer year = CalendarDay.today().getYear();
    public Integer month = CalendarDay.today().getMonth();
    public Integer day = CalendarDay.today().getDay();

    public WidgetModel(String userId) {
        this.userId = userId;
    }
}
