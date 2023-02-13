package com.ssafy.maryfarmuserservice.api.dto.query.FirstHomeView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "HomeFollowerImage")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeFollowerImageDTO {
    @Id
    private String id;
    private String userId;
    private String userName;
    private String profilePath;
    private String latestDiaryImagePath;

    public HomeFollowerImageDTO(Map<Object, Object> payload) {
        this.userId = (String) payload.get("user_id");
        this.userName = (String) payload.get("user_name");
        this.profilePath = (String) payload.get("profile_path");
        this.latestDiaryImagePath = (String) payload.get("image_path");
    }

}
