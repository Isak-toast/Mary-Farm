package com.ssafy.maryfarmcalendarservice;

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
//        private final UserService userService;
//        private final FollowService followService;
        public void dbInit() {
//            User user = User.of("123456", "baek", Tier.씨앗);
//            User saveUser1 = userService.saveUser(user);
//            User user2 = User.of("1234567", "seung", Tier.씨앗);
//            User saveUser2 = userService.saveUser(user2);
//            followService.saveFollow(saveUser1.getId(),saveUser2.getId());
        }
    }
}
