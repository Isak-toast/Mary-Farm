package com.numberONE.maryfarm.Retrofit.Board;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BoardWrite {

    @Expose
    private String articleId;
    @Expose
    private String userId;
    @Expose
    private String userName;
    @Expose
    private String title;
    @Expose
    private String content;
    @Expose
    private String type;
    @Expose
    private String profilePath;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    @Override
    public String toString() {
        return "BoardWrite{" +
                "articleId='" + articleId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", profilePath='" + profilePath + '\'' +
                '}';
    }
}
