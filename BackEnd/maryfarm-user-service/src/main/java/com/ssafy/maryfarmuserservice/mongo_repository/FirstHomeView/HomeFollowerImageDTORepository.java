package com.ssafy.maryfarmuserservice.mongo_repository.FirstHomeView;

import com.ssafy.maryfarmuserservice.api.dto.query.FirstHomeView.HomeFollowerImageDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HomeFollowerImageDTORepository extends MongoRepository<HomeFollowerImageDTO, String> {
    Optional<HomeFollowerImageDTO> findByUserId(String userId);
}
