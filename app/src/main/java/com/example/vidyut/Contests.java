package com.example.vidyut;

import com.google.gson.annotations.SerializedName;

public class Contests {

    @SerializedName("id")
    int id;
    @SerializedName("title")
    String title;
    @SerializedName("department")
    String dept;
    @SerializedName("about")
    String about;
    @SerializedName("prize1")
    int p1;
    @SerializedName("prize2")
    int p2;
    @SerializedName("prize3")
    int p3;
    @SerializedName("short")
    String shor;
    @SerializedName("pworth")
    int pworth;
    @SerializedName("d1dur")
    String d1dur;
    @SerializedName("d2dur")
    String d2dur;
    @SerializedName("d3dur")
    String d3dur;
    @SerializedName("venue")
    String venue;
    @SerializedName("team_limit")
    int limit;
    @SerializedName("fee")
    int fee;
    @SerializedName("incharge")
    String incharge;
    @SerializedName("support")
    int suppor;
    @SerializedName("rules")
    String rules;
    @SerializedName("prereq")
    String prereq;
    @SerializedName("d1beg")
    String d1beg;
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

    public Contests(int id, String title, String dept, String about, int p1, int p2, int p3, String shor, int pworth, String d1dur, String d2dur, String d3dur, String venue, int limit, int fee, String incharge, int suppor, String rules, String prereq,String d1beg,String d2beg,String d3beg,String d1end,String d2end,String d3end) {
        this.id = id;
        this.title = title;
        this.dept = dept;
        this.about = about;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.shor = shor;
        this.pworth = pworth;
        this.d1dur = d1dur;
        this.d2dur = d2dur;
        this.d3dur = d3dur;
        this.venue = venue;
        this.limit = limit;
        this.fee = fee;
        this.incharge = incharge;
        this.suppor = suppor;
        this.rules = rules;
        this.prereq = prereq;
        this.d1beg=d1beg;
        this.d2beg=d2beg;
        this.d3beg=d3beg;
        this.d1end=d1end;
        this.d2end=d2end;
        this.d3end=d3end;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDept() {
        return dept;
    }

    public String getAbout() {
        return about;
    }

    public int getP1() {
        return p1;
    }

    public int getP2() {
        return p2;
    }

    public int getP3() {
        return p3;
    }

    public String getShor() {
        return shor;
    }

    public int getPworth() {
        return pworth;
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

    public String getVenue() {
        return venue;
    }

    public int getLimit() {
        return limit;
    }

    public int getFee() {
        return fee;
    }

    public String getIncharge() {
        return incharge;
    }

    public int getSuppor() {
        return suppor;
    }

    public String getRules() {
        return rules;
    }

    public String getPrereq() {
        return prereq;
    }

    public String getD1beg() {
        return d1beg;
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
}
