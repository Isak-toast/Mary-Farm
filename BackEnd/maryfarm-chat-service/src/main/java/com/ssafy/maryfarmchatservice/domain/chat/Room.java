package com.ssafy.maryfarmchatservice.domain.chat;

import com.ssafy.maryfarmchatservice.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
// 프록시 객체 생성만을 위한 생성자라 Protected로 사용제한
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "room_id")
    private String id;
    @Column(name = "sender_id")
    private String senderId;
    private String senderName;
    private String senderProfilePath;
    @Column(name = "receiver_id")
    private String receiverId;
    private String receiverName;
    private String receiverProfilePath;

    public static Room of(String senderId, String senderName, String senderProfilePath,
                          String receiverId, String receiverName, String receiverProfilePath) {
        Room room = new Room();
        room.senderId = senderId;
        room.senderName = senderName;
        room.senderProfilePath = senderProfilePath;
        room.receiverId = receiverId;
        room.receiverName = receiverName;
        room.receiverProfilePath = receiverProfilePath;
        return room;
    }
}
