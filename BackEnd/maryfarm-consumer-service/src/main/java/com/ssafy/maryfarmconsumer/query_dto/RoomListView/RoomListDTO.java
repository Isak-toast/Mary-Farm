package com.ssafy.maryfarmconsumer.query_dto.RoomListView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Document(collection = "MyRoomList")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomListDTO {
    @Id
    private String id;
    private String userId;
    private List<RoomDTO> rooms = new ArrayList<>();

    public RoomListDTO(Map<Object, Object> payload) {
        this.userId = (String)payload.get("user_id");
    }
}
