package com.ssafy.maryfarmnotifyservice.service;

import com.ssafy.maryfarmnotifyservice.domain.notify.AlarmType;
import com.ssafy.maryfarmnotifyservice.domain.notify.Notify;
import com.ssafy.maryfarmnotifyservice.jpa_repository.NotifyCRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class NotifyCService {
    private final NotifyCRepository notifyCRepository;

    @Transactional
    public Notify saveNotify(String type, String content, String userId,
                             String followerId, String plantId, String diaryId) {
        Notify notify = Notify.of(AlarmType.valueOf(type), content, true, userId,
                followerId,plantId,diaryId);
        Notify saveNotify = notifyCRepository.save(notify);
        return saveNotify;
    }
}
