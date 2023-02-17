package com.numberONE.maryfarm.Retrofit.dto.DetailDiariesPerPlantView;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class DetailDiariesPerPlantDTO implements Serializable {

    private String id;
    private String plantId;
    private String title;
    private String plantCreatedDate;
    private String harvestDate;
    private List<DetailDiaryDTO> diaries = new ArrayList<>();
    private String userId;
    private String profilePath;

    public String getId() {
        return id;
    }

    public String getPlantId() {
        return plantId;
    }

    public String getTitle() {
        return title;
    }

    public String getPlantCreatedDate() {
        return plantCreatedDate;
    }

    public String getHarvestDate() {
        return harvestDate;
    }

    public List<DetailDiaryDTO> getDiaries() {
        return diaries;
    }

    public String getUserId() {
        return userId;
    }

    public String getProfilePath() {
        return profilePath;
    }
}
