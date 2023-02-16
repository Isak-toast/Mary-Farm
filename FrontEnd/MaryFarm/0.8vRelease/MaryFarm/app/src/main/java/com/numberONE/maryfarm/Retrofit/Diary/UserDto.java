package com.numberONE.maryfarm.Retrofit.Diary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDto {

    @SerializedName("userId")
    @Expose
    private String userid;

    @SerializedName("plantId")
    @Expose
    private String plantId;

    @SerializedName("userName")
    @Expose
    private String userName;

    @SerializedName("profilePath")
    @Expose
    private String profilePath;

    @SerializedName("content")
    @Expose
    private String content;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
