package com.ygha.retrofit.ruvieinfo.RevieInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Parcel {
    @SerializedName("parcelResult")
    @Expose
    private List<ParcelResult> parcelResult = null;

    public List<ParcelResult> getParcelResult() {
        return parcelResult;
    }

    public void setParcelResult(List<ParcelResult> parcelResult) {
        this.parcelResult = parcelResult;
    }
}


