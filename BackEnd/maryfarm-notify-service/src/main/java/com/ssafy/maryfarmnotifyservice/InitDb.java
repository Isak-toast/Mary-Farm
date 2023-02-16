package com.ssafy.maryfarmnotifyservice;

import com.ssafy.maryfarmnotifyservice.domain.notify.AlarmType;
import com.ssafy.maryfarmnotifyservice.domain.notify.Notify;
import com.ssafy.maryfarmnotifyservice.jpa_repository.NotifyCRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;
    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final NotifyCRepository notifyCRepository;
        public void dbInit() {
//            Notify notify1 = Notify.of(AlarmType.FollowRequest, "백님이 팔로우했습니다.", true, "123456");
//            Notify notify2 = Notify.of(AlarmType.FollowerUpload, "김님이 글을 올렸습니다.", true, "1234567");
//            Notify saveNotify1 = notifyCRepository.save(notify1);
//            Notify saveNotify2 = notifyCRepository.save(notify2);
        }
    }
}
