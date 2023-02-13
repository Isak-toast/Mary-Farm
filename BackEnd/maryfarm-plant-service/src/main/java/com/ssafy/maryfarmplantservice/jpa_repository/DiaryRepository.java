package com.ssafy.maryfarmplantservice.jpa_repository;

import com.ssafy.maryfarmplantservice.domain.diary.Diary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, String> {

//    @Query(value = "SELECT d" +
//            " FROM Diary d" +
//            " JOIN Plant p ON d.plant.id = p.id" +
//            " JOIN Follow f ON p.user.id = f.receiverUser.id" +
//            " WHERE f.senderUser.id = :id AND d.id < :lastPostId")
//    List<Diary> findFollowersDiary(@Param("id") Long id, @Param("lastPostId") Long lastPostId, Pageable pageable);

    // FROM의 주체와 SELECT로 가져오는 엔티티가 달라서 안될수도...
    @Query(value = "SELECT d" +
            " FROM Diary d" +
            " JOIN FETCH Tag t ON t.diary.id = d.id" +
            " JOIN FETCH Plant p ON p.id = d.plant.id" +
            " WHERE t.name = :text")
//            " GROUP BY d.plant.id" +
//            " ORDER BY d.createdDate desc")
//            " LIMIT 1")
    List<Diary> findDiaryByTag(@Param("text") String text);

    Optional<Diary> findById(String diaryId);

    @Query(value = "SELECT d" +
            " FROM Diary d" +
            " JOIN FETCH Plant p ON p.id = d.plant.id" +
            " WHERE d.plant.id = :id")
    List<Diary> findDiaryGroup(@Param("id") String id);

    @Query(value = "SELECT d" +
            " FROM Diary d" +
            " JOIN FETCH Plant p ON p.id = d.plant.id" +
            " WHERE p.userId = :id")
    List<Diary> findDiaryByUserId(@Param("id") String id);

    @Query(value = "SELECT d" +
            " FROM Diary d" +
            " JOIN FETCH Plant p ON p.id = d.plant.id" +
            " WHERE p.id = :id" +
            " ORDER BY d.createdDate DESC")
    List<Diary> findEarlyDiaryByPlantId(@Param("id") String id, Pageable page);

    @Query(value = "SELECT d" +
            " FROM Diary d" +
            " JOIN FETCH Tag t ON t.diary.id = d.id" +
            " JOIN FETCH Plant p ON p.id = d.plant.id" +
            " WHERE d.plant.id = :id")
    List<Diary> findDiaryByPlantId(@Param("id") String id);

    List<Diary> findTop5ByOrderByLikesDesc();
}
