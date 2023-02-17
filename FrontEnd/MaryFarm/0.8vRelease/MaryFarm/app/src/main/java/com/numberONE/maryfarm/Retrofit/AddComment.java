package com.numberONE.maryfarm.Retrofit;

public class AddComment {
    private String diaryId;
    private String userId;
    private String userName;
    private String content;
    private String profilePath;

    public AddComment(String diaryId, String userId, String userName, String content, String profilePath) {
        this.diaryId = diaryId;
        this.userId = userId;
        this.userName = userName;
        this.content = content;
        this.profilePath = profilePath;
    }

    public String getDiaryId() {
        return diaryId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getContent() {
        return content;
    }

    public String getProfilePath() {
        return profilePath;
    }
}
