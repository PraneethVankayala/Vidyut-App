package com.example.vidyut;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Workshops implements Serializable {
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
    @SerializedName("support")
    int support;
    @SerializedName("d1beg")
    String d1big;
    @SerializedName("d2beg")
    String d2beg;
    @SerializedName("d3beg")
    String d3beg;
    @SerializedName("d1end")
    String d1end;
    @SerializedName("d2end")
    String d2end;
    @SerializedName("d3end")
    String d3end;
    @SerializedName("registered")
    boolean registered;

    public Workshops(int id, String title, String plink, String sh, String about, String org, String venue, int department, int fee, String rules, int seats, String prereq, String d1dur, String d2dur, String d3dur, int rmseats, int support, String d1big, String d2beg, String d3beg, String d1end, String d2end, String d3end, boolean registered) {
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
        this.support = support;
        this.d1big = d1big;
        this.d2beg = d2beg;
        this.d3beg = d3beg;
        this.d1end = d1end;
        this.d2end = d2end;
        this.d3end = d3end;
        this.registered = registered;
    }

    public Workshops(int id, String title, String about, String venue, int department, int fee, String rules, String prereq, String d1big, String d2beg, String d3beg, String d1end, String d2end, String d3end) {
        this.id = id;
        this.title = title;
        this.about = about;
        this.venue = venue;
        this.department = department;
        this.fee = fee;
        this.rules = rules;
        this.prereq = prereq;
        this.d1big = d1big;
        this.d2beg = d2beg;
        this.d3beg = d3beg;
        this.d1end = d1end;
        this.d2end = d2end;
        this.d3end = d3end;
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

    public int getDepartment() {
        return department;
    }

    public int getFee() {
        return fee;
    }

    public String getRules() {
        return rules;
    }

    public int getSeats() {
        return seats;
    }

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

    public int getSupport() {
        return support;
    }

    public String getD1big() {
        return d1big;
    }

    public String getD2beg() {
        return d2beg;
    }

    public String getD3beg() {
        return d3beg;
    }

    public String getD1end() {
        return d1end;
    }

    public String getD2end() {
        return d2end;
    }

    public String getD3end() {
        return d3end;
    }

    public boolean isRegistered() {
        return registered;
    }
}
