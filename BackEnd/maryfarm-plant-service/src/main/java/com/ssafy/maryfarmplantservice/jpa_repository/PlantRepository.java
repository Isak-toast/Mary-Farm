package com.ssafy.maryfarmplantservice.jpa_repository;

import com.ssafy.maryfarmplantservice.domain.plant.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlantRepository extends JpaRepository<Plant, String> {
    Optional<Plant> findById(String id);

    List<Plant> findByUserId(String id);

//    @Query(value = "select p" +
//        " FROM Plant p" +
//        " WHERE (p.userId = :userId)" +
//        " AND ((p.active = true) OR (p.harvestTime = :yearMonth))")
    @Query(value = "select *" +
                " from plant" +
                " where plant.user_id = :userId" +
                " and ((plant.active = true) or ((year(plant.harvest_time) = :year) and (month(plant.harvest_time) = :month)))",nativeQuery = true)
    List<Plant> findPlantByMonth(@Param("userId") String userId, @Param("year") Integer year, @Param("month") Integer month);

//    @Query(value = "SELECT DISTINCT p" +
//            " FROM Plant p" +
//            " RIGHT JOIN Tag t ON t.plant.id = p.id" +
//            " WHERE t.name = :name")
//    List<Plant> findPlantByTag(@Param("name") String text);
}
