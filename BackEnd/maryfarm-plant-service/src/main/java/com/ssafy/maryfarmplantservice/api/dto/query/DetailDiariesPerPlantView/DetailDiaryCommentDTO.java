package com.ssafy.maryfarmplantservice.api.dto.query.DetailDiariesPerPlantView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailDiaryCommentDTO {
    private String profilePath;
    private String userName;
    private String content;
    private Integer likes;
}
