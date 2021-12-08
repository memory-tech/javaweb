package com.example.web.domain;

/**
 * @Author Memory
 * @Date 2021/12/7 10:32
 * @Version 1.0
 */
public class UserAttendance {
    private int id;
    private String date;     //日期
    private String no;      //编号
    private String username;        //名字
    private String department;      //部门
    private String begin;      //签到时间
    private String end;        //签退时间
    private String state;      //考勤状态（分为正常、缺卡、旷工）

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
