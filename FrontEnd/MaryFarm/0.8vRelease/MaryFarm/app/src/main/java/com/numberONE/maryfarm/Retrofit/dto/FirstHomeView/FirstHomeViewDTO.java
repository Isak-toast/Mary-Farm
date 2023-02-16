package com.numberONE.maryfarm.Retrofit.dto.FirstHomeView;

import java.util.ArrayList;
import java.util.List;

public class FirstHomeViewDTO {
    private String id;
    private String userId;
    private String latestSystemNotify;
    private List<HomeFollowerImageDTO> followers = new ArrayList<>();
    private List<HomeDiaryImageDTO> diaries = new ArrayList<>();

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getLatestSystemNotify() {
        return latestSystemNotify;
    }

    public List<HomeFollowerImageDTO> getFollowers() {
        return followers;
    }

    public List<HomeDiaryImageDTO> getDiaries() {
        return diaries;
    }
}
