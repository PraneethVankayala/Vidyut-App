package com.amrita.vidyut;

public class Schedule {

    private String tittle;
    private String desc;
    private String date;
    private String venue;
    private String time ;
    private Timer timer;

    public Schedule(String tittle, String desc, String date, String time,Timer timer,String venue) {
        this.tittle = tittle;
        this.desc = desc;
        this.date = date;
        this.time = time;
        this.timer = timer;
        this.venue = venue;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public String getTittle() {
        return tittle;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return "Date: "+date;
    }

    public String getTime() {
        return "Time: "+time;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeName() {
        return timer.getTime();
    }

    public int getTimeId() {
        return timer.getViewtype();
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}