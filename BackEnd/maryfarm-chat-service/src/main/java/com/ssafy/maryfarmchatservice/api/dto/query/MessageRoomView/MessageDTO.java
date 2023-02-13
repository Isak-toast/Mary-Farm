package com.ssafy.maryfarmchatservice.api.dto.query.MessageRoomView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TimeZone;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private String messageId;
    private String userId;
    private String userName;
    private String profilePath;
    private String content;
    private LocalDateTime timestamp;
}
