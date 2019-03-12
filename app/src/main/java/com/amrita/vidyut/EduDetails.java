package com.amrita.vidyut;

import com.google.gson.annotations.SerializedName;

public class EduDetails {

  @SerializedName("course")
    String course;
  @SerializedName("major")
    String major;
  @SerializedName("college")
    int college;
  @SerializedName("institution")
    String institution;
  @SerializedName("year")
    int year;
  @SerializedName("educomp")
    boolean edcomp;

    public String getCourse() {
        return course;
    }

    public String getMajor() {
        return major;
    }

    public int getCollege() {
        return college;
    }

    public String getInstitution() {
        return institution;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setCollege(int college) {
        this.college = college;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public EduDetails(String course, String major, int college, String institution, int year) {
        this.course = course;
        this.major = major;
        this.college = college;
        this.institution = institution;
        this.year = year;
    }

    @Override
    public String toString() {
        return "EduDetails{" +
                "course='" + course + '\'' +
                ", major='" + major + '\'' +
                ", college='" + college + '\'' +
                ", institution='" + institution + '\'' +
                '}';
    }
}
