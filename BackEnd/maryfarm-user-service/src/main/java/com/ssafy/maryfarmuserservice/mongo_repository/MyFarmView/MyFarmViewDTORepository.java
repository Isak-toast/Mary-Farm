package com.ssafy.maryfarmuserservice.mongo_repository.MyFarmView;

import com.ssafy.maryfarmuserservice.api.dto.query.MyFarmView.MyFarmViewDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MyFarmViewDTORepository extends MongoRepository<MyFarmViewDTO, String> {
    Optional<MyFarmViewDTO> findByUserId(String userId);
}
