package com.ssafy.maryfarmuserservice.mongo_repository.FirstHomeView;

import com.ssafy.maryfarmuserservice.api.dto.query.FirstHomeView.FirstHomeViewDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FirstHomeViewDTORepository extends MongoRepository<FirstHomeViewDTO, String> {
    Optional<FirstHomeViewDTO> findByUserId(String userId);
}
