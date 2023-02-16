package com.ssafy.maryfarmchatservice.api.dto.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageRequestDTO {
    private String roomId;
    private String userId;
    private String userName;
    private String profilePath;
    private String content;
}
