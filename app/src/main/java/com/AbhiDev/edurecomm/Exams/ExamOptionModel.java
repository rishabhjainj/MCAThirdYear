package com.AbhiDev.edurecomm.Exams;

import java.io.Serializable;

public class ExamOptionModel implements Serializable {
    String name;
    Integer id;
    Integer examImage;
    Integer examIcon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExamImage() {
        return examImage;
    }

    public void setExamImage(Integer examImage) {
        this.examImage = examImage;
    }

    public Integer getExamIcon() {
        return examIcon;
    }

    public void setExamIcon(Integer examIcon) {
        this.examIcon = examIcon;
    }
}
