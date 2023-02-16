package com.ssafy.maryfarmconsumer.query_dto.MyFarmView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Document(collection = "MyFarmView")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyFarmViewDTO {
    @Id
    private String id;
    @Indexed
    private String userId;
    private Integer followerCount;
    private Integer followingCount;
    private List<FarmDiaryDTO> diaries = new ArrayList<>();
    private String userName;
    private String profilePath;

    public void addFollowerCount(Integer x) {
        this.followerCount+=x;
    }
    public void addFollowingCount(Integer x) {
        this.followingCount+=x;
    }

    public MyFarmViewDTO(Map<Object, Object> payload) {
        this.userId = (String) payload.get("user_id");
        this.followerCount = 0;
        this.followingCount = 0;
        this.userName = (String) payload.get("user_name");
        this.profilePath = (String) payload.get("profile_path");
    }
}
