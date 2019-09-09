
package com.AbhiDev.edurecomm.models.exams;

import com.wireout.Exams.CategoryModel;

import java.io.Serializable;
import java.util.List;

public class ExamSubCategory implements Serializable
{
    private Integer id;
    private List<Question> questions;
    private CategoryModel category;
    private Boolean isAttempted;
    private String subcategory;
    private String timestamp;
    private String questionOrdering;
    private Integer time;
    private Integer difficulty;
    private Double totalMarks;
    private String image;
    private Boolean showResults;
    private Boolean showSolutions;


    public Boolean getAttempted() {
        return isAttempted;
    }

    public void setAttempted(Boolean attempted) {
        isAttempted = attempted;
    }

    public Boolean getShowResult() {
        return showResults;
    }

    public void setShowResult(Boolean showResult) {
        this.showResults = showResult;
    }

    public Boolean getShowSolutions() {
        return showSolutions;
    }

    public void setShowSolutions(Boolean showSolutions) {
        this.showSolutions = showSolutions;
    }

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

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public Boolean getIsAttempted() {
        return isAttempted;
    }

    public void setIsAttempted(Boolean isAttempted) {
        this.isAttempted = isAttempted;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Double getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(Double totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
