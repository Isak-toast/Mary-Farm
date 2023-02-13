package com.ssafy.maryfarmplantservice;

import com.ssafy.maryfarmplantservice.domain.diary.Diary;
import com.ssafy.maryfarmplantservice.domain.plant.Plant;
import com.ssafy.maryfarmplantservice.jpa_repository.DiaryRepository;
import com.ssafy.maryfarmplantservice.jpa_repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final PlantRepository plantRepository;
        private final DiaryRepository diaryRepository;

        public void dbInit() {
//            User user = User.of("123456", "baek", Tier.씨앗);
//            User saveUser1 = userService.saveUser(user);
//            User user2 = User.of("1234567", "seung", Tier.씨앗);
//            User saveUser2 = userService.saveUser(user2);
//            followService.saveFollow(saveUser1.getId(),saveUser2.getId());
            Plant plant1 = Plant.of("123456", "왕감자 일지 시작!", "감자");
            Plant savePlant1 = plantRepository.save(plant1);
            Plant plant2 = Plant.of("1234567", "왕딸기 일지 시작!", "딸기");
            Plant savePlant2 = plantRepository.save(plant2);
            Diary diary1 = Diary.of(plant1, "#왕감자 심기 시작함!", "010101","123456","baek","0101010",plant1.getTitle(),plant1.getCreatedDate(),plant1.getHarvestDate());
            Diary diary2 = Diary.of(plant2, "#왕딸기 심기 시작함!", "01010123","123456","baek","1212121",plant2.getTitle(),plant2.getCreatedDate(),plant2.getHarvestDate());
            diaryRepository.save(diary1);
            diaryRepository.save(diary2);
        }
    }
}
