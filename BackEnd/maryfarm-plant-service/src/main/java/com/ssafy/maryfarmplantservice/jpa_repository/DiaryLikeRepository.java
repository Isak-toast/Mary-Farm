package com.ssafy.maryfarmplantservice.jpa_repository;

import com.ssafy.maryfarmplantservice.domain.diary.DiaryLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryLikeRepository extends JpaRepository<DiaryLike, String> {
}
