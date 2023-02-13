package com.ssafy.maryfarmconsumer.client.dto.plant;

import com.ssafy.maryfarmconsumer.client.dto.user.UserResponseDTO;
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
}
