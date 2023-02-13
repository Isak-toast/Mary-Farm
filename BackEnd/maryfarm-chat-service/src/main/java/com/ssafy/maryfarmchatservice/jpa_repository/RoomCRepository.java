package com.ssafy.maryfarmchatservice.jpa_repository;

import com.ssafy.maryfarmchatservice.domain.chat.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomCRepository extends JpaRepository<Room, String> {
    List<Room> findRoomBySenderIdOrReceiverId(String senderId, String receiverId);
}
