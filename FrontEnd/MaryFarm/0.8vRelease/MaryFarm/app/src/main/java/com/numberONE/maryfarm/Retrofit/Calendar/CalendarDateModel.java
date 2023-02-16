package com.numberONE.maryfarm.Retrofit.Calendar;

import com.prolificinteractive.materialcalendarview.CalendarDay;

// 캘린더 : 달의 작물 받아오기 요청
public class CalendarDateModel {
    public String userId = null;
    public Integer year = CalendarDay.today().getYear();
    public Integer month = CalendarDay.today().getMonth();
}
