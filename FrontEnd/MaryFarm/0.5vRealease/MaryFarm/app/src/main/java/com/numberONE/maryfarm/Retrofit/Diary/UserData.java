package com.numberONE.maryfarm.Retrofit.Diary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//@SerializedName - JSON으로 serialize 될 때 매칭되는 이름을 명시하는 목적으로 사용
//@Expose - object 중 해당 값이 null 일 경우 , json으로 만들 필드를 자동 생략해준다.
public class UserData {

    @SerializedName("userId")
    @Expose
    private String userid;

    @SerializedName("nickname")
    @Expose
    private String nickname;

    @SerializedName("tier")
    @Expose
    private String tier;

    @SerializedName("profilepath")
    @Expose
    private String profilepath;

    @SerializedName("land")
    @Expose
    private List<Land> land;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getProfilepath() {
        return profilepath;
    }

    public void setProfilepath(String profilepath) {
        this.profilepath = profilepath;
    }

    public List<Land> getLand() {
        return land;
    }

    public void setLand(List<Land> land) {
        this.land = land;
    }
}
