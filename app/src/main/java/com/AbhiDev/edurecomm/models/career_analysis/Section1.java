package com.AbhiDev.edurecomm.models.career_analysis;

import android.util.Log;

import com.wireout.apiservices.Repository;
import com.wireout.common.EntityLoader;
import com.wireout.listeners.OnEntityReceivedListener;

import java.util.List;

public class Section1 extends AbstractSection<Section1>{

    /** History and Goals **/

    //screen 1
    String name;
    String dob;
    String gender;
    String jobStatus;
    String highestEducationLevel;
    String country;
    String modeOfStudy;
    Integer year;

    //screeen 2
    String interest;
    String subject;
    String pastTime;
    String gameCategory;

    //screeen 3
    Integer yearOfPassing10;
    Integer yearOfPassing12;
    Float marks10;
    String marks10Type ="percentage";
    String marks12Type="percentage";
    Float marks12;


    List<HigherEducationDetail> professionalDetails;
    List<HigherEducationDetail> higherEducationDetails;
    List<ExperienceDetail> experienceDetails;

    public Section1(int numScreens) {
        super(numScreens);
    }


    public String getMarks10Type() {
        return marks10Type;
    }

    public void setMarks10Type(String marks10Type) {
        this.marks10Type = marks10Type;
    }

    public String getMarks12Type() {
        return marks12Type;
    }

    public void setMarks12Type(String marks12Type) {
        this.marks12Type = marks12Type;
    }

    public List<HigherEducationDetail> getProfessionalDetails() {
        return professionalDetails;
    }

    public void setProfessionalDetails(List<HigherEducationDetail> professionalDetails) {
        this.professionalDetails = professionalDetails;
    }

    public boolean isScreenValidated(int screenId){
        switch (screenId){
            case 1 :
                if(name.equals("") || dob.equals("") || gender.equals("") || jobStatus.equals("") || highestEducationLevel.equals("")
                        || country.equals("") || modeOfStudy.equals("") || year.equals(""))
                    return false;
                return true;
            case 2:if(interest.equals("")||subject.equals("")||pastTime.equals("")||gameCategory.equals(""))
                return false;
            return true;
            case 3:
                Log.d("AnalysisPresenter",marks10+"");
                if(marks10==null||marks12==null||yearOfPassing10==null||yearOfPassing12==null)
                return false;
            return true;
            default:
                return true;
        }
    }

    public void submit(OnEntityReceivedListener<Section1> listener){
//        if(!isAllValidated()) {
//            listener.showMessage("Section not validated.");
//            return;
//        }
        Repository repository = new Repository();
        repository.postAnalysisSection1(this, new EntityLoader(listener));
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

    public String getHigherEducationLevel() {
        return highestEducationLevel;
    }

    public void setHigherEducationLevel(String higherEducationLevel) {
        this.highestEducationLevel = higherEducationLevel;
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

    public Float getMarks10() {
        return marks10;
    }

    public void setMarks10(Float marks10) {
        this.marks10 = marks10;
    }

    public Float getMarks12() {
        return marks12;
    }

    public void setMarks12(Float marks12) {
        this.marks12 = marks12;
    }

    public String getHighestEducationLevel() {
        return highestEducationLevel;
    }

    public void setHighestEducationLevel(String highestEducationLevel) {
        this.highestEducationLevel = highestEducationLevel;
    }

    public List<HigherEducationDetail> getHigherEducationDetails() {
        return higherEducationDetails;
    }

    public void setHigherEducationDetails(List<HigherEducationDetail> higherEducationDetails) {
        this.higherEducationDetails = higherEducationDetails;
    }

    public List<ExperienceDetail> getExperienceDetails() {
        return experienceDetails;
    }

    public void setExperienceDetails(List<ExperienceDetail> experienceDetails) {
        this.experienceDetails = experienceDetails;
    }
}
