package com.ssafy.maryfarmcalendarservice.api.dto.calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCalendarByDayRequestDTO {
    private String userId;
    private Integer year;
    private Integer month;
    private Integer day;
}
