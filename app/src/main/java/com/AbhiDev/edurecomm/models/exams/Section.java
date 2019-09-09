package com.AbhiDev.edurecomm.models.exams;

import java.io.Serializable;
import java.util.List;

public class Section implements Serializable
{
    private Integer id;
    private List<Question> questions;
    private Boolean isAttempted;
    private String title;
    private String questionOrdering;
    private Integer time;
    private String image;
    private List<String> tags = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Boolean getIsAttempted() {
        return isAttempted;
    }

    public void setIsAttempted(Boolean isAttempted) {
        this.isAttempted = isAttempted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestionOrdering() {
        return questionOrdering;
    }

    public void setQuestionOrdering(String questionOrdering) {
        this.questionOrdering = questionOrdering;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}