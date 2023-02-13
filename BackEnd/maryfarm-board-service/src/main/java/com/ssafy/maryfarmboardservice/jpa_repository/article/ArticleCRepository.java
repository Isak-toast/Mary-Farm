package com.ssafy.maryfarmboardservice.jpa_repository.article;

import com.ssafy.maryfarmboardservice.domain.board.Article;
import com.ssafy.maryfarmboardservice.domain.board.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleCRepository extends JpaRepository<Article, String> {
    Optional<Article> findById(String id);
    List<Article> findByType(BoardType type);
}
