package com.AbhiDev.edurecomm.models;

import java.io.Serializable;

public class DailyGoals implements Serializable
{

    private Integer id;
    private String title;
    private String code;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}