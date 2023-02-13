package com.ssafy.maryfarmchatservice.kafka.producer.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.maryfarmchatservice.domain.chat.Message;
import com.ssafy.maryfarmchatservice.kafka.dto.Status;
import com.ssafy.maryfarmchatservice.kafka.dto.message.KafkaMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class MessageProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, Message message, Status status) {
//        KafkaMessageDTO kafkaMessageDTO = new KafkaMessageDTO(status, message);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(message);
        } catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }
        kafkaTemplate.send(topic, jsonInString);
    }
}
