package com.numberONE.maryfarm.Retrofit.dto.FirstHomeView;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class HomeDiaryImageDTO {
    private String id;
    private String plantId;
    private String userId;
    private String userName;
    private String profilePath;
    private String latestDiaryImagePath;
    private String content;
    private String createdDate;
    private List<String> otherDiaryImagePath = new ArrayList<>();

    public String getId() {
        return id;
    }

    public String getPlantId() {
        return plantId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public String getLatestDiaryImagePath() {
        return latestDiaryImagePath;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public List<String> getOtherDiaryImagePath() {
        return otherDiaryImagePath;
    }

    @Override
    public String toString() {
        return "HomeDiaryImageDTO{" +
                "id='" + id + '\'' +
                ", plantId='" + plantId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", profilePath='" + profilePath + '\'' +
                ", latestDiaryImagePath='" + latestDiaryImagePath + '\'' +
                ", content='" + content + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", otherDiaryImagePath=" + otherDiaryImagePath +
                '}';
    }
}
