package com.ssafy.maryfarmuserservice.api.controller.follow;

import com.ssafy.maryfarmuserservice.api.dto.follow.request.CreateFollowRequestDTO;
import com.ssafy.maryfarmuserservice.api.dto.follow.response.FollowResponseDTO;
import com.ssafy.maryfarmuserservice.client.service.notify.NotifyServiceClient;
import com.ssafy.maryfarmuserservice.domain.user.Follow;
import com.ssafy.maryfarmuserservice.kafka.producer.follow.FollowProducer;
import com.ssafy.maryfarmuserservice.service.FollowCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class FollowCommand {
    private final FollowCService followCService;
    private final FollowProducer followProducer;
    private final NotifyServiceClient notifyServiceClient;
    @Operation(summary = "팔로우 요청", description = "다른 유저를 팔로우합니다.", tags = { "Follow Command" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = FollowResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/follow/following")
    public ResponseEntity<?> FollowUser(@RequestBody CreateFollowRequestDTO dto) {
        followCService.saveFollow(dto.getSenderId(),dto.getReceiverId());
        return ResponseEntity.ok(1);
    }
}
