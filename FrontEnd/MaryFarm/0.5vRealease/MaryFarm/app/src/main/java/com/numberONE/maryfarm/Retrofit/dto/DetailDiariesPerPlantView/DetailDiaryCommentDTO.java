package com.numberONE.maryfarm.Retrofit.dto.DetailDiariesPerPlantView;


import java.io.Serializable;

public class DetailDiaryCommentDTO implements Serializable {
    private String profilePath;
    private String userName;
    private String content;
    private Integer likes;

    public String getProfilePath() {
        return profilePath;
    }

    public String getUserName() {
        return userName;
    }

    public String getContent() {
        return content;
    }

    public Integer getLikes() {
        return likes;
    }
}
