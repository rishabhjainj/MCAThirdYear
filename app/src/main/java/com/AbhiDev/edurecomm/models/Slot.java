
package com.AbhiDev.edurecomm.models;

import java.io.Serializable;

public class Slot implements Serializable
{

    private Integer id;
    private String title;
    private String expertName;

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

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

}
