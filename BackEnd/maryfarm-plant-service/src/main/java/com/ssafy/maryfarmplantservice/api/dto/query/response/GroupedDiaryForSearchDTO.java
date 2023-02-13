package com.ssafy.maryfarmplantservice.api.dto.query.response;

import com.ssafy.maryfarmplantservice.domain.diary.Diary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupedDiaryForSearchDTO {
    private String diaryId;
    private String imagepath;

    public static GroupedDiaryForSearchDTO of(Diary diary) {
        GroupedDiaryForSearchDTO dto = new GroupedDiaryForSearchDTO();
        dto.diaryId = diary.getId();
        dto.imagepath = diary.getImagePath();
        return dto;
    }
}
