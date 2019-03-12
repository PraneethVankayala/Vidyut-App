package com.amrita.vidyut;

import com.google.gson.annotations.SerializedName;

public class Registration {
    @SerializedName("regid")
    int regid;
    @SerializedName("typ")
    int typ;
    @SerializedName("registime")
    String time;
    @SerializedName("eid")
    int eid;
    @SerializedName("title")
    String title;
    @SerializedName("fee")
    int fee;

    public Registration(int regid, int typ, String time, int eid, String title, int fee) {
        this.regid = regid;
        this.typ = typ;
        this.time = time;
        this.eid = eid;
        this.title = title;
        this.fee = fee;
    }

    public int getRegid() {
        return regid;
    }

    public void setRegid(int regid) {
        this.regid = regid;
    }

    public int getTyp() {
        return typ;
    }

    public void setTyp(int typ) {
        this.typ = typ;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }
}
