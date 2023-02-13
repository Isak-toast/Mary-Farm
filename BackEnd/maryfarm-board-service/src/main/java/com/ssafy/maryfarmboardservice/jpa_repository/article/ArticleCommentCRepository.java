package com.ssafy.maryfarmboardservice.jpa_repository.article;

import com.ssafy.maryfarmboardservice.domain.board.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleCommentCRepository extends JpaRepository<ArticleComment,String> {
    List<ArticleComment> findByArticle_Id(String articleId);
}
