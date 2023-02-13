package com.ssafy.maryfarmnotifyservice.jpa_repository;

import com.ssafy.maryfarmnotifyservice.domain.notify.Notify;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotifyCRepository extends JpaRepository<Notify, String> {
}
