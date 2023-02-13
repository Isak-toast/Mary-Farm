package com.ssafy.maryfarmconsumer.query_dto.MessageRoomView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import javax.annotation.Nonnull;
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

    public MessageDTO(Map<Object, Object> payload) {
        this.messageId = (String) payload.get("message_id");
        this.userId = (String) payload.get("user_id");
        this.userName = (String) payload.get("user_name");
        this.profilePath = (String) payload.get("profile_path");
        this.content = (String) payload.get("content");
        LocalDateTime timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli((Long) payload.get("timestamp")), TimeZone.getDefault().toZoneId());
        this.timestamp = timestamp;
    }
}
