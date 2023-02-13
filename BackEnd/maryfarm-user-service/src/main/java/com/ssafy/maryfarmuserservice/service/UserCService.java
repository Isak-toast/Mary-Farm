package com.ssafy.maryfarmuserservice.service;

import com.ssafy.maryfarmuserservice.domain.Land;
import com.ssafy.maryfarmuserservice.domain.user.Recommend;
import com.ssafy.maryfarmuserservice.domain.user.User;
import com.ssafy.maryfarmuserservice.jpa_repository.RecommendRepository;
import com.ssafy.maryfarmuserservice.jpa_repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserCService {
    private final UserRepository userRepository;
    private final RecommendRepository recommendRepository;
    private final MongoTemplate mongoTemplate;

    @Transactional
    public User saveUser(final User user) {
        User saveUser = userRepository.save(user);
        return saveUser;
    }
    public User findUser(final String id) {
        return userRepository.findById(id).get();
    }


    public User loginUser(final String kakaoid) {
        Optional<User> user = userRepository.findById(kakaoid);
        if(user.isPresent()) {
            return user.get();
        }
        return null;
    }
    @Transactional
    public User updateUser(final String id, final String userName, final String profilePath) {
        Optional<User> user = userRepository.findById(id);
        user.get().setUserName(userName);
        user.get().setProfilePath(profilePath);
        return user.get();
    }

    @Transactional
    public User registLand(String id, String latitude, String longitude) {
        Optional<User> user = userRepository.findById(id);
        Land land = new Land(latitude, longitude);
        user.get().setLand(land);
        return user.get();
    }

    @Transactional
    public Recommend saveRecommend(String id, String fullCode) {
        Optional<User> user = userRepository.findById(id);
        Recommend recommend = Recommend.of(user.get(), fullCode);
        Recommend saveRecommend = recommendRepository.save(recommend);
        return saveRecommend;
    }

    public List<User> searchFollowers(String userId) {
        return userRepository.findFollower(userId);
    }
}
