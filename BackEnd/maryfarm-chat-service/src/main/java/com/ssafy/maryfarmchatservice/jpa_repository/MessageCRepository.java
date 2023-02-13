package com.ssafy.maryfarmchatservice.jpa_repository;

import com.ssafy.maryfarmchatservice.domain.chat.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageCRepository extends JpaRepository<Message, String> {
    public List<Message> findByRoomIdOrderByTimestamp(String roomId, Pageable pageable);

    public List<Message> findByRoomId(String roomId);
}
