package com.ssafy.maryfarmuserservice.api.dto.query.response;

import com.ssafy.maryfarmuserservice.domain.user.User;
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

    public static UserResponseDTO of(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.userId = user.getId();
        dto.userName = user.getUserName();
        dto.tier = user.getTier().name();
        dto.profilePath = user.getProfilePath();
        return dto;
    }
}
