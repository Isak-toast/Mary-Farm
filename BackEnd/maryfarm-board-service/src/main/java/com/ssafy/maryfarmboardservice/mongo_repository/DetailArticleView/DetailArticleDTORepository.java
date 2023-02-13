package com.ssafy.maryfarmboardservice.mongo_repository.DetailArticleView;

import com.ssafy.maryfarmboardservice.api.dto.query.DetailArticleView.DetailArticleDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DetailArticleDTORepository extends MongoRepository<DetailArticleDTO, String> {
    Optional<DetailArticleDTO> findByArticleId(String articleId);
}
