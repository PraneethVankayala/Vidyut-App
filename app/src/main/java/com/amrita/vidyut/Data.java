package com.amrita.vidyut;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("vid")
    String vid;
    @SerializedName("email")
    String email;
    @SerializedName("fname")
    String fname;
    @SerializedName("lname")
    String lname;
    @SerializedName("ppic")
    String pic;
    @SerializedName("course")
    String course;
    @SerializedName("major")
    String major;
    @SerializedName("sex")
    int s;
    @SerializedName("year")
    int year;
    @SerializedName("college")
    int college;
    @SerializedName("institution")
    String institu;
    @SerializedName("school")
    String school;
    @SerializedName("phno")
    String phno;
    @SerializedName("detailscomp")
    boolean details;
    @SerializedName("educomp")
    boolean edu;
    @SerializedName("time_created")
    String time;
    @SerializedName("lastseen")
    String lastseen;

    public Data(String vid, String email, String fname, String lname, String pic, String course, String major, int s, int year, int college, String institu, String school, String phno, boolean details, boolean edu, String time, String lastseen) {
        this.vid = vid;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.pic = pic;
        this.course = course;
        this.major = major;
        this.s = s;
        this.year = year;
        this.college = college;
        this.institu = institu;
        this.school = school;
        this.phno = phno;
        this.details = details;
        this.edu = edu;
        this.time = time;
        this.lastseen = lastseen;
    }

    public Data(String vid, String email, String fname, String lname, String pic,boolean details,boolean edu,String phno){
        this.details=details;
        this.edu=edu;
        this.vid=vid;
        this.email=email;
        this.fname=fname;
        this.lname=lname;
        this.pic=pic;
        this.phno=phno;
    }

    public String getVid() {
        return vid;
    }

    public String getEmail() {
        return email;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getPic() {
        return pic;
    }

    public String getCourse() {
        return course;
    }

    public String getMajor() {
        return major;
    }

    public int getS() {
        return s;
    }

    public int getYear() {
        return year;
    }

    public int getCollege() {
        return college;
    }

    public String getInstitu() {
        return institu;
    }

    public String getSchool() {
        return school;
    }

    public String getPhno() {
        return phno;
    }

    public boolean isDetails() {
        return details;
    }

    public boolean isEdu() {
        return edu;
    }

    public String getTime() {
        return time;
    }

    public String getLastseen() {
        return lastseen;
    }


}
