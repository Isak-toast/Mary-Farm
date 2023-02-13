package com.ssafy.maryfarmplantservice.jpa_repository;

import com.ssafy.maryfarmplantservice.domain.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, String> {

}
