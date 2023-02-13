package com.ssafy.maryfarmuserservice.api.dto.follow.response;

import com.ssafy.maryfarmuserservice.domain.user.Follow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowResponseDTO {
    private String followId;

    public static FollowResponseDTO of(Follow follow) {
        FollowResponseDTO dto = new FollowResponseDTO();
        dto.followId = follow.getId();
        return dto;
    }
}
