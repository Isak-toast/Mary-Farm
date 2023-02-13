package com.ssafy.maryfarmplantservice.api.dto.query.response;

import com.ssafy.maryfarmplantservice.client.dto.user.UserResponseDTO;
import com.ssafy.maryfarmplantservice.domain.plant.Plant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantResponseDTO {
    private String plantId;
    private UserResponseDTO user;
    private String name;
    private String title;
    private LocalDateTime harvestTime;
    private boolean active;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    public static PlantResponseDTO of(Plant plant, UserResponseDTO userDto) {
        PlantResponseDTO dto = new PlantResponseDTO();
        dto.plantId = plant.getId();
        dto.user = userDto;
        dto.name = plant.getName();
        dto.title = plant.getTitle();;
        dto.harvestTime = plant.getHarvestDate();
        dto.active = plant.getActive();
        return dto;
    }
}
