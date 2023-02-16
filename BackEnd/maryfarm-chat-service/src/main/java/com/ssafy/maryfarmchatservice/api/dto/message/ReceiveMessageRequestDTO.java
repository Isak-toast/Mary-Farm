package com.ssafy.maryfarmchatservice.api.dto.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveMessageRequestDTO {
    private String roomId;
    private String userId;
}
