package com.numberONE.maryfarm.Retrofit.dto.MyFarmView;

import java.util.ArrayList;
import java.util.List;

public class MyFarmViewDTO {
    private String id;
    private String userId;
    private Integer followerCount;
    private Integer followingCount;
    private List<FarmDiaryDTO> diaries = new ArrayList<>();

    public void addFollowerCount(Integer x) {
        this.followerCount+=x;
    }
    public void addFollowingCount(Integer x) {
        this.followingCount+=x;
    }
}
