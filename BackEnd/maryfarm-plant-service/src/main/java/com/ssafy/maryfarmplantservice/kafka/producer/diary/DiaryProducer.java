package com.ssafy.maryfarmplantservice.kafka.producer.diary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.maryfarmplantservice.domain.diary.Diary;
import com.ssafy.maryfarmplantservice.kafka.dto.Status;
import com.ssafy.maryfarmplantservice.kafka.dto.diary.KafkaDiaryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class DiaryProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, Diary diary, Status status) {
//        KafkaDiaryDTO kafkaDiaryDTO = new KafkaDiaryDTO(status, diary);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(diary);
        } catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }
        kafkaTemplate.send(topic, jsonInString);
    }
}
