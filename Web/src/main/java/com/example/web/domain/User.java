package com.example.web.domain;

/**
 * @Author Memory
 * @Date 2021/12/2 13:20
 * @Version 1.0
 */
public class User {

    private int id;
    private String no;
    private String username;
    private String department;
    private String sex;
    private String phone;
    private String password;

    /*public User(int id, String no, String username, String department, String sex, String phone, String password) {
        this.id = id;
        this.no = no;
        this.username = username;
        this.department = department;
        this.sex = sex;
        this.phone = phone;
        this.password = password;
    }*/

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
