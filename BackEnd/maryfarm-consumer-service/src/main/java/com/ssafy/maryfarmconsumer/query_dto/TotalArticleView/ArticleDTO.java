package com.ssafy.maryfarmconsumer.query_dto.TotalArticleView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TimeZone;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    private String articleId;
    private String userName;
    private String title;
    private Integer views;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public ArticleDTO (Map<Object, Object> payload) {
        this.articleId = (String) payload.get("article_id");
        this.userName = (String) payload.get("user_name");
        this.title = (String) payload.get("title");
        this.views = 0;
        LocalDateTime createdDate = LocalDateTime.ofInstant(Instant.ofEpochMilli((Long) payload.get("created_date")), TimeZone.getDefault().toZoneId());
        LocalDateTime lastModifiedDate = LocalDateTime.ofInstant(Instant.ofEpochMilli((Long) payload.get("last_modified_date")), TimeZone.getDefault().toZoneId());
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}
