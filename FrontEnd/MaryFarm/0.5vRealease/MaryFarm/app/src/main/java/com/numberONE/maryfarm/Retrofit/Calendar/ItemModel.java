package com.numberONE.maryfarm.Retrofit.Calendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemModel {
    @Expose
    public long id;
    @SerializedName("name")
    public String plantName;
    @SerializedName("createdDate")
    public String createdAt;
    @SerializedName("harvestTime")
    public String harvestTime;

    public long getId() { return id; }

    public String getPlantName() {
        return plantName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getHarvestTime() { return harvestTime; }
}
