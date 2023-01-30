package com.ssafy.maryfarmuserservice.kafka.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String user_id;
    private String nickname;
    private String tier;
    private String latitude;
    private String longitude;
    private String profilePath;
}
