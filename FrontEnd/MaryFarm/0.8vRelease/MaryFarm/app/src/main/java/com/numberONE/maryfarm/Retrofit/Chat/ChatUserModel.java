package com.numberONE.maryfarm.Retrofit.Chat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.numberONE.maryfarm.Retrofit.Calendar.ItemModel;

public class ChatUserModel {
    @Expose
    public long id;
    @SerializedName("userId")
    public String userId;
    @SerializedName("nickname")
    public String nickname;
    @SerializedName("tier")
    public String tier;
    @SerializedName("profilepath")
    public String profilepath;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public void setProfilepath(String profilepath) {
        this.profilepath = profilepath;
    }

    public String getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTier() {
        return tier;
    }

    public String getProfilepath() {
        return profilepath;
    }
}