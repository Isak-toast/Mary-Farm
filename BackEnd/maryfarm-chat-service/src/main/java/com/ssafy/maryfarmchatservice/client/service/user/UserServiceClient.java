package com.ssafy.maryfarmchatservice.client.service.user;

import com.ssafy.maryfarmchatservice.client.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="maryfarm-user-service")
public interface UserServiceClient {
    @GetMapping("/api/user/{userId}")
    public UserResponseDTO searchUser(@PathVariable("userId") String userId);
}
