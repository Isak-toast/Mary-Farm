package com.ssafy.maryfarmplantservice.api.dto.plant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthPlantSearchRequestDTO {
    private String userId;
    private Integer year;
    private Integer month;
}
