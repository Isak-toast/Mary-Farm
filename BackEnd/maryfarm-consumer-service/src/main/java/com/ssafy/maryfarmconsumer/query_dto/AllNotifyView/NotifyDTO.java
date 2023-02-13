package com.ssafy.maryfarmconsumer.query_dto.AllNotifyView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotifyDTO {
    private String notifyId;
    private String type;
    private String content;

    public NotifyDTO(Map<Object, Object> payload) {
        this.notifyId = (String) payload.get("notify_id");
        this.type = (String) payload.get("type");
        this.content = (String) payload.get("content");
    }
}
