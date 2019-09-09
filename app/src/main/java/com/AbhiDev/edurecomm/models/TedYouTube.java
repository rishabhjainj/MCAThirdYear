
package com.AbhiDev.edurecomm.models;

import java.io.Serializable;

public class TedYouTube implements Serializable
{

    private Integer id;
    private String title;
    private String youtubeId;

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

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

}