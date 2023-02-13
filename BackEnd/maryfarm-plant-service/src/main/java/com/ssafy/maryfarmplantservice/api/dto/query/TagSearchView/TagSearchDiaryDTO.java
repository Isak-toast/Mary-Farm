package com.ssafy.maryfarmplantservice.api.dto.query.TagSearchView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagSearchDiaryDTO {
    @Id
    private String id;
    private String plantId;
    private String latestDiaryImagePath;
    private String title;
    private LocalDateTime diaryCreatedDate;
    private LocalDateTime plantCreatedDate;
    private LocalDateTime harvestDate;
    private List<String> otherDiaryImagePath = new ArrayList<>();

    public TagSearchDiaryDTO(Map<Object, Object> payload) {
        this.plantId = (String) payload.get("plant_id");
        this.latestDiaryImagePath = (String) payload.get("image_path");
        this.title = (String) payload.get("title");
        LocalDateTime diaryCreatedDate = LocalDateTime.ofInstant(Instant.ofEpochMilli((Long) payload.get("created_date")), TimeZone.getDefault().toZoneId());
        this.diaryCreatedDate = diaryCreatedDate;
        LocalDateTime plantCreatedDate = LocalDateTime.ofInstant(Instant.ofEpochMilli((Long) payload.get("plant_created_date")), TimeZone.getDefault().toZoneId());
        this.plantCreatedDate = plantCreatedDate;
        if(!(payload.get("harvest_date")==null)) {
            LocalDateTime harvestDate = LocalDateTime.ofInstant(Instant.ofEpochMilli((Long) payload.get("harvest_date")), TimeZone.getDefault().toZoneId());
            this.harvestDate = harvestDate;
        }
    }

    public void update(Map<Object, Object> payload) {
        String prevDiaryImagePath = this.latestDiaryImagePath;
        this.latestDiaryImagePath = (String) payload.get("image_path");
        LocalDateTime createdDate = LocalDateTime.ofInstant(Instant.ofEpochMilli((Long) payload.get("created_date")), TimeZone.getDefault().toZoneId());
        this.diaryCreatedDate = createdDate;
        this.otherDiaryImagePath.add(prevDiaryImagePath);
    }
}
