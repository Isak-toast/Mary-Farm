package com.ssafy.maryfarmuserservice.kafka.producer.follow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.maryfarmuserservice.domain.user.Follow;
import com.ssafy.maryfarmuserservice.kafka.dto.Status;
import com.ssafy.maryfarmuserservice.kafka.dto.follow.KafkaFollowDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class FollowProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, Follow follow, Status status) {
//        KafkaFollowDTO kafkaFollowDTO = new KafkaFollowDTO(status, follow);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(follow);
        } catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }
        kafkaTemplate.send(topic, jsonInString);
    }
}
