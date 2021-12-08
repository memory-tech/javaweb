package com.example.web.domain;

/**
 * @Author Memory
 * @Date 2021/12/8 14:24
 * @Version 1.0
 */
public class Application {
    private int id;
    private String no;
    private String date;
    private String reason;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
