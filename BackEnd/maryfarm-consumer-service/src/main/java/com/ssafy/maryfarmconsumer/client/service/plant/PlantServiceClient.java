package com.ssafy.maryfarmconsumer.client.service.plant;

import com.ssafy.maryfarmconsumer.client.dto.plant.PlantResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="maryfarm-plant-service")
public interface PlantServiceClient {
    @GetMapping("/api/plant/{plantId}")
    public PlantResponseDTO searchPlant(@PathVariable("plantId") String plantId);
}
