package com.ssafy.maryfarmplantservice.api.dto.diary;

import com.ssafy.maryfarmplantservice.domain.diary.DiaryComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryCommentResponseDTO {
    private String diaryCommentId;
    private String userId;
    private String content;
    private Integer likes;

    public static DiaryCommentResponseDTO of(DiaryComment diaryComment) {
        DiaryCommentResponseDTO dto = new DiaryCommentResponseDTO();
        dto.diaryCommentId = diaryComment.getId();
        dto.userId = diaryComment.getUserId();
        dto.content = diaryComment.getContent();
        dto.likes = diaryComment.getLikes();
        return dto;
    }
}
