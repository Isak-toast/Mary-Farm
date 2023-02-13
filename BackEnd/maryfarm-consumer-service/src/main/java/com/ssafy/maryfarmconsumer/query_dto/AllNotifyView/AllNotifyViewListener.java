package com.ssafy.maryfarmconsumer.query_dto.AllNotifyView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.maryfarmconsumer.query_dto.RoomListView.RoomListDTO;
import com.ssafy.maryfarmconsumer.repository.AllNotifyView.AllNotifyDTORepository;
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
public class AllNotifyViewListener {

    private final AllNotifyDTORepository allNotifyDTORepository;

    @KafkaListener(
            topics = "userdb-user",
            groupId = "AllNotifyViewInit"
    )
    public void AllNotifyViewInitListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {
        });
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        Optional<AllNotifyDTO> dto = allNotifyDTORepository.findByUserId((String) payload.get("user_id"));
        if(!dto.isPresent()) {
            AllNotifyDTO allNotifyDTO = new AllNotifyDTO(payload);
            allNotifyDTORepository.save(allNotifyDTO);
        }
    }

    @KafkaListener(
            topics = "notifydb-notify",
            groupId = "AllNotifyView"
    )
    public void AllNotifyViewListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {});
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        NotifyDTO notifyDTO = new NotifyDTO(payload);
        Optional<AllNotifyDTO> dto = allNotifyDTORepository.findByUserId((String) payload.get("user_id"));
        dto.get().getNotifies().add(notifyDTO);
        allNotifyDTORepository.save(dto.get());
    }
}
