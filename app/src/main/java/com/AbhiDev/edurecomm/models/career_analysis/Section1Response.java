
package com.AbhiDev.edurecomm.models.career_analysis;

import java.io.Serializable;
import java.util.List;

public class Section1Response implements Serializable
{

    private Integer id;
    private List<Object> higherEducationDetails;
    private List<Object> experienceDetails;
    private String name;
    private String dob;
    private String gender;
    private String jobStatus;
    private String country;
    private String modeOfStudy;
    private Integer year;
    private String highestEducationLevel;
    private String interest;
    private String subject;
    private String pastTime;
    private String gameCategory;
    private Integer yearOfPassing10;
    private Integer yearOfPassing12;
    private Integer marks10;
    private Integer marks12;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Object> getHigherEducationDetails() {
        return higherEducationDetails;
    }

    public void setHigherEducationDetails(List<Object> higherEducationDetails) {
        this.higherEducationDetails = higherEducationDetails;
    }

    public List<Object> getExperienceDetails() {
        return experienceDetails;
    }

    public void setExperienceDetails(List<Object> experienceDetails) {
        this.experienceDetails = experienceDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getModeOfStudy() {
        return modeOfStudy;
    }

    public void setModeOfStudy(String modeOfStudy) {
        this.modeOfStudy = modeOfStudy;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getHighestEducationLevel() {
        return highestEducationLevel;
    }

    public void setHighestEducationLevel(String highestEducationLevel) {
        this.highestEducationLevel = highestEducationLevel;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPastTime() {
        return pastTime;
    }

    public void setPastTime(String pastTime) {
        this.pastTime = pastTime;
    }

    public String getGameCategory() {
        return gameCategory;
    }

    public void setGameCategory(String gameCategory) {
        this.gameCategory = gameCategory;
    }

    public Integer getYearOfPassing10() {
        return yearOfPassing10;
    }

    public void setYearOfPassing10(Integer yearOfPassing10) {
        this.yearOfPassing10 = yearOfPassing10;
    }

    public Integer getYearOfPassing12() {
        return yearOfPassing12;
    }

    public void setYearOfPassing12(Integer yearOfPassing12) {
        this.yearOfPassing12 = yearOfPassing12;
    }

    public Integer getMarks10() {
        return marks10;
    }

    public void setMarks10(Integer marks10) {
        this.marks10 = marks10;
    }

    public Integer getMarks12() {
        return marks12;
    }

    public void setMarks12(Integer marks12) {
        this.marks12 = marks12;
    }

}
