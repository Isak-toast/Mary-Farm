package com.ssafy.maryfarmchatservice.service;

import com.ssafy.maryfarmchatservice.domain.chat.Message;
import com.ssafy.maryfarmchatservice.domain.chat.Room;
import com.ssafy.maryfarmchatservice.jpa_repository.MessageCRepository;
import com.ssafy.maryfarmchatservice.jpa_repository.RoomCRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MessageCService {
    private final MessageCRepository messageCRepository;
    private final RoomCRepository roomCRepository;


    public Message searchLatestMessage(String roomId) {
        PageRequest pageRequest = PageRequest.of(0, 1);
        List<Message> list = messageCRepository.findByRoomIdOrderByTimestamp(roomId, pageRequest);
        if(list.isEmpty()) return null;
        else return list.get(0);
    }

    @Transactional
    public Message saveMessage(String roomId, String userId, String userName, String profilePath, String content) {
        Optional<Room> room = roomCRepository.findById(roomId);
        Message message = Message.of(room.get(), userId, userName, profilePath, content, LocalDateTime.now());
        return messageCRepository.save(message);
    }

    public List<Message> findMessageByRoom(String roomId) {
        return messageCRepository.findByRoomId(roomId);
    }
}
