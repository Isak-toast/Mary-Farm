package com.ssafy.maryfarmuserservice.domain.user;

import com.ssafy.maryfarmuserservice.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow extends BaseTimeEntity implements Serializable {
    /*
        Follow의 sender와 receiver 조합이 중복되는 경우는
        프론트엔드에서 팔로우 버튼을 누르면 언팔로우 버튼으로 바뀌는
        상황을 만들어 프론트엔드에서 해결해야 함.
     */
    @Id @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "follow_id")
    private String id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id")
    private User senderUser;
    private String senderName;
    private String senderProfilePath;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "receiver_id")
    private User receiverUser;
    private String receiverName;
    private String receiverProfilePath;
    public static Follow of(User sender, User receiver) {
        Follow follow = new Follow();
        follow.senderUser = sender;
        follow.senderName = sender.getUserName();
        follow.senderProfilePath = sender.getProfilePath();
        follow.receiverUser = receiver;
        follow.receiverName = receiver.getUserName();
        follow.receiverProfilePath = receiver.getProfilePath();
        return follow;
    }
}
