package com.numberONE.maryfarm.Retrofit;

import com.numberONE.maryfarm.Retrofit.dto.DetailDiariesPerPlantView.DetailDiariesPerPlantDTO;

public class Thumbnail {
    private String title, thumbImg1, thumbImg2, thumbImg3, plantId, plantCreatedDate, harvestDate;
    private DetailDiariesPerPlantDTO detailDiariesPerPlantDTO;

    public Thumbnail(String title, String thumbImg1, String thumbImg2, String thumbImg3, String plantId, String plantCreatedDate, String harvestDate, DetailDiariesPerPlantDTO detailDiariesPerPlantDTO) {
        this.title = title;
        this.thumbImg1 = thumbImg1;
        this.thumbImg2 = thumbImg2;
        this.thumbImg3 = thumbImg3;
        this.plantId = plantId;
        this.plantCreatedDate = plantCreatedDate;
        this.harvestDate = harvestDate;
        this.detailDiariesPerPlantDTO = detailDiariesPerPlantDTO;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbImg1() {
        return thumbImg1;
    }

    public String getThumbImg2() {
        return thumbImg2;
    }

    public String getThumbImg3() {
        return thumbImg3;
    }

    public String getPlantId() {
        return plantId;
    }

    public String getPlantCreatedDate() {
        return plantCreatedDate;
    }

    public String getHarvestDate() {
        return harvestDate;
    }

    public DetailDiariesPerPlantDTO getDetailDiariesPerPlantDTO() {
        return detailDiariesPerPlantDTO;
    }
}
