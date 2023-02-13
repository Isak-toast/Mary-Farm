package com.ssafy.maryfarmplantservice.api.dto.query.response;

import com.ssafy.maryfarmplantservice.client.dto.user.UserResponseDTO;
import com.ssafy.maryfarmplantservice.domain.diary.Diary;
import com.ssafy.maryfarmplantservice.domain.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailDiaryResponseDTO {
    private String diaryId;
    private PlantResponseDTO plant;
    private String content;
    private Integer likes;
    private String imagepath;
    private List<TagResponseDTO> taglist = new ArrayList<>();

    public static DetailDiaryResponseDTO of(Diary diary, UserResponseDTO userDto) {
        DetailDiaryResponseDTO dto = new DetailDiaryResponseDTO();
        dto.diaryId = diary.getId();
        dto.plant = PlantResponseDTO.of(diary.getPlant(),userDto);
        dto.content = diary.getContent();
        dto.likes = diary.getLikes();
        dto.imagepath = diary.getImagePath();
        for(Tag t : diary.getTags()) {
            dto.taglist.add(TagResponseDTO.from(t));
        }
        return dto;
    }
}
