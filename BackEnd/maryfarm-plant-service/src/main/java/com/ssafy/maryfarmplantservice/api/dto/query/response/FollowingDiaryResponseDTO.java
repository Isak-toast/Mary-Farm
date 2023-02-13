package com.ssafy.maryfarmplantservice.api.dto.query.response;

import com.ssafy.maryfarmplantservice.client.dto.user.UserResponseDTO;
import com.ssafy.maryfarmplantservice.domain.diary.Diary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowingDiaryResponseDTO {
    private UserResponseDTO user;
    private String imagepath;
    public static FollowingDiaryResponseDTO of(Diary diary, UserResponseDTO userDto) {
        FollowingDiaryResponseDTO dto = new FollowingDiaryResponseDTO();
        dto.user = userDto;
        dto.imagepath = diary.getImagePath();
        return dto;
    }
}
