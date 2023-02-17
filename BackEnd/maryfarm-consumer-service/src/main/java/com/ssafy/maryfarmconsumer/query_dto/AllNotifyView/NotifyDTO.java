package com.ssafy.maryfarmconsumer.query_dto.AllNotifyView;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotifyDTO {
    private String notifyId;
    private String type;
    private String content;
    private String followerId;
    private String plantId;
    private String diaryId;

    public static NotifyDTO makeFollowerNotifyDTO(Map<Object, Object> payload) {
        NotifyDTO dto = new NotifyDTO();
        dto.notifyId = (String) payload.get("notify_id");
        dto.type = (String) payload.get("type");
        dto.content = (String) payload.get("content");
        dto.followerId = (String) payload.get("follower_id");
        return dto;
    }

    public static NotifyDTO makeFollowerDiaryNotifyDTO(Map<Object, Object> payload) {
        NotifyDTO dto = new NotifyDTO();
        dto.notifyId = (String) payload.get("notify_id");
        dto.type = (String) payload.get("type");
        dto.content = (String) payload.get("content");
        dto.plantId = (String) payload.get("plant_id");
        dto.diaryId = (String) payload.get("diary_id");
        return dto;
    }

    public static NotifyDTO makeDiaryLikeyNotifyDTO(Map<Object, Object> payload) {
        NotifyDTO dto = new NotifyDTO();
        dto.notifyId = (String) payload.get("notify_id");
        dto.type = (String) payload.get("type");
        dto.content = (String) payload.get("content");
        dto.plantId = (String) payload.get("plant_id");
        dto.diaryId = (String) payload.get("diary_id");
        return dto;
    }
}
