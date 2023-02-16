package com.ssafy.maryfarmuserservice.service;

import com.ssafy.maryfarmuserservice.client.dto.notify.CreateNotifyRequestDTO;
import com.ssafy.maryfarmuserservice.client.service.notify.NotifyServiceClient;
import com.ssafy.maryfarmuserservice.domain.user.Follow;
import com.ssafy.maryfarmuserservice.domain.user.User;
import com.ssafy.maryfarmuserservice.kafka.producer.user.UserProducer;
import com.ssafy.maryfarmuserservice.jpa_repository.FollowRepository;
import com.ssafy.maryfarmuserservice.jpa_repository.UserRepository;
import com.ssafy.maryfarmuserservice.redis.RedisFollow;
import com.ssafy.maryfarmuserservice.redis.RedisFollowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class FollowCService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final NotifyServiceClient notifyServiceClient;
    private final RedisFollowRepository redisFollowRepository;
    @Transactional
    public void saveFollow(String senderId, String receiverId) {
        redisFollowRepository.save(new RedisFollow(null,senderId,receiverId));
    }

    @Transactional
    @Scheduled(cron = "0 0/1 * * * ?")
    public void deleteRedisFollowFromRedis() {
        log.info("-----------update redisFollow run--------");
        Iterable<RedisFollow> all = redisFollowRepository.findAll();
        for(RedisFollow r : all) {
            Optional<User> sender = userRepository.findById(r.getSenderId());
            Optional<User> receiver = userRepository.findById(r.getReceiverId());
            Follow follow = Follow.of(sender.get(), receiver.get());
            Follow saveFollow = followRepository.save(follow);
            // 알람 생성 시작
            String content = sender.get().getUserName() + "님이 내 농장 이웃이 되었어요!";
            CreateNotifyRequestDTO createNotifyRequestDTO = new CreateNotifyRequestDTO("FollowRequest", content, r.getReceiverId(),
                    r.getSenderId(), "","");
            notifyServiceClient.saveNotify(createNotifyRequestDTO);
            // 알람 생성 종료
        }
        redisFollowRepository.deleteAll();
    }

//    @Transactional
//    public Follow saveFollow(String senderId, String receiverId) {
//        Optional<User> sender = userRepository.findById(senderId);
//        Optional<User> receiver = userRepository.findById(receiverId);
//        Follow follow = Follow.of(sender.get(), receiver.get());
//        Follow saveFollow = followRepository.save(follow);
//        // 알람 생성 시작
//        String content = sender.get().getUserName() + "님이 내 농장 이웃이 되었어요!";
//        CreateNotifyRequestDTO createNotifyRequestDTO = new CreateNotifyRequestDTO("FollowRequest", content, receiverId,
//                senderId, "","");
//        notifyServiceClient.saveNotify(createNotifyRequestDTO);
//        // 알람 생성 종료
//        return saveFollow;
//    }
}
