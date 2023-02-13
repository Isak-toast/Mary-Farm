package com.ssafy.maryfarmuserservice.client.service.notify;

import com.ssafy.maryfarmuserservice.client.dto.notify.CreateNotifyRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="maryfarm-notify-service")
public interface NotifyServiceClient {
    @PostMapping("/api/notify/save")
    public String saveNotify(@RequestBody CreateNotifyRequestDTO dto);
}
