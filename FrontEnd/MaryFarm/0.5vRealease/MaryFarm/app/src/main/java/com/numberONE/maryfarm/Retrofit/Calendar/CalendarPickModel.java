package com.numberONE.maryfarm.Retrofit.Calendar;

import com.prolificinteractive.materialcalendarview.CalendarDay;

public class CalendarPickModel {
    public String userId = null;
    public Integer year = CalendarDay.today().getYear();
    public Integer month = CalendarDay.today().getMonth();
    public Integer day = CalendarDay.today().getDay();
}
