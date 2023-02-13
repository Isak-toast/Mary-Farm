package com.ssafy.maryfarmuserservice.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LandRegistRequestDTO {
    private String userId;
    private String latitude;
    private String longitude;
}
