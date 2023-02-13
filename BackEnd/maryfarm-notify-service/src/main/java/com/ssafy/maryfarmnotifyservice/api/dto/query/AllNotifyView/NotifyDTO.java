package com.ssafy.maryfarmnotifyservice.api.dto.query.AllNotifyView;

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

}
