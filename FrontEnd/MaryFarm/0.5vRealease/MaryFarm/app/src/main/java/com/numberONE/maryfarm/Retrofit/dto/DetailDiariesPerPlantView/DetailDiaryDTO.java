package com.numberONE.maryfarm.Retrofit.dto.DetailDiariesPerPlantView;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class DetailDiaryDTO implements Serializable {
    private String imagePath;
    private Integer likes;
    private String content;
    private String diaryCreatedDate;
    private List<DetailDiaryCommentDTO> comments = new ArrayList<>();


    public String getImagePath() {
        return imagePath;
    }

    public Integer getLikes() {
        return likes;
    }

    public String getContent() {
        return content;
    }

    public String getDiaryCreatedDate() {
        return diaryCreatedDate;
    }

    public List<DetailDiaryCommentDTO> getComments() {
        return comments;
    }
}
