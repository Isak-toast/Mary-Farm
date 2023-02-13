package com.ssafy.maryfarmcalendarservice.domain.calendar;

import com.ssafy.maryfarmcalendarservice.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Calendar extends BaseTimeEntity implements Serializable {
    @EmbeddedId
    private CalendarID id;
    private String userId;
    private Boolean water;
    private Boolean branch;
    private Boolean nutrients;
    private Boolean division;
    private String memo;
    public static Calendar of(String plantId, Integer year, Integer month,
                              Integer day, String userId,
                              Boolean water, Boolean branch, Boolean nutrients,
                              Boolean division, String memo) {
        Calendar calendar = new Calendar();
        calendar.id = new CalendarID(plantId,year,month,day);
        calendar.userId = userId;
        calendar.water = water;
        calendar.branch = branch;
        calendar.nutrients = nutrients;
        calendar.division = division;
        calendar.memo = memo;
        return calendar;
    }
}
