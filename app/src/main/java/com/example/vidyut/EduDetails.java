package com.example.vidyut;

import com.google.gson.annotations.SerializedName;

public class EduDetails {

  @SerializedName("course")
    String course;
  @SerializedName("major")
    String major;
  @SerializedName("college")
    String college;
  @SerializedName("institution")
    String institution;
  @SerializedName("year")
    int year;

    public String getCourse() {
        return course;
    }

    public String getMajor() {
        return major;
    }

    public String getCollege() {
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

    public void setCollege(String college) {
        this.college = college;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
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
