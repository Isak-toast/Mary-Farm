package com.ssafy.maryfarmconsumer.query_dto.AllNotifyView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Document(collection = "AllNotifyView")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllNotifyDTO {
    @Id
    private String id;
    private String userId;
    private List<NotifyDTO> notifies = new ArrayList<>();

    public AllNotifyDTO(Map<Object, Object> payload) {
        this.userId = (String) payload.get("user_id");
    }
}
