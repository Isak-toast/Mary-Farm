package com.ssafy.maryfarmconsumer.query_dto.FirstHomeView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.maryfarmconsumer.repository.FirstHomeView.HomeDiaryImageDTORepository;
import com.ssafy.maryfarmconsumer.repository.FirstHomeView.FirstHomeViewDTORepository;
import com.ssafy.maryfarmconsumer.repository.FirstHomeView.HomeFollowerImageDTORepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
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
        /*
            팔로우 요청이 생겼을 시, 보낸 사람의 FirstHomeView에 받는 사람의 FollowerImageDTO를 추가함.
         */
        Optional<FirstHomeViewDTO> dto = firstHomeViewDTORepository.findByUserId((String) payload.get("sender_id"));
        Optional<HomeFollowerImageDTO> receiverHomeFollowerImageDto = homeFollowerImageDTORepository.findByUserId((String) payload.get("receiver_id"));
        if(receiverHomeFollowerImageDto.isPresent()) {
            dto.get().getFollowers().add(receiverHomeFollowerImageDto.get());
            firstHomeViewDTORepository.save(dto.get());
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
            특정 유저의 HomeFollowerImage 최초 생성
         */
        Optional<HomeFollowerImageDTO> userDto = homeFollowerImageDTORepository.findByUserId((String) payload.get("user_id"));
        if(!userDto.isPresent()) {
            HomeFollowerImageDTO homeFollowerImageDTO = new HomeFollowerImageDTO(payload);
            homeFollowerImageDTORepository.save(homeFollowerImageDTO);
        }
        /*
            유저가 프로필 이미지를 변경할 때마다 FirstHomeView에서 활용할 팔로워일지DTO를 갱신함.
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
            groupId = "HomeFollowerImage"
    )
    public void HomeFollowerImageListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {});
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        Optional<HomeFollowerImageDTO> dto = homeFollowerImageDTORepository.findByUserId((String) payload.get("user_id"));
        /*
            만약 유저가 일지를 생성하거나, 수정했다면 FollowerImageDTO 데이터를 최신 값으로 갱신시킴.
         */
        dto.get().setLatestDiaryImagePath((String) payload.get("image_path"));
        dto.get().setPlantId((String) payload.get("plant_id"));
        homeFollowerImageDTORepository.save(dto.get());
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
        /*
            유저가 새로운 일지를 쓸 때마다 FirstHomeView에서 활용할 추천일지DTO를 만듦.
            만약 일지를 수정했다면 해당 데이터를 최신 값으로 갱신시킴.
         */
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
    /*
        3분 주기로 전체 유저의 FirstHomeView를 최신상태로 갱신함.
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void update_view() {
        log.info("-----------update view run--------");
        List<FirstHomeViewDTO> all = firstHomeViewDTORepository.findAll();
        for(FirstHomeViewDTO f : all) {
            List<HomeFollowerImageDTO> followers = f.getFollowers();
            for(HomeFollowerImageDTO h : followers) {
                Optional<HomeFollowerImageDTO> byUserId = homeFollowerImageDTORepository.findByUserId(h.getUserId());
                h.setProfilePath(byUserId.get().getProfilePath());
                h.setLatestDiaryImagePath(byUserId.get().getLatestDiaryImagePath());
                h.setPlantId(byUserId.get().getPlantId());
            }
            List<HomeDiaryImageDTO> latestDiaries = homeDiaryImageDTORepository.findAll();
            f.setDiaries(latestDiaries);
        }
        firstHomeViewDTORepository.saveAll(all);
    }
}
