package com.ssafy.maryfarmuserservice.api.dto.query.FirstHomeView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Document(collection = "HomeDiaryImage")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeDiaryImageDTO {
    @Id
    private String id;
    private String plantId;
    private String userId;
    private String userName;
    private String profilePath;
    private String latestDiaryImagePath;
    private String content;
    private LocalDateTime createdDate;
    private List<String> otherDiaryImagePath = new ArrayList<>();

    public HomeDiaryImageDTO(Map<Object, Object> payload) {
        this.plantId = (String) payload.get("plant_id");
        this.userId = (String) payload.get("user_id");
        this.userName = (String) payload.get("user_name");
        this.profilePath = (String) payload.get("profile_path");
        this.latestDiaryImagePath = (String) payload.get("image_path");
        this.content = (String) payload.get("content");
        LocalDateTime createdDate = LocalDateTime.ofInstant(Instant.ofEpochMilli((Long) payload.get("created_date")), TimeZone.getDefault().toZoneId());
        this.createdDate = createdDate;
    }
}
