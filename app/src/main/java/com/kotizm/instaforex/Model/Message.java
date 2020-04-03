package com.kotizm.instaforex.Model;

import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("Id")
    private int Id;

    @SerializedName("ActualTime")
    private long actualTime;

    @SerializedName("Comment")
    private String comment;

    @SerializedName("Pair")
    private String pair;

    @SerializedName("Cmd")
    private int cmd;

    @SerializedName("TradingSystem")
    private int tradingSystem;

    @SerializedName("Period")
    private String period;

    @SerializedName("Price")
    private double price;

    @SerializedName("Sl")
    private double sl;

    @SerializedName("Tp")
    private double tp;

    public int getId() {
        return Id;
    }

    public long getActualTime() {
        return actualTime;
    }

    public String getComment() {
        return comment;
    }

    public String getPair() {
        return pair;
    }

    public int getCmd() {
        return cmd;
    }

    public int getTradingSystem() {
        return tradingSystem;
    }

    public String getPeriod() {
        return period;
    }

    public double getPrice() {
        return price;
    }

    public double getSl() {
        return sl;
    }

    public double getTp() {
        return tp;
    }
}