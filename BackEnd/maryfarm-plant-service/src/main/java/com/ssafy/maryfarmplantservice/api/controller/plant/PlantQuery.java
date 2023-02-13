package com.ssafy.maryfarmplantservice.api.controller.plant;

import com.ssafy.maryfarmplantservice.api.dto.plant.MonthPlantSearchRequestDTO;
import com.ssafy.maryfarmplantservice.api.dto.query.response.PlantResponseDTO;
import com.ssafy.maryfarmplantservice.api.dto.query.response.PlantSearchByMonthResponseDTO;
import com.ssafy.maryfarmplantservice.client.dto.user.UserResponseDTO;
import com.ssafy.maryfarmplantservice.client.service.user.UserServiceClient;
import com.ssafy.maryfarmplantservice.domain.plant.Plant;
import com.ssafy.maryfarmplantservice.service.PlantCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class PlantQuery {
    private final PlantCService plantCService;
    private final UserServiceClient userServiceClient;
    @Operation(summary = "특정 작물 조회", description = "특정 작물의 정보를 조회합니다.", tags = { "Plant Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/plant/{plantId}")
    public ResponseEntity<?> searchPlant(@PathVariable("plantId") String plantId) throws IOException {
        Plant plant = plantCService.findPlant(plantId);
        UserResponseDTO userDto = userServiceClient.searchUser(plant.getUserId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Plant.class, PlantResponseDTO.class).addMappings(mapper -> {
            mapper.map(Plant::getId, PlantResponseDTO::setPlantId);
        });
        PlantResponseDTO dto = modelMapper.map(plant, PlantResponseDTO.class);
        dto.setUser(userDto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "특정 년월의 작물들 조회", description = "특정 년월에 해당하는 자신의 작물들을 조회합니다.", tags = { "Plant Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = MonthPlantSearchRequestDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/plant/month/search")
    public ResponseEntity<?> searchPlant(@RequestBody MonthPlantSearchRequestDTO dto) throws IOException {
        List<Plant> list = plantCService.searchPlantByMonth(dto.getUserId(),dto.getYear(),dto.getMonth());
        List<PlantSearchByMonthResponseDTO> resultDtos = new ArrayList<>();
        for(Plant p : list) {
            resultDtos.add(PlantSearchByMonthResponseDTO.of(p));
        }
        return ResponseEntity.ok(resultDtos);
    }

}
