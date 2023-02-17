package com.numberONE.maryfarm.Retrofit.dto.AllNotifyView;


import java.util.Map;


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
