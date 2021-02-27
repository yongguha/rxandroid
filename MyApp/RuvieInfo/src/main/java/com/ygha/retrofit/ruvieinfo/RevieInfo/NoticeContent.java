package com.ygha.retrofit.ruvieinfo.RevieInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NoticeContent {
    @SerializedName("notiTotalCount")
    @Expose
    private String notiTotalCount;
    @SerializedName("notiResultList")
    @Expose
    private List<NotiResultList> notiResultList = null;
    @SerializedName("notiPageNum")
    @Expose
    private String notiPageNum;

    public String getNotiTotalCount() {
        return notiTotalCount;
    }

    public void setNotiTotalCount(String notiTotalCount) {
        this.notiTotalCount = notiTotalCount;
    }

    public List<NotiResultList> getNotiResultList() {
        return notiResultList;
    }

    public void setNotiResultList(List<NotiResultList> notiResultList) {
        this.notiResultList = notiResultList;
    }

    public String getNotiPageNum() {
        return notiPageNum;
    }

    public void setNotiPageNum(String notiPageNum) {
        this.notiPageNum = notiPageNum;
    }
}


