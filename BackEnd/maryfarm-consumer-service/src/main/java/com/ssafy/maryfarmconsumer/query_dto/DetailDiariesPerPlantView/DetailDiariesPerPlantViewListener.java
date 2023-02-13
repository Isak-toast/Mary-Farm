package com.ssafy.maryfarmconsumer.query_dto.DetailDiariesPerPlantView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.maryfarmconsumer.query_dto.DetailArticleView.DetailArticleDTO;
import com.ssafy.maryfarmconsumer.repository.DetailDiariesPerPlantView.DetailDiariesPerPlantDTORepository;
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
public class DetailDiariesPerPlantViewListener {
    private final DetailDiariesPerPlantDTORepository detailDiariesPerPlantDTORepository;

    @KafkaListener(
            topics = "plantdb-diary",
            groupId = "DetailDiariesPerPlant"
    )
    public void DetailDiariesPerPlantListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {
        });
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        DetailDiaryDTO detailDiaryDTO = new DetailDiaryDTO(payload);
        Optional<DetailDiariesPerPlantDTO> dto = detailDiariesPerPlantDTORepository.findByPlantId((String) payload.get("plant_id"));
        if(!dto.isPresent()) {
            DetailDiariesPerPlantDTO detailDiariesPerPlantDTO = new DetailDiariesPerPlantDTO(payload);
            detailDiariesPerPlantDTO.getDiaries().add(detailDiaryDTO);
            detailDiariesPerPlantDTORepository.save(detailDiariesPerPlantDTO);
        } else {
            dto.get().getDiaries().add(detailDiaryDTO);
            detailDiariesPerPlantDTORepository.save(dto.get());
        }
    }
}
