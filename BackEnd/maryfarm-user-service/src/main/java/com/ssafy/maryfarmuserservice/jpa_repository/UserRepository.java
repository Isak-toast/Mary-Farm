package com.ssafy.maryfarmuserservice.jpa_repository;

import com.ssafy.maryfarmuserservice.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findById(String id);

    @Query(value = "SELECT u" +
            " FROM User u" +
            " RIGHT JOIN Follow f ON f.senderUser.id = u.id" +
            " WHERE f.receiverUser.id = :id")
    List<User> findFollower(@Param("id") String id);
}
