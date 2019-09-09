
package com.AbhiDev.edurecomm.models.exams;

import java.io.Serializable;

public class MultiChoiceAnswer implements Serializable
{

    private Integer id;
    private String answerText;
    private Integer question;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Integer getQuestion() {
        return question;
    }

    public void setQuestion(Integer question) {
        this.question = question;
    }

}
