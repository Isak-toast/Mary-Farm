package com.ssafy.maryfarmboardservice.client.dto.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponseDTO {
    private String userId;
    private String userName;
    private String tier;
    private String profilePath;

}
