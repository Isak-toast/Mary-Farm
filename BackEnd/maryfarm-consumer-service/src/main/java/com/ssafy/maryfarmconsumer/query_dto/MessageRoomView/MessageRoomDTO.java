package com.ssafy.maryfarmconsumer.query_dto.MessageRoomView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "MessagesPerRoom")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRoomDTO {
    @Id
    private String id;
    private String roomId;
    private List<MessageDTO> messages = new ArrayList<>();
}
