package com.ssafy.maryfarmplantservice.mongo_repository.DetailDiariesPerPlantView;

import com.ssafy.maryfarmplantservice.api.dto.query.DetailDiariesPerPlantView.DetailDiariesPerPlantDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DetailDiariesPerPlantDTORepository extends MongoRepository<DetailDiariesPerPlantDTO, String> {
    Optional<DetailDiariesPerPlantDTO> findByPlantId(String plantId);
}
