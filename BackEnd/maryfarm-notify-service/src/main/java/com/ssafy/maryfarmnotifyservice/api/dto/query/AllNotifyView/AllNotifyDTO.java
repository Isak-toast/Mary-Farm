package com.ssafy.maryfarmnotifyservice.api.dto.query.AllNotifyView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "AllNotifyView")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllNotifyDTO {
    @Id
    private String id;
    @Indexed
    private String userId;
    private List<NotifyDTO> notifies = new ArrayList<>();
}
