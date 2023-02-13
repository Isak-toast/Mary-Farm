package com.ssafy.maryfarmnotifyservice.mongo_repository.AllNotifyView;

import com.ssafy.maryfarmnotifyservice.api.dto.query.AllNotifyView.AllNotifyDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AllNotifyDTORepository extends MongoRepository<AllNotifyDTO, String> {
    Optional<AllNotifyDTO> findByUserId(String userId);
}
