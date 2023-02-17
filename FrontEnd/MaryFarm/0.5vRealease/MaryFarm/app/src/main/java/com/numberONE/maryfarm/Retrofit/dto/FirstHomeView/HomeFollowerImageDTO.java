package com.numberONE.maryfarm.Retrofit.dto.FirstHomeView;

import java.util.Map;

public class HomeFollowerImageDTO {
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
