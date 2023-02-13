package com.ssafy.maryfarmconsumer.repository.FirstHomeView;

import com.ssafy.maryfarmconsumer.query_dto.FirstHomeView.HomeDiaryImageDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HomeDiaryImageDTORepository extends MongoRepository<HomeDiaryImageDTO, String> {
    Optional<HomeDiaryImageDTO> findByPlantId(String plantId);
    Optional<HomeDiaryImageDTO> findTopByUserIdOrderByCreatedDateDesc(String userId);
}
