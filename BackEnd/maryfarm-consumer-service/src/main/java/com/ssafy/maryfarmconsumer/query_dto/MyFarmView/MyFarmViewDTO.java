package com.ssafy.maryfarmconsumer.query_dto.MyFarmView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "MyFarmView")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyFarmViewDTO {
    @Id
    private String id;
    private String userId;
    private Integer followerCount;
    private Integer followingCount;
    private List<FarmDiaryDTO> diaries = new ArrayList<>();

    public void addFollowerCount(Integer x) {
        this.followerCount+=x;
    }
    public void addFollowingCount(Integer x) {
        this.followingCount+=x;
    }
}
