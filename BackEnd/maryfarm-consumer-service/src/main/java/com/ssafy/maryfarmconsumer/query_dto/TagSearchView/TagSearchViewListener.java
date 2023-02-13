package com.ssafy.maryfarmconsumer.query_dto.TagSearchView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.maryfarmconsumer.query_dto.MyFarmView.FarmDiaryDTO;
import com.ssafy.maryfarmconsumer.query_dto.MyFarmView.MyFarmViewDTO;
import com.ssafy.maryfarmconsumer.repository.TagSearchView.TagSearchDTORepository;
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
public class TagSearchViewListener {
    private final TagSearchDTORepository tagSearchDTORepository;
    @KafkaListener(
            topics = "plantdb-tag",
            groupId = "TagSearchView"
    )
    public void TagSearchViewListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {});
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        TagSearchDiaryDTO tagSearchDiaryDTO = new TagSearchDiaryDTO(payload);
        Optional<TagSearchDTO> myTagSearchDTO = tagSearchDTORepository.findByTagName((String) payload.get("name"));
        if(myTagSearchDTO.isPresent()) {
            myTagSearchDTO.get().getDiaries().add(tagSearchDiaryDTO);
            tagSearchDTORepository.save(myTagSearchDTO.get());
        } else {
            TagSearchDTO tagSearchDTO = new TagSearchDTO();
            tagSearchDTO.setTagName((String) payload.get("name"));
            tagSearchDTO.getDiaries().add(tagSearchDiaryDTO);
            tagSearchDTORepository.save(tagSearchDTO);
        }
    }
}
