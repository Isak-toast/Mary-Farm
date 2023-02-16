package com.ssafy.maryfarmchatservice.api.controller.room;

import com.ssafy.maryfarmchatservice.api.dto.room.InitRoomRequestDTO;
import com.ssafy.maryfarmchatservice.client.dto.UserResponseDTO;
import com.ssafy.maryfarmchatservice.client.service.user.UserServiceClient;
import com.ssafy.maryfarmchatservice.domain.chat.Message;
import com.ssafy.maryfarmchatservice.domain.chat.Room;
import com.ssafy.maryfarmchatservice.kafka.producer.room.RoomProducer;
import com.ssafy.maryfarmchatservice.service.MessageCService;
import com.ssafy.maryfarmchatservice.service.RoomCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class RoomCommand {
    private final RoomCService roomCService;
    private final MessageCService messageCService;
    private final UserServiceClient userServiceClient;
    private final RoomProducer roomProducer;
    @Operation(summary = "채팅방 등록", description = "채팅방을 등록합니다.", tags = { "Room Command" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/room/init")
    public ResponseEntity<?> saveRoom(@RequestBody InitRoomRequestDTO dto) {
        Room room = roomCService.saveRoom(dto.getSenderId(), dto.getSenderName(), dto.getSenderProfilePath(),
                dto.getReceiverId(),dto.getReceiverName(),dto.getReceiverProfilePath());
        return ResponseEntity.ok(room.getId());
    }
}
