package com.ssafy.maryfarmconsumer.query_dto.RoomListView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.maryfarmconsumer.query_dto.DetailDiariesPerPlantView.DetailDiariesPerPlantDTO;
import com.ssafy.maryfarmconsumer.query_dto.DetailDiariesPerPlantView.DetailDiaryDTO;
import com.ssafy.maryfarmconsumer.repository.RoomListView.RoomListDTORepository;
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
public class RoomListViewListener {

    private final RoomListDTORepository roomListDTORepository;

    @KafkaListener(
            topics = "userdb-user",
            groupId = "DetailDiariesPerPlant"
    )
    public void DetailDiariesPerPlantListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {
        });
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        Optional<RoomListDTO> dto = roomListDTORepository.findByUserId((String) payload.get("user_id"));
        if(!dto.isPresent()) {
            RoomListDTO roomListDTO = new RoomListDTO(payload);
            roomListDTORepository.save(roomListDTO);
        }
    }

    @KafkaListener(
            topics = "chatdb-room",
            groupId = "RoomListView"
    )
    public void RoomListViewListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {
        });
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        /*
            sender 처리
         */
        RoomDTO receiverRoomDto = new RoomDTO(payload, "receiver");
        Optional<RoomListDTO> senderRoomListDto = roomListDTORepository.findByUserId((String) payload.get("sender_id"));
        senderRoomListDto.get().getRooms().add(receiverRoomDto);
        roomListDTORepository.save(senderRoomListDto.get());
        /*
            receiver 처리
         */
        RoomDTO senderRoomDto = new RoomDTO(payload, "sender");
        Optional<RoomListDTO> receiverRoomListDto = roomListDTORepository.findByUserId((String) payload.get("receiver_id"));
        receiverRoomListDto.get().getRooms().add(senderRoomDto);
        roomListDTORepository.save(receiverRoomListDto.get());
    }
    @KafkaListener(
            topics = "chatdb-message",
            groupId = "latestMassageToRoomData"
    )
    public void latestMassageToRoomDataListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {
        });
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        /*
            sender의 RoomList에 최근 메시지 추가
         */
        Optional<RoomListDTO> roomDto = roomListDTORepository.findByUserId((String) payload.get("user_id"));
        List<RoomDTO> myRooms = roomDto.get().getRooms();
        String opponentId = null;
        for(RoomDTO r : myRooms) {
            if(r.getRoomId().equals((String) payload.get("room_id"))) {
                r.setLatestMessage((String) payload.get("content"));
                LocalDateTime timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli((Long) payload.get("timestamp")), TimeZone.getDefault().toZoneId());
                r.setLatestTimestamp(timestamp);
                opponentId = r.getOpponentId();
            }
        }
        roomListDTORepository.save(roomDto.get());
        Optional<RoomListDTO> oppoRoomDto = roomListDTORepository.findByUserId(opponentId);
        List<RoomDTO> oppoRooms = oppoRoomDto.get().getRooms();
        for(RoomDTO r : oppoRooms) {
            if(r.getRoomId().equals((String) payload.get("room_id"))) {
                r.setLatestMessage((String) payload.get("content"));
                LocalDateTime timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli((Long) payload.get("timestamp")), TimeZone.getDefault().toZoneId());
                r.setLatestTimestamp(timestamp);
            }
        }
        roomListDTORepository.save(oppoRoomDto.get());
    }
}
