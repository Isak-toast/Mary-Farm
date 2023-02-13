package com.ssafy.maryfarmconsumer.repository.TotalArticleView;

import com.ssafy.maryfarmconsumer.query_dto.TotalArticleView.SearchArticleByTypeDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SearchArticleByTypeDTORepository extends MongoRepository<SearchArticleByTypeDTO, String> {
    Optional<SearchArticleByTypeDTO> findByType(String type);
}
