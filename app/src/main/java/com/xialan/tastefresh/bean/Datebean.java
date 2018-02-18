package com.xialan.tastefresh.bean;

/**
 * Created by Administrator on 2018/1/31.
 */

public class Datebean {
    private String date;
    private String time_start;

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Datebean(String date, String time_start) {
        this.date = date;
        this.time_start = time_start;
    }

    @Override
    public String toString() {
        return "Datebean{" +
                "date='" + date + '\'' +
                ", time_start='" + time_start + '\'' +
                '}';
    }
}
