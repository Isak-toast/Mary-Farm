package com.ssafy.maryfarmcalendarservice.kafka.producer.calendar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.maryfarmcalendarservice.domain.calendar.Calendar;
import com.ssafy.maryfarmcalendarservice.kafka.dto.Status;
import com.ssafy.maryfarmcalendarservice.kafka.dto.calendar.KafkaCalendarDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class CalendarProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, Calendar calendar, Status status) {
//        KafkaCalendarDTO kafkaCalendarDTO = new KafkaCalendarDTO(status, calendar);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(calendar);
        } catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }
        kafkaTemplate.send(topic, jsonInString);
    }
}
