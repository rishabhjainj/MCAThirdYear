package com.AbhiDev.edurecomm.Exams;

import java.io.Serializable;

public class QuestionResponse implements Serializable
{
    private Integer question;
    private String answer;

    public Integer getQuestion() {
        return question;
    }

    public void setQuestion(Integer question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}