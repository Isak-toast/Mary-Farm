package com.ssafy.myfarm.repository;

import com.ssafy.myfarm.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findById(String id);
    Optional<User> findByEmail(String email);

    @Query("select u from User u where ")
    List<User> findFollower(String id);
}
