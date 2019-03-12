package com.example.vidyut.Model;

import android.graphics.drawable.Drawable;

public class NotificationData {
    private String text;
    private String image;
    private String desc;
    private long timeago;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getTimeago() {
        return timeago;
    }

    public void setTimeago(long timeago) {
        this.timeago = timeago;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
