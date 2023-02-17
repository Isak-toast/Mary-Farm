package com.numberONE.maryfarm.Retrofit.dto.DetailArticleView;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;


public class DetailArticleDTO {
    private String id;
    private String articleId;
    private String userId;
    private String userName;
    private String type;
    private String title;
    private String content;
    private Integer views;
    private Integer likes;
    private Integer commentCount;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    private List<ArticleCommentDTO> comments = new ArrayList<>();

    public DetailArticleDTO(Map<Object, Object> payload) {
        this.articleId = (String) payload.get("article_id");
        this.userId = (String) payload.get("user_id");
        this.userName = (String) payload.get("user_name");
        this.type = (String) payload.get("type");
        this.title = (String) payload.get("title");
        this.content = (String) payload.get("content");
        this.views = 0;
        this.likes = 0;
        this.commentCount = 0;
        LocalDateTime createdDate = LocalDateTime.ofInstant(Instant.ofEpochMilli((Long) payload.get("created_date")), TimeZone.getDefault().toZoneId());
        LocalDateTime lastModifiedDate = LocalDateTime.ofInstant(Instant.ofEpochMilli((Long) payload.get("last_modified_date")), TimeZone.getDefault().toZoneId());
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}
