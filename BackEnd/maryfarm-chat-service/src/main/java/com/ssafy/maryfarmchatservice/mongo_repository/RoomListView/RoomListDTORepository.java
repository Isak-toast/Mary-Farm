package com.ssafy.maryfarmchatservice.mongo_repository.RoomListView;

import com.ssafy.maryfarmchatservice.api.dto.query.RoomListView.RoomListDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoomListDTORepository extends MongoRepository<RoomListDTO, String> {
    Optional<RoomListDTO> findByUserId(String userId);
}
