package com.numberONE.maryfarm.Retrofit.Calendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WidgetMemoModel {
    @Expose
    public long id;
    @SerializedName("plantId")
    public String widgetplantId;
    @SerializedName("plantName")
    public String widgetplantName;
    @SerializedName("userId")
    public String widgetuserId;
    @SerializedName("water")
    public Boolean widgetwater;
    @SerializedName("branch")
    public Boolean widgetbranch;
    @SerializedName("nutrients")
    public Boolean widgetnutrients;
    @SerializedName("division")
    public Boolean widgetdivision;
    @SerializedName("memo")
    public Boolean widgetmemo;

    public long getId() {
        return id;
    }

    public String getWidgetplantId() {
        return widgetplantId;
    }

    public String getWidgetplantName() {
        return widgetplantName;
    }

    public String getWidgetuserId() {
        return widgetuserId;
    }

    public Boolean getWidgetwater() {
        return widgetwater;
    }

    public Boolean getWidgetbranch() {
        return widgetbranch;
    }

    public Boolean getWidgetnutrients() {
        return widgetnutrients;
    }

    public Boolean getWidgetdivision() {
        return widgetdivision;
    }

    public Boolean getWidgetmemo() {
        return widgetmemo;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setWidgetplantId(String widgetplantId) {
        this.widgetplantId = widgetplantId;
    }

    public void setWidgetplantName(String widgetplantName) {
        this.widgetplantName = widgetplantName;
    }

    public void setWidgetuserId(String widgetuserId) {
        this.widgetuserId = widgetuserId;
    }

    public void setWidgetwater(Boolean widgetwater) {
        this.widgetwater = widgetwater;
    }

    public void setWidgetbranch(Boolean widgetbranch) {
        this.widgetbranch = widgetbranch;
    }

    public void setWidgetnutrients(Boolean widgetnutrients) {
        this.widgetnutrients = widgetnutrients;
    }

    public void setWidgetdivision(Boolean widgetdivision) {
        this.widgetdivision = widgetdivision;
    }

    public void setWidgetmemo(Boolean widgetmemo) {
        this.widgetmemo = widgetmemo;
    }
}
