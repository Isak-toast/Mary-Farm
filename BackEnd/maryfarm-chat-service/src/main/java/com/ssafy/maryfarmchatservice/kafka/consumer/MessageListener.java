package com.ssafy.maryfarmchatservice.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.maryfarmchatservice.domain.chat.Message;
import com.ssafy.maryfarmchatservice.kafka.constants.KafkaConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageListener {
//    @KafkaListener(
//            topics = "message",
//            groupId = KafkaConstants.GROUP_ID
//    )
//    public void MessageListen(String message) {
//        log.info("Kafka Message: ->" + message);
//
//        Map<Object, Object> map = new HashMap<>();
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {
//            });
//            log.info(map.toString());
//        } catch (JsonProcessingException ex) {
//            ex.printStackTrace();
//        }
//    }

//    @KafkaListener(
//            topics = "room",
//            groupId = KafkaConstants.GROUP_ID
//    )
//    public void RoomListen(String message) {
//        log.info("Kafka Message: ->" + message);
//
//        Map<Object, Object> map = new HashMap<>();
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {
//            });
//            log.info(map.toString());
//        } catch (JsonProcessingException ex) {
//            ex.printStackTrace();
//        }
//    }
}
