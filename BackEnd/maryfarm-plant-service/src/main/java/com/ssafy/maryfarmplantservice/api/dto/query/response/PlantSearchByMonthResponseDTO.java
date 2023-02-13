package com.ssafy.maryfarmplantservice.api.dto.query.response;

import com.ssafy.maryfarmplantservice.domain.plant.Plant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantSearchByMonthResponseDTO {
    private String id;
    private String userId;
    private String name;
    private String title;
    private LocalDateTime harvestTime;
    private Boolean active;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public static PlantSearchByMonthResponseDTO of(Plant plant) {
        PlantSearchByMonthResponseDTO dto = new PlantSearchByMonthResponseDTO();
        dto.id = plant.getId();
        dto.userId = plant.getUserId();
        dto.name = plant.getName();
        dto.title = plant.getTitle();
        dto.harvestTime = plant.getHarvestDate();
        dto.active = plant.getActive();
        dto.createdDate = plant.getCreatedDate();
        dto.lastModifiedDate = plant.getLastModifiedDate();
        return dto;
    }
}
