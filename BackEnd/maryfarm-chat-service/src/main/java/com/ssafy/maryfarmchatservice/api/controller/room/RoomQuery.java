package com.ssafy.maryfarmchatservice.api.controller.room;

import com.ssafy.maryfarmchatservice.api.dto.query.RoomListView.RoomListDTO;
import com.ssafy.maryfarmchatservice.client.service.user.UserServiceClient;
import com.ssafy.maryfarmchatservice.mongo_repository.RoomListView.RoomListDTORepository;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class RoomQuery {
    private final RoomCService roomCService;
    private final MessageCService messageCService;
    private final UserServiceClient userServiceClient;
    private final RoomListDTORepository roomListDTORepository;
    @Operation(summary = "채팅방 리스트 화면 조회", description = "특정 유저의 채팅방 리스트 화면을 조회합니다.", tags = { "Room Query" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = RoomListDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/room/search/{userId}")
    public ResponseEntity<?> searchMyRoom(@PathVariable("userId") String userId) {
        Optional<RoomListDTO> resultDto = roomListDTORepository.findByUserId(userId);
        return ResponseEntity.ok(resultDto.get());
    }
}
