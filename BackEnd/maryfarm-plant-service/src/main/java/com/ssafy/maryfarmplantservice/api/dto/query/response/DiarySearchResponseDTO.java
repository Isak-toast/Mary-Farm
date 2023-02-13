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
public class DiarySearchResponseDTO {
    private PlantResponseDTO plant;
    private String content;
    private Integer likes;
    private String imagepath;
    private List<String> taglist = new ArrayList<>();

    private List<GroupedDiaryForSearchDTO> diarygroup = new ArrayList<>();

    public static DiarySearchResponseDTO of(Diary diary, UserResponseDTO userDto, List<Diary> diarygroup) {
        DiarySearchResponseDTO dto = new DiarySearchResponseDTO();
        dto.plant = PlantResponseDTO.of(diary.getPlant(),userDto);
        dto.content = diary.getContent();
        dto.likes = diary.getLikes();
        dto.imagepath = diary.getImagePath();
        for(Tag t : diary.getTags()) {
            dto.taglist.add(t.getName());
        }
        for(Diary d : diarygroup) {
            dto.diarygroup.add(GroupedDiaryForSearchDTO.of(d));
        }
        return dto;
    }
}
