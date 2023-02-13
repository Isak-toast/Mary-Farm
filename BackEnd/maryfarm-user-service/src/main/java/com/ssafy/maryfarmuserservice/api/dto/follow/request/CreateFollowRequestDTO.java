package com.ssafy.maryfarmuserservice.api.dto.follow.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFollowRequestDTO {
    private String senderId;
    private String receiverId;
}
