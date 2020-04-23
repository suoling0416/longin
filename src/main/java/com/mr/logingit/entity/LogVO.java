package com.mr.logingit.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class LogVO {
    private Integer id;
    private  String name;
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "LogVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", time=" + time +
                '}';
    }
}
