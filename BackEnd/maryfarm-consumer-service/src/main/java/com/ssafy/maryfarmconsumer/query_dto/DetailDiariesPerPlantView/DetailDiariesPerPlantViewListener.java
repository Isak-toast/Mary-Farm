package com.ssafy.maryfarmconsumer.query_dto.DetailDiariesPerPlantView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.maryfarmconsumer.query_dto.DetailArticleView.DetailArticleDTO;
import com.ssafy.maryfarmconsumer.repository.DetailDiariesPerPlantView.DetailDiariesPerPlantDTORepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class DetailDiariesPerPlantViewListener {
    private final DetailDiariesPerPlantDTORepository detailDiariesPerPlantDTORepository;

    @KafkaListener(
            topics = "plantdb-diary",
            groupId = "DetailDiariesaddingLike"
    )
    public void DetailDiariesaddingLikeListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {
        });
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        DetailDiaryDTO detailDiaryDTO = new DetailDiaryDTO(payload);
        Optional<DetailDiariesPerPlantDTO> dto = detailDiariesPerPlantDTORepository.findByPlantId((String) payload.get("plant_id"));
        /*
            새로운 일지 추가시, 해당 일지가 특정 작물의 최초 작성 일지라면, DetailDiariesPerPlantDTO를 추가함.
            해당 일지가 특정 작물의 추가 작성 일지라면, 해당 작물의 DetailDiariesPerPlantDTO의 list에 추가함.
            특정 일지의 수정 요청(Like 갱신, 내용 수정)이라면, DetailDiariesPerPlantDTO내의 list중 해당 내용을 바꿈.
         */
        if(!dto.isPresent()) {
            DetailDiariesPerPlantDTO detailDiariesPerPlantDTO = new DetailDiariesPerPlantDTO(payload);
            detailDiariesPerPlantDTO.getDiaries().add(detailDiaryDTO);
            detailDiariesPerPlantDTORepository.save(detailDiariesPerPlantDTO);
        } else {
            String diaryId = (String) payload.get("diary_id");
            List<DetailDiaryDTO> list = dto.get().getDiaries();
            for(DetailDiaryDTO d : list) {
                if(d.getDiaryId().equals(diaryId)) {
                    d.update(payload);
                    detailDiariesPerPlantDTORepository.save(dto.get());
                    return;
                }
            }
            dto.get().getDiaries().add(detailDiaryDTO);
            detailDiariesPerPlantDTORepository.save(dto.get());
        }
    }

    @KafkaListener(
            topics = "plantdb-diary_comment",
            groupId = "DetailDiariesaddingComment"
    )
    public void DetailDiariesaddingCommentListen(String message) throws JsonProcessingException {
        log.info("Kafka Message: ->" + message);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {
        });
        Map<Object, Object> payload = (Map<Object, Object>) map.get("payload");
        DetailDiaryCommentDTO detailDiaryCommentDTO = new DetailDiaryCommentDTO(payload);
        Optional<DetailDiariesPerPlantDTO> dto = detailDiariesPerPlantDTORepository.findByPlantId((String) payload.get("plant_id"));
        /*
            특정 일지에 댓글이 추가되었을 때, 해당 일지 작물의 DetailDiariesPerPlantDTO를
            가져온 뒤, 내부의 list에서 해당 일지를 찾아 DetailDiaryCommentDTO를 추가해줌.
         */
        String diaryId = (String) payload.get("diary_id");
        List<DetailDiaryDTO> diaries = dto.get().getDiaries();
        for(DetailDiaryDTO d : diaries) {
            if(d.getDiaryId().equals(diaryId)) {
                d.getComments().add(detailDiaryCommentDTO);
                detailDiariesPerPlantDTORepository.save(dto.get());
                return;
            }
        }
    }
}
