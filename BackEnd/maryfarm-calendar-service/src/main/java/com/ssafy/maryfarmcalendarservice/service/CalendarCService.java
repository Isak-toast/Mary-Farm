package com.ssafy.maryfarmcalendarservice.service;

import com.ssafy.maryfarmcalendarservice.api.dto.calendar.RegistCalendarRequestDTO;
import com.ssafy.maryfarmcalendarservice.domain.calendar.Calendar;
import com.ssafy.maryfarmcalendarservice.jpa_repository.CalendarCRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CalendarCService {
    private final CalendarCRepository calendarCRepository;
    @Transactional
    public Calendar registCalendar(RegistCalendarRequestDTO dto) {
        Calendar calendar = Calendar.of(dto.getPlantId(), dto.getYear(), dto.getMonth(),
                dto.getDay(), dto.getUserId(), dto.getWater(),
                dto.getBranch(), dto.getNutrients(), dto.getDivision(), dto.getMemo());
        return calendarCRepository.save(calendar);
    }
}
