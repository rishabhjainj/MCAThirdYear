
package com.AbhiDev.edurecomm.models;

import java.io.Serializable;

public class BannerImages implements Serializable
{

    private Integer id;
    private String image;
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
