package com.ssafy.maryfarmconsumer.repository.TagSearchView;

import com.ssafy.maryfarmconsumer.query_dto.TagSearchView.TagSearchDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TagSearchDTORepository extends MongoRepository<TagSearchDTO, String> {
    Optional<TagSearchDTO> findByTagName(String tagName);
}
