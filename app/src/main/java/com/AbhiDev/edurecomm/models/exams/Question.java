
package com.AbhiDev.edurecomm.models.exams;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable
{

    private Integer id;
    private Integer serialNo;
    private List<MultiChoiceAnswer> multiChoiceAnswers;
    private String questionText;
    private String questionType;
    private String correctAnswer;
    private String explanation;
    private Integer difficulty;
    private String image;
    private Double marks;
    private Boolean isMathematical;
    private Integer test;

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MultiChoiceAnswer> getMultiChoiceAnswers() {
        return multiChoiceAnswers;
    }

    public void setMultiChoiceAnswers(List<MultiChoiceAnswer> multiChoiceAnswers) {
        this.multiChoiceAnswers = multiChoiceAnswers;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
    }

    public Boolean getIsMathematical() {
        if(isMathematical!=null)
        return isMathematical;
        else
            return true;
    }

    public void setIsMathematical(Boolean isMathematical) {
        this.isMathematical = isMathematical;
    }

    public Integer getTest() {
        return test;
    }

    public void setTest(Integer test) {
        this.test = test;
    }

}