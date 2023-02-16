package com.ssafy.maryfarmplantservice.api.dto.query.DetailDiariesPerPlantView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Document(collection = "DetailDiariesPerPlantView")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailDiariesPerPlantDTO {
    @Id
    private String id;
    @Indexed
    private String plantId;
    private String title;
    private LocalDateTime plantCreatedDate;
    private LocalDateTime harvestDate;
    private List<DetailDiaryDTO> diaries = new ArrayList<>();

    private String userId;
    private String profilePath;

}
