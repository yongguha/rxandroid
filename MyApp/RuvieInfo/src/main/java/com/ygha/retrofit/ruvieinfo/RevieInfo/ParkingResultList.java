package com.ygha.retrofit.ruvieinfo.RevieInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParkingResultList {

    @SerializedName("carno")
    @Expose
    private String carno;
    @SerializedName("parkingFloor")
    @Expose
    private String parkingFloor;
    @SerializedName("parkingNum")
    @Expose
    private String parkingNum;
    @SerializedName("parkingTime")
    @Expose
    private Object parkingTime;

    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno;
    }

    public String getParkingFloor() {
        return parkingFloor;
    }

    public void setParkingFloor(String parkingFloor) {
        this.parkingFloor = parkingFloor;
    }

    public String getParkingNum() {
        return parkingNum;
    }

    public void setParkingNum(String parkingNum) {
        this.parkingNum = parkingNum;
    }

    public Object getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(Object parkingTime) {
        this.parkingTime = parkingTime;
    }

}