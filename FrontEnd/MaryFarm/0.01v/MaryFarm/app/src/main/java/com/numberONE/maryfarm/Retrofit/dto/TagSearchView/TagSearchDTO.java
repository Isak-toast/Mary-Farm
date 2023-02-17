package com.numberONE.maryfarm.Retrofit.dto.TagSearchView;

import java.util.ArrayList;
import java.util.List;

public class TagSearchDTO {
    private String id;
    private String tagName;
    private List<TagSearchDiaryDTO> diaries = new ArrayList<>();
}
