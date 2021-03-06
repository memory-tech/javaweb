package com.example.web.domain;

/**
 * @Author Memory
 * @Date 2021/12/8 17:14
 * @Version 1.0
 */
public class Notice {
    private int id;
    private String title;//公告标题
    private String content;//公告内容

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
