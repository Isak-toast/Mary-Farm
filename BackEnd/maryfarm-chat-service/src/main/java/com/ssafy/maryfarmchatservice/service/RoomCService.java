package com.ssafy.maryfarmchatservice.service;

import com.ssafy.maryfarmchatservice.domain.chat.Room;
import com.ssafy.maryfarmchatservice.jpa_repository.RoomCRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class RoomCService {
    private final RoomCRepository roomCRepository;

    @Transactional
    public Room saveRoom(String senderId, String senderName, String senderProfilePath,
                         String receiverId, String receiverName, String receiverProfilePath) {
        Room room = Room.of(senderId, senderName, senderProfilePath, receiverId, receiverName, receiverProfilePath);
        return roomCRepository.save(room);
    }

    public List<Room> findByUser(String userId) {
        return roomCRepository.findRoomBySenderIdOrReceiverId(userId, userId);
    }
}
