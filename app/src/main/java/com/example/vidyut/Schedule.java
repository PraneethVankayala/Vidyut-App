package com.example.vidyut;

public class Schedule {

    private String tittle;
    private String desc;
    private String date;
    private String time ;
    private Timer timer;

    public Schedule(String tittle, String desc, String date, String time,Timer timer) {
        this.tittle = tittle;
        this.desc = desc;
        this.date = date;
        this.time = time;
        this.timer = timer;
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

}