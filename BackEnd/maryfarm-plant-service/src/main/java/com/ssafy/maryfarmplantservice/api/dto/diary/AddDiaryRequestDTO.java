package com.ssafy.maryfarmplantservice.api.dto.diary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDiaryRequestDTO {
    private String plantId;
    private String content;
    private String userId;
    private String userName;
    private String profilePath;
}
