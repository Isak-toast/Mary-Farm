package com.ssafy.maryfarmconsumer.repository.AllNotifyView;

import com.ssafy.maryfarmconsumer.query_dto.AllNotifyView.AllNotifyDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AllNotifyDTORepository extends MongoRepository<AllNotifyDTO, String> {
    Optional<AllNotifyDTO> findByUserId(String userId);
}
