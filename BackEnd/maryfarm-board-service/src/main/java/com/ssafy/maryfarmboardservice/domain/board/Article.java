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
public class Article extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "article_id")
    private String id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_name")
    private String userName;
    @Enumerated(EnumType.STRING)
    private BoardType type;
    private String title;
    private String content;
    private Integer views;
    private Integer likes;
    @Column(name = "comment_count")
    private Integer commentCount;
     public static Article of(String userId, String userName, BoardType type, String title, String content) {
        Article article = new Article();
        article.userId = userId;
        article.userName = userName;
        article.type = type;
        article.title = title;
        article.content = content;
        article.views = 0;
        article.likes = 0;
        article.commentCount = 0;
        return article;
    }

    public void addViews(Integer viewCnt) {
        this.views+=viewCnt;
    }
}
