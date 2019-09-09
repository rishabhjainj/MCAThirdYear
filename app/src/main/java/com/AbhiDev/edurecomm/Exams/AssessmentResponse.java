
package com.AbhiDev.edurecomm.Exams;

import java.io.Serializable;
import java.util.List;

public class AssessmentResponse implements Serializable
{
    private Integer assessment;
    private List<QuestionResponse> questionResponses;

    public Integer getAssessment() {
        return assessment;
    }

    public void setAssessment(Integer assessment) {
        this.assessment = assessment;
    }

    public List<QuestionResponse> getQuestionResponses() {
        return questionResponses;
    }

    public void setQuestionResponses(List<QuestionResponse> questionResponses) {
        this.questionResponses = questionResponses;
    }

}