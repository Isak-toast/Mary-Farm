package com.ssafy.maryfarmcalendarservice.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.maryfarmcalendarservice.kafka.constants.KafkaConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageListener {
//    @KafkaListener(
//            topics = "calendar",
//            groupId = KafkaConstants.GROUP_ID
//    )
//    public void listen(String message) {
//        log.info("Kafka Message: ->" + message);
//
//        Map<Object, Object> map = new HashMap<>();
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {});
//            log.info(map.toString());
//        } catch (JsonProcessingException ex) {
//            ex.printStackTrace();
//        }
//
////        CatalogEntity entity = repository.findByProductId((String)map.get("productId"));
////        if (entity != null) {
////            entity.setStock(entity.getStock() - (Integer)map.get("qty"));
////            repository.save(entity);
////        }
//    }
}
