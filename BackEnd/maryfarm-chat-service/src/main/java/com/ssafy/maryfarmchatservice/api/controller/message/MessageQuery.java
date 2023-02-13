package com.ssafy.maryfarmchatservice.api.controller.message;

import com.ssafy.maryfarmchatservice.api.dto.query.MessageRoomView.MessageRoomDTO;
import com.ssafy.maryfarmchatservice.client.service.user.UserServiceClient;
import com.ssafy.maryfarmchatservice.mongo_repository.MessageRoomView.MessageRoomDTORepository;
import com.ssafy.maryfarmchatservice.service.MessageCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
@RequestMapping("/api")
public class MessageQuery {
    private final MessageCService messageCService;
    private final UserServiceClient userServiceClient;
    private final MessageRoomDTORepository messageRoomDTORepository;
    @Operation(summary = "채팅 방 내 화면 불러오기", description = "특정 방의 채팅 메시지 전체를 불러옵니다.", tags = { "Chat Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = MessageRoomDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/message/search/{roomId}")
    public ResponseEntity<?> searchMessage(@PathVariable("roomId") String roomId) throws ExecutionException, InterruptedException {
        Optional<MessageRoomDTO> resultDto = messageRoomDTORepository.findByRoomId(roomId);
        return ResponseEntity.ok(resultDto.get());
    }
}
