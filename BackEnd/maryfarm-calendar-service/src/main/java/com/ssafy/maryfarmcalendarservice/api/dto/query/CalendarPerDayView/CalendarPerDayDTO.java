package com.ssafy.maryfarmcalendarservice.api.dto.query.CalendarPerDayView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "CalendarPerDay")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarPerDayDTO {
    @Id
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
}
