package com.ssafy.maryfarmuserservice;

import com.ssafy.maryfarmuserservice.domain.user.Tier;
import com.ssafy.maryfarmuserservice.domain.user.User;
import com.ssafy.maryfarmuserservice.service.FollowCService;
import com.ssafy.maryfarmuserservice.service.UserCService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

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
        private final UserCService userCService;
        private final FollowCService followCService;
        public void dbInit() {
            User user = User.of("123456", "baek", Tier.씨앗);
            User saveUser1 = userCService.saveUser(user);
            User user2 = User.of("1234567", "seung", Tier.씨앗);
            User saveUser2 = userCService.saveUser(user2);
        }
    }
}
