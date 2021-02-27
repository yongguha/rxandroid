package com.ygha.retrofit.ruvieinfo.RevieInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmsData {
    @SerializedName("monthlyList")
    @Expose
    private List<MonthlyList> monthlyList = null;
    @SerializedName("emsDate")
    @Expose
    private String emsDate;
    @SerializedName("basicDay")
    @Expose
    private String basicDay;

    public List<MonthlyList> getMonthlyList() {
        return monthlyList;
    }

    public void setMonthlyList(List<MonthlyList> monthlyList) {
        this.monthlyList = monthlyList;
    }

    public String getEmsDate() {
        return emsDate;
    }

    public void setEmsDate(String emsDate) {
        this.emsDate = emsDate;
    }

    public String getBasicDay() {
        return basicDay;
    }

    public void setBasicDay(String basicDay) {
        this.basicDay = basicDay;
    }
}








