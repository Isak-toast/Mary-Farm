package com.numberONE.maryfarm.Retrofit.Board;

public class BoardWrite {

    private String userId,userName,title,content,type,profilePath;

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
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", profilePath='" + profilePath + '\'' +
                '}';
    }
}
