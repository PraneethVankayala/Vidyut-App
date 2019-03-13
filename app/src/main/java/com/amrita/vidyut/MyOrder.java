package com.amrita.vidyut;

import com.google.gson.annotations.SerializedName;

public class MyOrder {

    @SerializedName("purid")
    int id;
    @SerializedName("purchase")
    String purchase;
    @SerializedName("pid")
    String pid;
    @SerializedName("scount")
    int scount;
    @SerializedName("mcount")
    int mcount;
    @SerializedName("lcount")
    int lcount;
    @SerializedName("xlcount")
    int xlcount;
    @SerializedName("xxlcount")
    int xxlcount;
    @SerializedName("Qty")
    int qty;
    @SerializedName("total")
    int total;
    @SerializedName("purtime")
    String purtime;

    public MyOrder(int id, String purchase, String pid, int scount, int mcount, int lcount, int xlcount, int xxlcount, int qty, int total, String purtime) {
        this.id = id;
        this.purchase = purchase;
        this.pid = pid;
        this.scount = scount;
        this.mcount = mcount;
        this.lcount = lcount;
        this.xlcount = xlcount;
        this.xxlcount = xxlcount;
        this.qty = qty;
        this.total = total;
        this.purtime = purtime;
    }

    public int getId() {
        return id;
    }

    public String getPurchase() {
        return purchase;
    }

    public String getPid() {
        return pid;
    }

    public int getScount() {
        return scount;
    }

    public int getMcount() {
        return mcount;
    }

    public int getLcount() {
        return lcount;
    }

    public int getXlcount() {
        return xlcount;
    }

    public int getXxlcount() {
        return xxlcount;
    }

    public int getQty() {
        return qty;
    }

    public int getTotal() {
        return total;
    }

    public String getPurtime() {
        return purtime;
    }
}
