package com.numberONE.maryfarm.Retrofit.Chat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatModel {
    @Expose
    public long id;
    @SerializedName("opponent")
    public ChatUserModel opponent;
    @SerializedName("latestMessage")
    public String latestMessage;

    public long getId() {
        return id;
    }

    public ChatUserModel getOpponent() {
        return opponent;
    }

    public String getLatestMessage() {
        return latestMessage;
    }
}
