
package com.AbhiDev.edurecomm.models;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable
{

    private Integer id;
    private List<AttributesAffected> attributesAffected;
    private String text;
    private String serialNo;
    private String image;
    private Integer section;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<AttributesAffected> getAttributesAffected() {
        return attributesAffected;
    }

    public void setAttributesAffected(List<AttributesAffected> attributesAffected) {
        this.attributesAffected = attributesAffected;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

}
