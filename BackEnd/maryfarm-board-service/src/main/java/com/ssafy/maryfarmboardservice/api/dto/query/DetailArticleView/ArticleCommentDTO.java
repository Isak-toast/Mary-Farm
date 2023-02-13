package com.ssafy.maryfarmboardservice.api.dto.query.DetailArticleView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCommentDTO {
    private String commentId;
    private String userId;
    private String userName;
    private String content;
    private Integer likes;
}
