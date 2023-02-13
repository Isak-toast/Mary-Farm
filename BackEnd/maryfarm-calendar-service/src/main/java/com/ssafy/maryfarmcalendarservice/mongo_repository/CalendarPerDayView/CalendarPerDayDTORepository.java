package com.ssafy.maryfarmcalendarservice.mongo_repository.CalendarPerDayView;

import com.ssafy.maryfarmcalendarservice.api.dto.query.CalendarPerDayView.CalendarPerDayDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CalendarPerDayDTORepository extends MongoRepository<CalendarPerDayDTO, String> {
    Optional<CalendarPerDayDTO> findByPlantIdAndYearAndMonthAndDay(String plantId, Integer year, Integer month, Integer day);
    List<CalendarPerDayDTO> findByUserIdAndYearAndMonthAndDay(String userId, Integer year, Integer month, Integer day);
}
