package com.ssafy.maryfarmplantservice.mongo_repository.TagSearchView;

import com.ssafy.maryfarmplantservice.api.dto.query.TagSearchView.TagSearchDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TagSearchDTORepository extends MongoRepository<TagSearchDTO, String> {
    Optional<TagSearchDTO> findByTagName(String tagName);
}
