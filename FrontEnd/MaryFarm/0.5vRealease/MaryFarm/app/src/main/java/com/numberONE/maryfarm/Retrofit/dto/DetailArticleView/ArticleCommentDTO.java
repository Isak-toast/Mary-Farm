package com.numberONE.maryfarm.Retrofit.dto.DetailArticleView;

import java.util.Map;
public class ArticleCommentDTO {
    private String commentId;
    private String userId;
    private String userName;
    private String content;
    private Integer likes;

    public ArticleCommentDTO(Map<Object, Object> payload) {
        this.commentId = (String) payload.get("article_comment_id");
        this.userId = (String) payload.get("user_id");
        this.userName = (String) payload.get("user_name");
        this.content = (String) payload.get("content");
        this.likes = 0;
    }
}
