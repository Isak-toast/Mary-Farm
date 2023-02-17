package com.numberONE.maryfarm.Retrofit.dto.RoomListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoomListDTO {
    private String id;
    private String userId;
    private List<RoomDTO> rooms = new ArrayList<>();

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public RoomListDTO(Map<Object, Object> payload) {
        this.userId = (String)payload.get("user_id");
    }
}
