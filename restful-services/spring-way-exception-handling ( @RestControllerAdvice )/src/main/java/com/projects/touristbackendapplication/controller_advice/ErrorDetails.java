package com.projects.touristbackendapplication.controller_advice;

import java.time.LocalDateTime;

public class ErrorDetails {

    private String msg;

    private LocalDateTime dateTime;

    public ErrorDetails() {
    }

    public ErrorDetails(String msg, LocalDateTime dateTime) {
        this.msg = msg;
        this.dateTime = dateTime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "ErrorDetails [msg=" + msg + ", dateTime=" + dateTime + "]";
    }

}
