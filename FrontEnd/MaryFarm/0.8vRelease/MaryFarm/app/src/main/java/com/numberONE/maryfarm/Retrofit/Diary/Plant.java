package com.numberONE.maryfarm.Retrofit.Diary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Plant {

    @SerializedName("plantId")
    @Expose
    private String plant_id;

    @SerializedName("user")
    @Expose
    private UserData userdata;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("lastModifiedDate")
    @Expose
    private String date;

    public String getPlant_id() {
        return plant_id;
    }

    public void setPlant_id(String plant_id) {
        this.plant_id = plant_id;
    }

    public UserData getUserdata() {
        return userdata;
    }

    public void setUserdata(UserData userdata) {
        this.userdata = userdata;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
