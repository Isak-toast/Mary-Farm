package com.ssafy.maryfarmboardservice.domain.board;

import com.ssafy.maryfarmboardservice.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleComment extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "article_comment_id")
    private String id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id")
    private Article article;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_name")
    private String userName;
    private String content;
    private Integer likes;

    public static ArticleComment of(Article article, String userId, String userName, String content) {
        ArticleComment comment = new ArticleComment();
        comment.article = article;
        comment.userId = userId;
        comment.userName = userName;
        comment.content = content;
        comment.likes = 0;
        return comment;
    }
}
