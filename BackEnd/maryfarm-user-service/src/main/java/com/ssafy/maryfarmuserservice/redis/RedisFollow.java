package com.ssafy.maryfarmuserservice.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("follow")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedisFollow {
    @Id
    private String id;
    private String senderId;
    private String receiverId;
}
