package com.numberONE.maryfarm.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Signup {

    public Signup(){}

    public Signup(String kakaoId, String userName) {
        this.kakaoId = kakaoId;
        this.userName = userName;
    }

    @SerializedName("kakaoId")
    @Expose
    private String kakaoId;

    @SerializedName("userName")
    @Expose
    private String userName;

    public void setKakaoId(String kakaoId) {
        this.kakaoId = kakaoId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKakaoId() {
        return kakaoId;
    }

    public String getUserName() {
        return userName;
    }
}
