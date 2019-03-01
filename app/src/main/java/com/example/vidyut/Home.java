package com.example.vidyut;

import android.graphics.drawable.Drawable;
import android.media.Image;

public class Home {

    String name;
    int image;

    public Home(String name, int image){
        this.name  = name;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
