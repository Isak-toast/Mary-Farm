package com.ssafy.maryfarmuserservice.kafka.dto.user;

import com.ssafy.maryfarmuserservice.domain.user.User;
import com.ssafy.maryfarmuserservice.kafka.dto.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaUserDTO {
    private Status status;
    private User user;
}
