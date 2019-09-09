package com.AbhiDev.edurecomm.models.career_analysis;

public class BooleanQuestionResponse {
    int question; //database id of the question. Take value of id of the question retrieved from backend
    boolean answer; // agree/disagree , Yes/No

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
