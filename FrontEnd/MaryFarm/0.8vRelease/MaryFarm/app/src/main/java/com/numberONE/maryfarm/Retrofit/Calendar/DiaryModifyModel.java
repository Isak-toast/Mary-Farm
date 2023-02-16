package com.numberONE.maryfarm.Retrofit.Calendar;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class DiaryModifyModel {
    @Expose
    public long id;
    @SerializedName("content")
    public String diaryContent;
    @SerializedName("modifyDate")
    public Date modifyDate;
    @SerializedName("newimage")
    public Bitmap newimage;

    public long getId() {
        return id;
    }

    public String getDiaryContent() {
        return diaryContent;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public Bitmap getNewimage() {
        return newimage;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDiaryContent(String diaryContent) {
        this.diaryContent = diaryContent;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public void setNewimage(Bitmap newimage) {
        this.newimage = newimage;
    }
}
