package com.ssafy.maryfarmboardservice.api.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCommentRequestDTO {
    private String articleId;
    private String userId;
    private String userName;
    private String content;
}
