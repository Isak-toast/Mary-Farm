package com.ssafy.maryfarmconsumer.query_dto.TotalArticleView;

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
public class TotalArticleViewListener {
    private final DetailArticleDTORepository detailArticleDTORepository;
    private final SearchArticleByTypeDTORepository searchArticleByTypeDTORepository;

    @KafkaListener(
            topics = "boarddb-article",
            groupId = "articleGroupByType"
    )
    public void articleGroupByTypeListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {});
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        ArticleDTO articleDTO = new ArticleDTO(payload);
        String type = (String) payload.get("type");
        Optional<SearchArticleByTypeDTO> dto = searchArticleByTypeDTORepository.findByType(type);
        if(!dto.isPresent()) {
            SearchArticleByTypeDTO searchArticleByTypeDTO = new SearchArticleByTypeDTO();
            searchArticleByTypeDTO.setType(type);
            searchArticleByTypeDTO.getArticles().add(articleDTO);
            searchArticleByTypeDTORepository.save(searchArticleByTypeDTO);
        } else {
            dto.get().getArticles().add(articleDTO);
            searchArticleByTypeDTORepository.save(dto.get());
        }
    }
}
