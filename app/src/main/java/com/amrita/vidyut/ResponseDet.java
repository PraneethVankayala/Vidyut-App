package com.amrita.vidyut;

import com.google.gson.annotations.SerializedName;

public class ResponseDet {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;

    public ResponseDet(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
