package com.ssafy.maryfarmconsumer.repository.DetailDiariesPerPlantView;

import com.ssafy.maryfarmconsumer.query_dto.DetailDiariesPerPlantView.DetailDiariesPerPlantDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DetailDiariesPerPlantDTORepository extends MongoRepository<DetailDiariesPerPlantDTO, String> {
    Optional<DetailDiariesPerPlantDTO> findByPlantId(String plantId);
}
