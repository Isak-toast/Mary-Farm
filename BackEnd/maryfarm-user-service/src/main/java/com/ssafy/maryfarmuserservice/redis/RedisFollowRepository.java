package com.ssafy.maryfarmuserservice.redis;

import com.ssafy.maryfarmuserservice.domain.user.Follow;
import org.springframework.data.repository.CrudRepository;

public interface RedisFollowRepository extends CrudRepository<RedisFollow, String> {

}
