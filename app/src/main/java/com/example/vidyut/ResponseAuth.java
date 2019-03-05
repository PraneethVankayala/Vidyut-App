package com.example.vidyut;

import com.google.gson.annotations.SerializedName;

public class ResponseAuth {

    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("auth_token")
    String auth;

    public String getAuth() {
        return auth;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public ResponseAuth(String status,String message,String auth)
    {
      this.status=status;
      this.message=message;
      this.auth=auth;
    }
}
