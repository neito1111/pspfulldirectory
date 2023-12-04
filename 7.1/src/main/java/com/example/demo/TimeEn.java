package com.example.demo;

import java.sql.Time;

public class TimeEn {
    private Integer id;
    private Time time;

    public TimeEn() {
    }

    public TimeEn(Integer id, Time time) {
        this.id = id;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return time.toString();
    }
}
