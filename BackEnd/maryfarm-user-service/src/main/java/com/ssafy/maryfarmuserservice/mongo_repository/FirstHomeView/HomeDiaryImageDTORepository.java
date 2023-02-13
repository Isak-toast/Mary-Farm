package com.ssafy.maryfarmuserservice.mongo_repository.FirstHomeView;

import com.ssafy.maryfarmuserservice.api.dto.query.FirstHomeView.HomeDiaryImageDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HomeDiaryImageDTORepository extends MongoRepository<HomeDiaryImageDTO, String> {
    Optional<HomeDiaryImageDTO> findByPlantId(String plantId);
    Optional<HomeDiaryImageDTO> findTopByUserIdOrderByCreatedDateDesc(String userId);
}
