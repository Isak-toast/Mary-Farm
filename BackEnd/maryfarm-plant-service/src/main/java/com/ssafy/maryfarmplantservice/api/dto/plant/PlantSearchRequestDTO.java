package com.ssafy.maryfarmplantservice.api.dto.plant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantSearchRequestDTO {
    private String text;
}
