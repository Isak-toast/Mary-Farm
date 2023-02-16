package com.numberONE.maryfarm.Retrofit.Board;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BoardComments {

    @SerializedName("commentId")
    @Expose
    private String commentId;

    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("userName")
    @Expose
    private String userName;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("likes")
    @Expose
    private int likes;

//    @SerializedName("") 프로필 ~
    @Expose
    private String profile;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "BoardComments{" +
                "commentId='" + commentId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", profile='" + profile + '\'' +
                '}';
    }
}

