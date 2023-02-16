package com.ssafy.maryfarmnotifyservice.domain.notify;

import com.ssafy.maryfarmnotifyservice.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notify extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "notify_id")
    private String id;
    @Column(name = "user_id")
    private String userId;
    @Enumerated(EnumType.STRING)
    private AlarmType type;
    private String content;
    private Boolean active;
    private String followerId;
    private String plantId;
    private String diaryId;

    public static Notify of(AlarmType type, String content, boolean active,
                            String userId, String followerId, String plantId, String diaryId) {
        Notify notify = new Notify();
        notify.type = type;
        notify.content = content;
        notify.active = active;
        notify.userId = userId;
        notify.followerId = followerId;
        notify.plantId = plantId;
        notify.diaryId = diaryId;
        return notify;
    }
}
