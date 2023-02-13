package com.ssafy.maryfarmboardservice.client.service.user;

import com.ssafy.maryfarmboardservice.client.dto.user.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="maryfarm-user-service")
public interface UserServiceClient {
    @GetMapping("/api/user/{userId}")
    public UserResponseDTO searchUser(@PathVariable("userId") String userId);

    @GetMapping("/api/user/followers/{userId}")
    public List<UserResponseDTO> searchFollower(@PathVariable("userId") String userId);
}
