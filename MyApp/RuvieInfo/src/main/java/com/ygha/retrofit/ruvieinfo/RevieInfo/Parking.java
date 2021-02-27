package com.ygha.retrofit.ruvieinfo.RevieInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Parking {
    @SerializedName("parkCount")
    @Expose
    private String parkCount;
    @SerializedName("parkingResultList")
    @Expose
    private List<ParkingResultList> parkingResultList = null;

    public String getParkCount() {
        return parkCount;
    }

    public void setParkCount(String parkCount) {
        this.parkCount = parkCount;
    }

    public List<ParkingResultList> getParkingResultList() {
        return parkingResultList;
    }

    public void setParkingResultList(List<ParkingResultList> parkingResultList) {
        this.parkingResultList = parkingResultList;
    }

}


