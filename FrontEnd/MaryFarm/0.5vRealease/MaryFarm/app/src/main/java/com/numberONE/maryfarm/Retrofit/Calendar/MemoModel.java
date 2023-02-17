package com.numberONE.maryfarm.Retrofit.Calendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MemoModel {
    @Expose
    public long id;
    @SerializedName("plant")
    public ItemModel plant;
    @SerializedName("water")
    public Boolean water;
    @SerializedName("branch")
    public Boolean branch;
    @SerializedName("nutrients")
    public Boolean nutrients;
    @SerializedName("division")
    public Boolean division;
    @SerializedName("memo")
    public Boolean memo;

    public long getId() { return id; }
    public ItemModel getPlant() { return plant; }
    public Boolean getWater() { return water; }
    public Boolean getBranch() { return branch;}
    public Boolean getNutrients() { return nutrients; }
    public Boolean getDivision() { return division; }
    public Boolean getMemo() { return memo; }

    public void setId(long id) {
        this.id = id;
    }

    public void setWater(Boolean water) {
        this.water = water;
    }

    public void setBranch(Boolean branch) {
        this.branch = branch;
    }

    public void setNutrients(Boolean nutrients) {
        this.nutrients = nutrients;
    }

    public void setDivision(Boolean division) {
        this.division = division;
    }

    public void setMemo(Boolean memo) {
        this.memo = memo;
    }
}
