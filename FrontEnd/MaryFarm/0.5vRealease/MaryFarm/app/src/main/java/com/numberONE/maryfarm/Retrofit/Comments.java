package com.numberONE.maryfarm.Retrofit;

import com.google.gson.annotations.SerializedName;

public class Comments {
    private String nickname;
    private String createdAt;
    private String updatedAt;
    private String content;
    private int likes;

    @SerializedName("profilepath")
    private String profileImg;

    public String getNickname() {
        return nickname;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getContent() {
        return content;
    }

    public int getLikes() {
        return likes;
    }

    public String getProfileImg() {
        return profileImg;
    }
}
