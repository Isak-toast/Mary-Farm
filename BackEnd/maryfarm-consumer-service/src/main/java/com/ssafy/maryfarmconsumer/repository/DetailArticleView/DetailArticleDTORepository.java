package com.ssafy.maryfarmconsumer.repository.DetailArticleView;

import com.ssafy.maryfarmconsumer.query_dto.DetailArticleView.DetailArticleDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DetailArticleDTORepository extends MongoRepository<DetailArticleDTO, String> {
    Optional<DetailArticleDTO> findByArticleId(String articleId);
}
