
package com.AbhiDev.edurecomm.models;

import com.wireout.models.career_analysis.BooleanQuestion;

import java.io.Serializable;
import java.util.List;

public class AnalysisQuestions implements Serializable
{

    private Integer count;
    private Object next;
    private Object previous;
    private List<BooleanQuestion> results = null;


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getNext() {
        return next;
    }

    public void setNext(Object next) {
        this.next = next;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public List<BooleanQuestion> getResults() {
        return results;
    }

    public void setResults(List<BooleanQuestion> results) {
        this.results = results;
    }

}
