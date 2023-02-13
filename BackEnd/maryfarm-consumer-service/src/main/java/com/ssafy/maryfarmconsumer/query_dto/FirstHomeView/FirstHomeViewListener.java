package com.ssafy.maryfarmconsumer.query_dto.FirstHomeView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.maryfarmconsumer.repository.FirstHomeView.HomeDiaryImageDTORepository;
import com.ssafy.maryfarmconsumer.repository.FirstHomeView.FirstHomeViewDTORepository;
import com.ssafy.maryfarmconsumer.repository.FirstHomeView.HomeFollowerImageDTORepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class FirstHomeViewListener {
    private final FirstHomeViewDTORepository firstHomeViewDTORepository;
    private final HomeDiaryImageDTORepository homeDiaryImageDTORepository;
    private final HomeFollowerImageDTORepository homeFollowerImageDTORepository;
    @KafkaListener(
            topics = "userdb-follow",
            groupId = "AddingFirstHomeViewFollower"
    )
    public void AddingFirstHomeViewFollowerListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {});
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        Optional<FirstHomeViewDTO> dto = firstHomeViewDTORepository.findByUserId((String) payload.get("sender_id"));
        HomeFollowerImageDTO homeFollowerImageDTO = new HomeFollowerImageDTO(payload);
        Optional<HomeDiaryImageDTO> followerDiaryImageDto = homeDiaryImageDTORepository.findTopByUserIdOrderByCreatedDateDesc((String) payload.get("receiver_id"));
        homeFollowerImageDTO.setLatestDiaryImagePath(followerDiaryImageDto.get().getLatestDiaryImagePath());
        dto.get().getFollowers().add(homeFollowerImageDTO);
        firstHomeViewDTORepository.save(dto.get());
    }

    @KafkaListener(
            topics = "plantdb-diary",
            groupId = "HomeFollowerImage"
    )
    public void HomeFollowerImageListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {});
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        Optional<HomeFollowerImageDTO> dto = homeFollowerImageDTORepository.findByUserId((String) payload.get("user_id"));
        if(dto.isPresent()) {
            HomeFollowerImageDTO homeFollowerImageDTO = new HomeFollowerImageDTO(payload);
            homeFollowerImageDTORepository.save(homeFollowerImageDTO);
        } else {
            dto.get().setLatestDiaryImagePath((String) payload.get("image_path"));
            homeFollowerImageDTORepository.save(dto.get());
        }
    }

    @KafkaListener(
            topics = "userdb-user",
            groupId = "FirstHomeView"
    )
    public void FirstHomeViewListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {});
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        /*
            특정 유저의 FirstHomeView 최초 생성
         */
        Optional<FirstHomeViewDTO> myHomeViewDTO = firstHomeViewDTORepository.findByUserId((String) payload.get("user_id"));
        if(!myHomeViewDTO.isPresent()) {
            FirstHomeViewDTO firstHomeViewDTO = new FirstHomeViewDTO();
            firstHomeViewDTO.setUserId((String) payload.get("user_id"));
            firstHomeViewDTO.setLatestSystemNotify("처음 방문하셨군요!");
            List<HomeDiaryImageDTO> allHomeDiaryImageDto = homeDiaryImageDTORepository.findAll();
            firstHomeViewDTO.getDiaries().addAll(allHomeDiaryImageDto);
            firstHomeViewDTORepository.save(firstHomeViewDTO);
        }
        /*
            특정 유저의 프로필 이미지 변경시, 스토리 화면에도 적용
         */
        Optional<HomeFollowerImageDTO> homeFollowerImageDto = homeFollowerImageDTORepository.findByUserId((String) payload.get("user_id"));
        if(homeFollowerImageDto.isPresent()) {
            homeFollowerImageDto.get().setUserName((String) payload.get("user_name"));
            homeFollowerImageDto.get().setProfilePath((String) payload.get("profile_path"));
            homeFollowerImageDTORepository.save(homeFollowerImageDto.get());
        }
    }

    @KafkaListener(
            topics = "plantdb-diary",
            groupId = "HomeDiaryImage"
    )
    public void HomeDiaryImageListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {});
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        Optional<HomeDiaryImageDTO> dto = homeDiaryImageDTORepository.findByPlantId((String) payload.get("plant_id"));
        if(dto.isPresent()) {
            String prevDiaryImagePath = dto.get().getLatestDiaryImagePath();
            dto.get().setLatestDiaryImagePath((String) payload.get("image_path"));
            dto.get().setContent((String) payload.get("content"));
            LocalDateTime createdDate = LocalDateTime.ofInstant(Instant.ofEpochMilli((Long) payload.get("created_date")), TimeZone.getDefault().toZoneId());
            dto.get().setCreatedDate(createdDate);
            dto.get().getOtherDiaryImagePath().add(prevDiaryImagePath);
            homeDiaryImageDTORepository.save(dto.get());
        } else {
            HomeDiaryImageDTO homeDiaryImageDTO = new HomeDiaryImageDTO(payload);
            homeDiaryImageDTORepository.save(homeDiaryImageDTO);
        }
    }
}
