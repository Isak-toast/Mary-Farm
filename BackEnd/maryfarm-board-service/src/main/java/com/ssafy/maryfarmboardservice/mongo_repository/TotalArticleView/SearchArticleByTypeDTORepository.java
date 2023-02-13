package com.ssafy.maryfarmboardservice.mongo_repository.TotalArticleView;

import com.ssafy.maryfarmboardservice.api.dto.query.TotalArticleView.SearchArticleByTypeDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SearchArticleByTypeDTORepository extends MongoRepository<SearchArticleByTypeDTO, String> {
    Optional<SearchArticleByTypeDTO> findByType(String type);
}
