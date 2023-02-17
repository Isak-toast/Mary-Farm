package com.ssafy.maryfarmconsumer.query_dto.DetailArticleView;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Document(collection = "DetailArticle")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailArticleDTO {
    @Id
    private String id;
    @Indexed
    private String articleId;
    private String userId;
    private String userName;
    private String profilePath;
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
        this.profilePath = (String) payload.get("profile_path");
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
