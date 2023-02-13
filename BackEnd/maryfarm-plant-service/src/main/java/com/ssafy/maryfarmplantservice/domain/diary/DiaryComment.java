package com.ssafy.maryfarmplantservice.domain.diary;

import com.ssafy.maryfarmplantservice.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryComment extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "diary_comment_id")
    private String id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "diary_id")
    private Diary diary;
    @Column(name = "user_id")
    private String userId;
    private String content;
    private Integer likes;

    public static DiaryComment of(Diary diary, String userId, String content) {
        DiaryComment diaryComment = new DiaryComment();
        diaryComment.diary = diary;
        diaryComment.userId = userId;
        diaryComment.content = content;
        diaryComment.likes = 0;
        return diaryComment;
    }
}
