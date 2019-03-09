package com.example.vidyut;

import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("fname")
    String name;
    @SerializedName("lname")
    String lname;
    @SerializedName("phno")
    String phnumber;
    @SerializedName("sex")
    int gender;
    @SerializedName("detailscomp")
    String dtcmp;

    public String getName() {
        return name;
    }

    public String getLname() {
        return lname;
    }

    public String getPhnumber() {
        return phnumber;
    }

    public int getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setPhnumber(String phnumber) {
        this.phnumber = phnumber;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Details{" +
                "name='" + name + '\'' +
                ", lname='" + lname + '\'' +
                ", phnumber='" + phnumber + '\'' +
                ", gender=" + gender +
                '}';
    }
}
