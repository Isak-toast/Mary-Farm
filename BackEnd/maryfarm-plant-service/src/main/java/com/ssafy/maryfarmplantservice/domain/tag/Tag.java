package com.ssafy.maryfarmplantservice.domain.tag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.maryfarmplantservice.domain.BaseTimeEntity;
import com.ssafy.maryfarmplantservice.domain.diary.Diary;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "tag_id")
    private String id;
    private String name;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "diary_id")
    private Diary diary;
    private String plantId;
    private String imagePath;
    private String title;
    private LocalDateTime createdDate;
    private LocalDateTime plantCreatedDate;


    public static Tag of(Diary diary, String name, String plantId,
                         String imagePath, String title, LocalDateTime createdDate, LocalDateTime plantCreatedDate) {
        Tag tag = new Tag();
        tag.diary = diary;
        diary.getTags().add(tag);
        tag.name = name;
        tag.plantId = plantId;
        tag.imagePath = imagePath;
        tag.title = title;
        tag.createdDate = createdDate;
        tag.plantCreatedDate = plantCreatedDate;
        return tag;
    }
}
