package com.example.vidyut;

import com.google.gson.annotations.SerializedName;

public class Workshops {
    @SerializedName("id")
    int id;
    @SerializedName("title")
    String title;
    @SerializedName("plink")
    String plink;
    @SerializedName("short")
    String sh;
    @SerializedName("about")
    String about;
    @SerializedName("org")
    String org;
    @SerializedName("venue")
    String venue;
    @SerializedName("department")
    int department;
    @SerializedName("fee")
    int fee;
    @SerializedName("rules")
    String rules;
    @SerializedName("seats")
    int seats;
    @SerializedName("prereq")
    String prereq;
    @SerializedName("d1dur")
    String d1dur;
    @SerializedName("d2dur")
    String d2dur;
    @SerializedName("d3dur")
    String d3dur;
    @SerializedName("rmseats")
    int rmseats;

    public Workshops(int id, String title, String plink, String sh, String about, String org, String venue, int department, int fee, String rules, int seats, String prereq, String d1dur, String d2dur, String d3dur, int rmseats) {
        this.id = id;
        this.title = title;
        this.plink = plink;
        this.sh = sh;
        this.about = about;
        this.org = org;
        this.venue = venue;
        this.department = department;
        this.fee = fee;
        this.rules = rules;
        this.seats = seats;
        this.prereq = prereq;
        this.d1dur = d1dur;
        this.d2dur = d2dur;
        this.d3dur = d3dur;
        this.rmseats = rmseats;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPlink() {
        return plink;
    }

    public String getSh() {
        return sh;
    }

    public String getAbout() {
        return about;
    }

    public String getOrg() {
        return org;
    }

    public String getVenue() {
        return venue;
    }

    public String getDepartment() { return Integer.toString(department); }

    public int getFee() {
        return fee;
    }

    public String getRules() {
        return rules;
    }

    public String getSeats() { return Integer.toString(seats); }

    public String getPrereq() {
        return prereq;
    }

    public String getD1dur() {
        return d1dur;
    }

    public String getD2dur() {
        return d2dur;
    }

    public String getD3dur() {
        return d3dur;
    }


    public int getRmseats() {
        return rmseats;
    }
}
