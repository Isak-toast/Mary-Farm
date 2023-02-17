package com.numberONE.maryfarm.Retrofit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailsAPI {

    private String diaryId, content, imagepath;
    private Map<Object, Object> plant = new HashMap<>();
    private int likes;

    public Map<Object, Object> getPlant() { return plant; }

    public String getDiaryId() {
        return diaryId;
    }

    public String getContent() {
        return content;
    }

    public String getImagepath() {
        return imagepath;
    }

    public int getLikes() {
        return likes;
    }
}
