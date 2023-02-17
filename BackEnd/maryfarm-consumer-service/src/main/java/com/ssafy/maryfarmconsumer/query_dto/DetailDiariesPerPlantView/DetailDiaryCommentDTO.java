package com.ssafy.maryfarmconsumer.query_dto.DetailDiariesPerPlantView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailDiaryCommentDTO {
    private String commentId;
    private String profilePath;
    private String userName;
    private String content;
    private Integer likes;

    public DetailDiaryCommentDTO(Map<Object, Object> payload) {
        this.commentId = (String) payload.get("diary_comment_id");
        this.profilePath = (String) payload.get("profile_path");
        this.userName = (String) payload.get("user_name");
        this.content = (String) payload.get("content");
        this.likes = (Integer) payload.get("likes");
    }
}
