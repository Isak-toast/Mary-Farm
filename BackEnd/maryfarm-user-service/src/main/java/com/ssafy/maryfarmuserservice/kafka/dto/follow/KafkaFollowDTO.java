package com.ssafy.maryfarmuserservice.kafka.dto.follow;

import com.ssafy.maryfarmuserservice.domain.user.Follow;
import com.ssafy.maryfarmuserservice.kafka.dto.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaFollowDTO {
    private Status status;
    private Follow follow;
}
