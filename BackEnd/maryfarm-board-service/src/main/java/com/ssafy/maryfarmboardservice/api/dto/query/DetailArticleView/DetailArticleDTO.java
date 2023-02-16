package com.ssafy.maryfarmboardservice.api.dto.query.DetailArticleView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "detailArticle")
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
}
