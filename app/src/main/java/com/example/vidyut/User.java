package com.example.vidyut;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    Data datas;

    public User(String status,Data datas){
        this.status=status;
        this.datas=datas;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Data getDatas() {
        return datas;
    }

    public void setDatas(Data datas) {
        this.datas = datas;
    }
}
