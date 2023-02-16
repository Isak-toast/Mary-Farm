package com.ssafy.maryfarmnotifyservice.api.controller.notify;

import com.ssafy.maryfarmnotifyservice.api.dto.query.AllNotifyView.AllNotifyDTO;
import com.ssafy.maryfarmnotifyservice.mongo_repository.AllNotifyView.AllNotifyDTORepository;
import com.ssafy.maryfarmnotifyservice.service.NotifyCService;
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

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class NotifyQuery {
    private final NotifyCService notifyCService;
    private final AllNotifyDTORepository allNotifyDTORepository;

    @Operation(summary = "알림 리스트 화면 조회", description = "특정 유저의 알림 리스트 화면을 조회합니다.", tags = { "Notify Query" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = AllNotifyDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/notify/search/{userId}")
    public ResponseEntity<?> saveNotify(@PathVariable("userId") String userId) {
        Optional<AllNotifyDTO> resultDto = allNotifyDTORepository.findByUserId(userId);
        return ResponseEntity.ok(resultDto.get());
    }
}
