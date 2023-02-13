package com.ssafy.maryfarmcalendarservice.api.controller.calendar;

import com.ssafy.maryfarmcalendarservice.api.dto.calendar.SearchCalendarByDayRequestDTO;
import com.ssafy.maryfarmcalendarservice.api.dto.query.CalendarPerDayView.CalendarPerDayDTO;
import com.ssafy.maryfarmcalendarservice.client.service.plant.PlantServiceClient;
import com.ssafy.maryfarmcalendarservice.mongo_repository.CalendarPerDayView.CalendarPerDayDTORepository;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class CalendarQuery {
    private final PlantServiceClient plantServiceClient;
    private final CalendarPerDayDTORepository calendarPerDayDTORepository;
    @Operation(summary = "특정 년월일의 달력 정보 조회", description = "특정 년월일에 해당하는 자신의 달력 정보를 조회합니다.", tags = { "Calendar Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = CalendarPerDayDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/calendar/day/search")
    public ResponseEntity<?> SearchCalendarByDay(@RequestBody SearchCalendarByDayRequestDTO dto) {
        List<CalendarPerDayDTO> resultDtos = calendarPerDayDTORepository.findByUserIdAndYearAndMonthAndDay(dto.getUserId(), dto.getYear(),
                dto.getMonth(), dto.getDay());
        return ResponseEntity.ok(resultDtos);

    }
}
