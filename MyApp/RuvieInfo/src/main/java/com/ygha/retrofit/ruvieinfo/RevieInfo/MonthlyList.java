package com.ygha.retrofit.ruvieinfo.RevieInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MonthlyList {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("electricity")
    @Expose
    private Electricity electricity;
    @SerializedName("gas")
    @Expose
    private Gas gas;
    @SerializedName("water")
    @Expose
    private Water water;
    @SerializedName("hotwater")
    @Expose
    private Hotwater hotwater;
    @SerializedName("heating")
    @Expose
    private Heating heating;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Electricity getElectricity() {
        return electricity;
    }

    public void setElectricity(Electricity electricity) {
        this.electricity = electricity;
    }

    public Gas getGas() {
        return gas;
    }

    public void setGas(Gas gas) {
        this.gas = gas;
    }

    public Water getWater() {
        return water;
    }

    public void setWater(Water water) {
        this.water = water;
    }

    public Hotwater getHotwater() {
        return hotwater;
    }

    public void setHotwater(Hotwater hotwater) {
        this.hotwater = hotwater;
    }

    public Heating getHeating() {
        return heating;
    }

    public void setHeating(Heating heating) {
        this.heating = heating;
    }

}

