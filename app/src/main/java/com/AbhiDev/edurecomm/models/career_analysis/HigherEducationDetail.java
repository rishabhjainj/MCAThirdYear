package com.AbhiDev.edurecomm.models.career_analysis;

public class HigherEducationDetail {
    /** For section 1 **/
    String institute;
    String course;
    Float marks;
    Integer year;
    String marksType = "percentage";

    public String getMarksType() {
        return marksType;
    }

    public void setMarksType(String marksType) {
        this.marksType = marksType;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Float getMarks() {
        return marks;
    }

    public void setMarks(Float marks) {
        this.marks = marks;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    boolean validate(){
        return true;
    }
}
