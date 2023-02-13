package com.ssafy.maryfarmplantservice.api.dto.query.DetailDiariesPerPlantView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailDiaryDTO {
    private String imagePath;
    private Integer likes;
    private String content;
    private LocalDateTime diaryCreatedDate;
    private List<DetailDiaryCommentDTO> comments = new ArrayList<>();

    public DetailDiaryDTO(Map<Object, Object> payload) {
        this.imagePath = (String) payload.get("image_path");
        this.likes = 0;
        this.content = (String) payload.get("content");
        LocalDateTime diaryCreatedDate = LocalDateTime.ofInstant(Instant.ofEpochMilli((Long) payload.get("created_date")), TimeZone.getDefault().toZoneId());
        this.diaryCreatedDate = diaryCreatedDate;
    }
}
