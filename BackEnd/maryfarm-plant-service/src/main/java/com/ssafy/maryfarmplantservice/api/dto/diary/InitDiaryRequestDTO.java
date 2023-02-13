package com.ssafy.maryfarmplantservice.api.dto.diary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitDiaryRequestDTO {
    private String userId;
    private String userName;
    private String profilePath;
    private String name;
    private String title;
    private String content;
}
