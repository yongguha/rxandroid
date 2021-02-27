package com.ygha.retrofit.ruvieinfo.RevieInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RuvieDevice {
    @SerializedName("resultList")
    @Expose
    public List<ResultList> resultList = null;

    public List<ResultList> getResultList() {
        return resultList;
    }

    public void setResultList(List<ResultList> resultList) {
        this.resultList = resultList;
    }
}
