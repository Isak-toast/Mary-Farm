package com.numberONE.maryfarm.Retrofit.Calendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// 캘린더 : 달의 작물 목록 받아오기 응답
// 위젯 : 오늘 작물 목록 받아오기 응답
public class ItemModel {
    @Expose
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String plantName;
    @SerializedName("createdDate")
    public String createdAt;
    @SerializedName("harvestTime")
    public String harvestTime;

    public String getId() { return id; }

    public String getPlantName() {
        return plantName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getHarvestTime() { return harvestTime; }
}
