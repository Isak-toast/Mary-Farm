package com.numberONE.maryfarm.Retrofit;

// 유저의 업로드된 전체 작물 정보 가져오기
public class UserPlant {
    private String plantId;

    public String getPlantId() {
        return plantId;
    }

    @Override
    public String toString() {
        return "UserPlant{" +
                "plantId='" + plantId + '\'' +
                '}';
    }
}