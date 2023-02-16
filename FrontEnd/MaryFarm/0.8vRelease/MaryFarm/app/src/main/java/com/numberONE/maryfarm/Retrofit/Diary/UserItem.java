package com.numberONE.maryfarm.Retrofit.Diary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserItem {

    @SerializedName("user")
    @Expose
    private List<UserData> userlist = null ;

    @SerializedName("imagepath")
    @Expose
    private String imagepath;

    @Override
    public String toString() {
        return "UserItem{" +
                "user=" + userlist +
                ", imagepath='" + imagepath + '\'' +
                '}';
    }

    public List<UserData> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<UserData> userlist) {
        this.userlist = userlist;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }
}
