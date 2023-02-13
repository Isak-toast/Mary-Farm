package com.ssafy.maryfarmnotifyservice.api.controller.notify;

import com.ssafy.maryfarmnotifyservice.api.dto.notify.CreateNotifyRequestDTO;
import com.ssafy.maryfarmnotifyservice.api.dto.query.AllNotifyView.AllNotifyDTO;
import com.ssafy.maryfarmnotifyservice.domain.notify.Notify;
import com.ssafy.maryfarmnotifyservice.kafka.producer.notify.NotifyProducer;
import com.ssafy.maryfarmnotifyservice.service.NotifyCService;
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
public class NotifyCommand {
    private final NotifyCService notifyCService;

    @Operation(summary = "알림 저장", description = "특정 알림을 저장합니다.", tags = { "Notify Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/notify/save")
    public ResponseEntity<?> saveNotify(@RequestBody CreateNotifyRequestDTO dto) {
        Notify saveNotify = notifyCService.saveNotify(dto.getType(), dto.getContent(), dto.getUserId());
        return ResponseEntity.ok(saveNotify.getId());
    }
}
