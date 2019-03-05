package com.example.vidyut;

import com.google.gson.annotations.SerializedName;

public class AuthToken {

    @SerializedName("idtoken")
    String idToken;

    public AuthToken(String idToken){
        this.idToken=idToken;
    }

    public String getIdToken(){
        return idToken;
    }

    public void setIdToken(String idToken){
        this.idToken=idToken;
    }
}
