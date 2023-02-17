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
public class DiaryResponseDTO {
    private String diaryId;
    private String plantId;
    private String userId;
    private String name;
    private String title;
    private String content;
    private String imagepath;

    public static DiaryResponseDTO of(Diary diary) {
        DiaryResponseDTO dto = new DiaryResponseDTO();
        dto.diaryId = diary.getId();
        dto.plantId = diary.getPlant().getId();
        dto.userId = diary.getUserId();
        dto.name = diary.getPlant().getName();
        dto.title = diary.getPlant().getTitle();
        dto.content = diary.getContent();
        dto.imagepath = diary.getImagePath();
        return dto;
    }
}
