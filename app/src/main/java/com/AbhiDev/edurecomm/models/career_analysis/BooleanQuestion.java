
package com.AbhiDev.edurecomm.models.career_analysis;

import com.wireout.models.Question;

import java.io.Serializable;
import java.util.List;

public class BooleanQuestion implements Serializable
{

    private Integer id;
    private List<Question> questions;
    private String name;
    private Integer serialNo;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

}
