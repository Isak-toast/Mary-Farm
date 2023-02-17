package com.numberONE.maryfarm.Retrofit.dto.CalendarPerDayView;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TimeZone;

public class CalendarPerDayDTO {
    private String id;
    private String plantId;
    private String userId;
    private Integer year;
    private Integer month;
    private Integer day;
    private Boolean water;
    private Boolean branch;
    private Boolean nutrients;
    private Boolean division;
    private String memo;
    public CalendarPerDayDTO(Map<Object, Object> payload) {
        this.plantId = (String) payload.get("plant_id");
        this.userId = (String) payload.get("user_id");
        this.year = (Integer) payload.get("year");
        this.month = (Integer) payload.get("month");
        this.day = (Integer) payload.get("day");
        this.water = ((Integer) payload.get("water") != 0);
        this.branch = ((Integer) payload.get("branch") != 0);
        this.nutrients = ((Integer) payload.get("nutrients") != 0);
        this.division = ((Integer) payload.get("division") != 0);
        this.memo = (String) payload.get("memo");
    }
    public void changeStatus(Map<Object, Object> payload) {
        this.water = ((Integer) payload.get("water") != 0);
        this.branch = ((Integer) payload.get("branch") != 0);
        this.nutrients = ((Integer) payload.get("nutrients") != 0);
        this.division = ((Integer) payload.get("division") != 0);
        this.memo = (String) payload.get("memo");
    }
}
