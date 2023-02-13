package com.ssafy.maryfarmchatservice.api.dto.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestDTO {
    private String roomId;
    private String userId;
    private String userName;
    private String profilePath;
    private String content;
}
