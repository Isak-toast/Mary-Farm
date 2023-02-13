package com.ssafy.maryfarmnotifyservice.kafka.dto.notify;

import com.ssafy.maryfarmnotifyservice.domain.notify.AlarmType;
import com.ssafy.maryfarmnotifyservice.domain.notify.Notify;
import com.ssafy.maryfarmnotifyservice.kafka.dto.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaNotifyDTO {
    private Status status;
    private Notify notify;
}
