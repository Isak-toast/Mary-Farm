package com.ssafy.maryfarmchatservice.api.dto.query.RoomListView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "MyRoomList")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomListDTO {
    @Id
    private String id;
    private String userId;
    private List<RoomDTO> rooms = new ArrayList<>();
}
