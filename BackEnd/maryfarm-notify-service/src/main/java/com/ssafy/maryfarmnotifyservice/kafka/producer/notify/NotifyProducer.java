package com.ssafy.maryfarmnotifyservice.kafka.producer.notify;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.maryfarmnotifyservice.domain.notify.Notify;
import com.ssafy.maryfarmnotifyservice.kafka.dto.Status;
import com.ssafy.maryfarmnotifyservice.kafka.dto.notify.KafkaNotifyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class NotifyProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, Notify notify, Status status) {
//        KafkaNotifyDTO kafkaNotifyDTO = new KafkaNotifyDTO(status, notify);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(notify);
        } catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }
        kafkaTemplate.send(topic, jsonInString);
    }
}
