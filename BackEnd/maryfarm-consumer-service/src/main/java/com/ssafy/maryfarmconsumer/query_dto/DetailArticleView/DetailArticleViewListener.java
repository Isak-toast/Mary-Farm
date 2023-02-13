package com.ssafy.maryfarmconsumer.query_dto.DetailArticleView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.maryfarmconsumer.repository.DetailArticleView.DetailArticleDTORepository;
import com.ssafy.maryfarmconsumer.repository.TotalArticleView.SearchArticleByTypeDTORepository;
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
public class DetailArticleViewListener {

    private final DetailArticleDTORepository detailArticleDTORepository;

    @KafkaListener(
            topics = "boarddb-article",
            groupId = "detailArticle"
    )
    public void detailArticleListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {
        });
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        DetailArticleDTO detailArticleDTO = new DetailArticleDTO(payload);
        detailArticleDTORepository.save(detailArticleDTO);
    }

    @KafkaListener(
            topics = "boarddb-article_comment",
            groupId = "articleCommentToDetailArticle"
    )
    public void articleCommentListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {
        });
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        Optional<DetailArticleDTO> dto = detailArticleDTORepository.findByArticleId((String) payload.get("article_id"));
        ArticleCommentDTO articleCommentDTO = new ArticleCommentDTO(payload);
        dto.get().getComments().add(articleCommentDTO);
        detailArticleDTORepository.save(dto.get());
    }
}
