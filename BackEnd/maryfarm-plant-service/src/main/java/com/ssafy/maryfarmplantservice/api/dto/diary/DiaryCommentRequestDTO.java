package com.ssafy.maryfarmplantservice.api.dto.diary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryCommentRequestDTO {
    private String diaryId;
    private String userId;
    private String content;
}
