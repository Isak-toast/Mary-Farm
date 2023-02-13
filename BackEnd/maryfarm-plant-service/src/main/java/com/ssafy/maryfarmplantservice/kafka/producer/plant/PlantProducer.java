package com.ssafy.maryfarmplantservice.kafka.producer.plant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.maryfarmplantservice.domain.diary.Diary;
import com.ssafy.maryfarmplantservice.domain.plant.Plant;
import com.ssafy.maryfarmplantservice.kafka.dto.Status;
import com.ssafy.maryfarmplantservice.kafka.dto.diary.KafkaDiaryDTO;
import com.ssafy.maryfarmplantservice.kafka.dto.plant.KafkaPlantDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlantProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, Plant plant, Status status) {
//        KafkaPlantDTO kafkaPlantDTO = new KafkaPlantDTO(status, plant);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(plant);
        } catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }
        kafkaTemplate.send(topic, jsonInString);
    }
}
