package com.ssafy.maryfarmboardservice.api.dto.query.TotalArticleView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
}
