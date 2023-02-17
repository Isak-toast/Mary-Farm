package com.ssafy.maryfarmconsumer.query_dto.MessageRoomView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.maryfarmconsumer.repository.MessageRoomView.MessageRoomDTORepository;
import com.ssafy.maryfarmconsumer.repository.RoomListView.RoomListDTORepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageRoomViewListener {

    private final MessageRoomDTORepository messageRoomDTORepository;
    @KafkaListener(
            topics = "chatdb-room",
            groupId = "MessagesPerRoomInit"
    )
    public void MessagesPerRoomInitListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {});
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        Optional<MessageRoomDTO> dto = messageRoomDTORepository.findByRoomId((String) payload.get("room_id"));
        if(!dto.isPresent()) {
            MessageRoomDTO messageRoomDTO = new MessageRoomDTO();
            messageRoomDTO.setRoomId((String) payload.get("room_id"));
            messageRoomDTORepository.save(messageRoomDTO);
        }
    }

    @KafkaListener(
            topics = "chatdb-message",
            groupId = "MessagesPerRoom"
    )
    public void MessagePerRoomListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {});
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        MessageDTO messageDTO = new MessageDTO(payload);
        Optional<MessageRoomDTO> dto = messageRoomDTORepository.findByRoomId((String) payload.get("room_id"));
        dto.get().getMessages().add(messageDTO);
        messageRoomDTORepository.save(dto.get());
    }
}
