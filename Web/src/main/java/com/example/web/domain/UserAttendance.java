package com.example.web.domain;

/**
 * @Author Memory
 * @Date 2021/12/7 10:32
 * @Version 1.0
 */
public class UserAttendance {
    private int id;
    private String no;
    private String username;
    private String begin;
    private String end;

    public UserAttendance(int id, String no, String username, String begin, String end) {
        this.id = id;
        this.no = no;
        this.username = username;
        this.begin = begin;
        this.end = end;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
