package com.ssafy.maryfarmplantservice.domain.diary;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ssafy.maryfarmplantservice.domain.BaseTimeEntity;
import com.ssafy.maryfarmplantservice.domain.plant.Plant;
import com.ssafy.maryfarmplantservice.domain.tag.Tag;
import com.ssafy.maryfarmplantservice.formatter.LocalDateTimeDeserializer;
import com.ssafy.maryfarmplantservice.formatter.LocalDateTimeSerializer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "diary_id")
    private String id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "plant_id")
    private Plant plant;
    private String content;
    private Integer likes;
    private String imagePath;

    private String userId;
    private String userName;
    private String profilePath;
    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL)
    private List<Tag> tags = new ArrayList<>();
    private String title;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime plantCreatedDate;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime harvestDate;

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static Diary of(Plant plant, String content, String imagePath,
                           String userId, String userName, String profilePath,
                           String title, LocalDateTime plantCreatedDate, LocalDateTime harvestDate) {
        Diary diary = new Diary();
        diary.plant = plant;
        diary.content = content;
        diary.likes = 0;
        diary.imagePath = imagePath;
        diary.userId = userId;
        diary.userName = userName;
        diary.profilePath = profilePath;
        diary.title = title;
        diary.plantCreatedDate = plantCreatedDate;
        diary.harvestDate = harvestDate;
        return diary;
    }

    // 비즈니스 로직
    public void addLike(Integer cnt) {
        this.likes+=cnt;
    }

}
