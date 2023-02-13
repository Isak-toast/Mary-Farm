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
public class DiaryCommentLike extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "diary_comment_like_id")
    private String id;
    @Column(name = "user_id")
    private String userId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "diary_comment_id")
    private DiaryComment comment;
}
