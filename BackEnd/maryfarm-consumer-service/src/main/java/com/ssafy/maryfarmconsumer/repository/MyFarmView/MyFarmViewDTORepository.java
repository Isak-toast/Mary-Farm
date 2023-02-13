package com.ssafy.maryfarmconsumer.repository.MyFarmView;

import com.ssafy.maryfarmconsumer.query_dto.MyFarmView.MyFarmViewDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MyFarmViewDTORepository extends MongoRepository<MyFarmViewDTO, String> {
    Optional<MyFarmViewDTO> findByUserId(String userId);
}
