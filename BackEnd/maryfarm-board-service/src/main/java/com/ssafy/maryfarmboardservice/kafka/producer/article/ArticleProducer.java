package com.ssafy.maryfarmboardservice.kafka.producer.article;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.maryfarmboardservice.domain.board.Article;
import com.ssafy.maryfarmboardservice.kafka.dto.Status;
import com.ssafy.maryfarmboardservice.kafka.dto.article.KafkaArticleDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, Article article, Status status) {
//        KafkaArticleDTO kafkaArticleDTO = new KafkaArticleDTO(status, article);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(article);
        } catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }
        kafkaTemplate.send(topic, jsonInString);
    }
}
