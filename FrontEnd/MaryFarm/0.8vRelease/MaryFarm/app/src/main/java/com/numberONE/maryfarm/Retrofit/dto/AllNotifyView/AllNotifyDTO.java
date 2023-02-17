package com.numberONE.maryfarm.Retrofit.dto.AllNotifyView;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AllNotifyDTO {
    private String id;
    private String userId;
    private List<NotifyDTO> notifies = new ArrayList<>();

    public AllNotifyDTO(Map<Object, Object> payload) {
        this.userId = (String) payload.get("user_id");
    }
}
