package com.ygha.retrofit.ruvieinfo.RevieInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Heating {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("averageOfBuilding")
    @Expose
    private String averageOfBuilding;
    @SerializedName("averageOfComplex")
    @Expose
    private String averageOfComplex;
    @SerializedName("averageOfSameSizeHouse")
    @Expose
    private String averageOfSameSizeHouse;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAverageOfBuilding() {
        return averageOfBuilding;
    }

    public void setAverageOfBuilding(String averageOfBuilding) {
        this.averageOfBuilding = averageOfBuilding;
    }

    public String getAverageOfComplex() {
        return averageOfComplex;
    }

    public void setAverageOfComplex(String averageOfComplex) {
        this.averageOfComplex = averageOfComplex;
    }

    public String getAverageOfSameSizeHouse() {
        return averageOfSameSizeHouse;
    }

    public void setAverageOfSameSizeHouse(String averageOfSameSizeHouse) {
        this.averageOfSameSizeHouse = averageOfSameSizeHouse;
    }

}
