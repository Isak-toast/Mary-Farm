package com.numberONE.maryfarm.Retrofit.Diary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserApi {

    @SerializedName("abcd")
    @Expose
    private UserItem useritem;

    public UserItem getUseritem() {
        return useritem;
    }

    public void setUseritem(UserItem useritem) {
        this.useritem = useritem;
    }
}
