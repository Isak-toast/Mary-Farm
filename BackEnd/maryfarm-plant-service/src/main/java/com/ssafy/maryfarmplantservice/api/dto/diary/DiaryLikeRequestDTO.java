package com.ssafy.maryfarmplantservice.api.dto.diary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryLikeRequestDTO {
    //나의 아이디
    private String diaryId;
    private String userId;
    private String userName;
}
