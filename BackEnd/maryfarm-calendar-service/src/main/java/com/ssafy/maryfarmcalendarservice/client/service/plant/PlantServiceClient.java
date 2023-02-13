package com.ssafy.maryfarmcalendarservice.client.service.plant;

import com.ssafy.maryfarmcalendarservice.client.dto.plant.PlantResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@FeignClient(name="maryfarm-plant-service")
public interface PlantServiceClient {
    @GetMapping("/api/plant/{plantId}")
    public PlantResponseDTO searchPlant(@PathVariable("plantId") String plantId);
}
