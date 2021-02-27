package com.ygha.retrofit.ruvieinfo.RevieInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotiResultList {

    @SerializedName("notiindex")
    @Expose
    private String notiindex;
    @SerializedName("notisubject")
    @Expose
    private String notisubject;
    @SerializedName("notimsg")
    @Expose
    private String notimsg;
    @SerializedName("notiwriter")
    @Expose
    private String notiwriter;
    @SerializedName("notidate")
    @Expose
    private String notidate;

    public String getNotiindex() {
        return notiindex;
    }

    public void setNotiindex(String notiindex) {
        this.notiindex = notiindex;
    }

    public String getNotisubject() {
        return notisubject;
    }

    public void setNotisubject(String notisubject) {
        this.notisubject = notisubject;
    }

    public String getNotimsg() {
        return notimsg;
    }

    public void setNotimsg(String notimsg) {
        this.notimsg = notimsg;
    }

    public String getNotiwriter() {
        return notiwriter;
    }

    public void setNotiwriter(String notiwriter) {
        this.notiwriter = notiwriter;
    }

    public String getNotidate() {
        return notidate;
    }

    public void setNotidate(String notidate) {
        this.notidate = notidate;
    }

}