package com.ssafy.maryfarmconsumer.repository.FirstHomeView;

import com.ssafy.maryfarmconsumer.query_dto.FirstHomeView.FirstHomeViewDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FirstHomeViewDTORepository extends MongoRepository<FirstHomeViewDTO, String> {
    Optional<FirstHomeViewDTO> findByUserId(String userId);
}
