package com.ssafy.maryfarmchatservice.api.dto.query.RoomListView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private String roomId;
    private String opponentId;
    private String opponentName;
    private String opponentProfilePath;
    private String latestMessage;
    private LocalDateTime latestTimestamp;

}
