package com.ssafy.maryfarmplantservice.kafka.dto.plant;

import com.ssafy.maryfarmplantservice.domain.plant.Plant;
import com.ssafy.maryfarmplantservice.kafka.dto.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaPlantDTO {
    private Status status;
    private Plant plant;
}
