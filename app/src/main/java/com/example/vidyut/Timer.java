package com.example.vidyut;

public class Timer {

    private String time;
    private int viewtype;

    public Timer(String time, int viewtype) {
        this.time = time;
        this.viewtype = viewtype;
    }

    public String getTime() {
        return time;
    }

    public int getViewtype() {
        return viewtype;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setViewtype(int viewtype) {
        this.viewtype = viewtype;
    }
}
