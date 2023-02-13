package com.ssafy.maryfarmchatservice.kafka.producer.room;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.maryfarmchatservice.domain.chat.Room;
import com.ssafy.maryfarmchatservice.kafka.dto.Status;
import com.ssafy.maryfarmchatservice.kafka.dto.room.KafkaRoomDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, Room room, Status status) {
//        KafkaRoomDTO kafkaRoomDTO = new KafkaRoomDTO(status, room);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(room);
        } catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }
        kafkaTemplate.send(topic, jsonInString);
    }
}
