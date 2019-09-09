package com.AbhiDev.edurecomm.models;

import java.util.List;

public class TestResponse {
    private Integer test;
    private List<QuestionResponse> questionResponses;

    public Integer getTest() {
        return test;
    }

    public void setTest(Integer test) {
        this.test = test;
    }

    public List<QuestionResponse> getQuestionResponses() {
        return questionResponses;
    }

    public void setQuestionResponses(List<QuestionResponse> questionResponses) {
        this.questionResponses = questionResponses;
    }

}
