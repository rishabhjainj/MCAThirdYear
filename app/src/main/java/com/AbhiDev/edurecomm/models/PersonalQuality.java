
package com.AbhiDev.edurecomm.models;

import java.io.Serializable;

public class PersonalQuality implements Serializable
{

    private Integer id;
    private String name;

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

}
