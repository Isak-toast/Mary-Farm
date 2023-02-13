package com.ssafy.maryfarmcalendarservice.domain.calendar;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ssafy.maryfarmcalendarservice.formatter.LocalDateTimeDeserializer;
import com.ssafy.maryfarmcalendarservice.formatter.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarID implements Serializable {
    @Column(name = "plant_id")
    private String plantId;
    private Integer year;
    private Integer month;
    private Integer day;
}
