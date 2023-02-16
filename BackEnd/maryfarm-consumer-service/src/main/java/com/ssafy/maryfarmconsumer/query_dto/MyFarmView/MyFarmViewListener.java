package com.ssafy.maryfarmconsumer.query_dto.MyFarmView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.maryfarmconsumer.repository.MyFarmView.MyFarmViewDTORepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class MyFarmViewListener {
    private final MyFarmViewDTORepository myFarmViewDTORepository;
    @KafkaListener(
            topics = "userdb-user",
            groupId = "MyFarmView"
    )
    public void MyFarmViewListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {});
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        Optional<MyFarmViewDTO> myFarmViewDTO = myFarmViewDTORepository.findByUserId((String) payload.get("user_id"));
        /*
            유저가 처음 생성됐을 때, 해당 유저만의 MyFarmView를 생성함.
            또한 유저 정보가 변경됐을 때, 해당 유저의 MyFarmView에서 유저이름,프로필을 변경함.
         */
        if(!myFarmViewDTO.isPresent()) {
            MyFarmViewDTO firstFarmViewDTO = new MyFarmViewDTO(payload);
            myFarmViewDTORepository.save(firstFarmViewDTO);
        } else {
            myFarmViewDTO.get().setUserName((String) payload.get("user_name"));
            myFarmViewDTO.get().setProfilePath((String) payload.get("profile_path"));
            myFarmViewDTORepository.save(myFarmViewDTO.get());
        }
    }

    @KafkaListener(
            topics = "plantdb-diary",
            groupId = "MyFarmViewAddingDiary"
    )
    public void MyFarmViewAddingDiaryListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {});
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        FarmDiaryDTO farmDiaryDTO = new FarmDiaryDTO(payload);
        Optional<MyFarmViewDTO> myFarmViewDTO = myFarmViewDTORepository.findByUserId((String) payload.get("user_id"));
        List<FarmDiaryDTO> list = myFarmViewDTO.get().getDiaries();
        String plantId = (String)payload.get("plant_id");
        for(FarmDiaryDTO f : list) {
            if(f.getPlantId().equals(plantId)) {
                f.update(payload);
                myFarmViewDTORepository.save(myFarmViewDTO.get());
                return;
            }
        }
        myFarmViewDTO.get().getDiaries().add(farmDiaryDTO);
        myFarmViewDTORepository.save(myFarmViewDTO.get());
    }

    @KafkaListener(
            topics = "userdb-follow",
            groupId = "MyFarmViewAddingFollowData"
    )
    public void MyFarmViewAddingFollowDataListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {});
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        Optional<MyFarmViewDTO> senderFarmViewDTO = myFarmViewDTORepository.findByUserId((String) payload.get("sender_id"));
        senderFarmViewDTO.get().addFollowingCount(1);
        myFarmViewDTORepository.save(senderFarmViewDTO.get());
        Optional<MyFarmViewDTO> receiverFarmViewDTO = myFarmViewDTORepository.findByUserId((String) payload.get("receiver_id"));
        receiverFarmViewDTO.get().addFollowerCount(1);
        myFarmViewDTORepository.save(receiverFarmViewDTO.get());
    }
}
