package com.ssafy.maryfarmuserservice.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyUserRequestDTO {
    private String userId;
    private String userName;
}
