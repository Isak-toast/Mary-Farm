package com.ssafy.maryfarmchatservice.api.dto.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitRoomRequestDTO {
    private String senderId;
    private String senderName;
    private String senderProfilePath;
    private String receiverId;
    private String receiverName;
    private String receiverProfilePath;
}
