package com.ssafy.maryfarmcalendarservice.kafka.dto.calendar;

import com.ssafy.maryfarmcalendarservice.domain.calendar.Calendar;
import com.ssafy.maryfarmcalendarservice.kafka.dto.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaCalendarDTO {
    private Status status;
    private Calendar calendar;
}
