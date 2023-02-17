package com.numberONE.maryfarm.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Signup {
    @SerializedName("kakaoId")
    @Expose
    private String kakaoId;

    @SerializedName("userName")
    @Expose
    private String userName;

    public Signup(String kakaoId, String userName) {
        this.kakaoId = kakaoId;
        this.userName = userName;
    }

    public String getKakaoId() {
        return kakaoId;
    }

    public String getUserName() {
        return userName;
    }
}
