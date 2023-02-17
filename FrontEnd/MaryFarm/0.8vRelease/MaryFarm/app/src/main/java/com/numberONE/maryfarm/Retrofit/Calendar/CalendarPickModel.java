package com.numberONE.maryfarm.Retrofit.Calendar;

import com.prolificinteractive.materialcalendarview.CalendarDay;

// 해당일 메모 정보 불러오는 요청
public class CalendarPickModel {
    public String userId = null;
    public Integer year = CalendarDay.today().getYear();
    public Integer month = CalendarDay.today().getMonth();
    public Integer day = CalendarDay.today().getDay();
}
