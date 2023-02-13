package com.ssafy.maryfarmconsumer.repository.FirstHomeView;

import com.ssafy.maryfarmconsumer.query_dto.FirstHomeView.HomeFollowerImageDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HomeFollowerImageDTORepository extends MongoRepository<HomeFollowerImageDTO, String> {
    Optional<HomeFollowerImageDTO> findByUserId(String userId);
}
