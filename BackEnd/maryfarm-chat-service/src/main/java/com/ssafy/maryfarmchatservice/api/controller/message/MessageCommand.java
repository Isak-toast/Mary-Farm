package com.ssafy.maryfarmchatservice.api.controller.message;

import com.ssafy.maryfarmchatservice.api.dto.message.MessageRequestDTO;
import com.ssafy.maryfarmchatservice.client.dto.UserResponseDTO;
import com.ssafy.maryfarmchatservice.client.service.user.UserServiceClient;
import com.ssafy.maryfarmchatservice.domain.chat.Message;
import com.ssafy.maryfarmchatservice.kafka.producer.message.MessageProducer;
import com.ssafy.maryfarmchatservice.service.MessageCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
@RequestMapping("/api")
public class MessageCommand {
    private final MessageProducer messageProducer;
    private final MessageCService messageCService;
    private final UserServiceClient userServiceClient;
    private final SimpMessagingTemplate template;
    @Operation(summary = "채팅 메시지 저장", description = "채팅 메시지를 저장합니다.", tags = { "Chat Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/message/send")
    public ResponseEntity<?> sendMessage(@RequestBody MessageRequestDTO dto) throws ExecutionException, InterruptedException {
        Message saveMessage = messageCService.saveMessage(dto.getRoomId(),dto.getUserId(),dto.getUserName(),dto.getProfilePath(),dto.getContent());
        System.out.println("sending via kafka listener..");
        System.out.println("/topic/group/" + saveMessage.getRoom().getId());
        template.convertAndSend("/topic/group/" + saveMessage.getRoom().getId(), saveMessage);
        return ResponseEntity.ok(saveMessage.getId());
    }

}
