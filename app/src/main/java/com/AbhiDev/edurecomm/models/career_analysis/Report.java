package com.AbhiDev.edurecomm.models.career_analysis;

import com.wireout.models.Career;
import com.wireout.models.CareerList;
import com.wireout.models.CourseList;

import java.io.Serializable;
import java.util.List;

public class Report implements Serializable {
    CareerAnalysis careerAnalysis;
    Integer conventional, artistic, investigative, realistic, enterprising, social; //for radar char
    Integer influence, achievement, affiliation;
    Integer child, parent, adult;
    Integer leftBrain, rightBrain;
    Integer intelligence, emotional, verbal, creativity, spatial, social2;
    List<CareerList> careerOptions;
    List<CourseList> courses;

    Integer interactive, introspective, analytical; //Mc Kinsie Variables

    public List<CareerList> getCareerOptions() {
        return careerOptions;
    }

    public void setCareerOptions(List<CareerList> careerOptions) {
        this.careerOptions = careerOptions;
    }

    public List<CourseList> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseList> courses) {
        this.courses = courses;
    }

    public CareerAnalysis getCareerAnalysis() {
        return careerAnalysis;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public Integer getEgogramSum(){
        return child + parent + adult == 0 ? 1 : child + parent + adult;
    }

    public Float getChildPercentage(){
        return (float) child * 100/ getEgogramSum();
    }

    public Float getParentPercentage(){ return (float) parent * 100 / getEgogramSum(); }

    public Float getAdultPercentage(){
        return (float) adult * 100 / getEgogramSum();
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getEmotional() {
        return emotional;
    }

    public void setEmotional(Integer emotional) {
        this.emotional = emotional;
    }

    public Integer getVerbal() {
        return verbal;
    }

    public void setVerbal(Integer verbal) {
        this.verbal = verbal;
    }

    public Integer getSocial2() {
        return social2;
    }

    public void setSocial2(Integer social2) {
        this.social2 = social2;
    }

    public Integer getCreativity() {
        return creativity;
    }

    public void setCreativity(Integer creativity) {
        this.creativity = creativity;
    }

    public Integer getSpatial() {
        return spatial;
    }

    public void setSpatial(Integer spatial) {
        this.spatial = spatial;
    }

    public Integer getEnterprising() {
        return enterprising;
    }

    public void setEnterprising(Integer enterprising) {
        this.enterprising = enterprising;
    }

    public Integer getLeftBrain() {
        return leftBrain;
    }

    public void setLeftBrain(Integer leftBrain) {
        this.leftBrain = leftBrain;
    }

    public Integer getRightBrain() {
        return rightBrain;
    }

    public void setRightBrain(Integer rightBrain) {
        this.rightBrain = rightBrain;
    }

    public void setCareerAnalysis(CareerAnalysis careerAnalysis) {
        this.careerAnalysis = careerAnalysis;
    }

    public Integer getConventional() {
        return conventional;
    }

    public void setConventional(Integer conventional) {
        this.conventional = conventional;
    }

    public Integer getSocial() {
        return social;
    }

    public void setSocial(Integer social) {
        this.social = social;
    }

    public Integer getArtistic() {
        return artistic;
    }

    public void setArtistic(Integer artistic) {
        this.artistic = artistic;
    }

    public Integer getInvestigative() {
        return investigative;
    }

    public void setInvestigative(Integer investigative) {
        this.investigative = investigative;
    }

    public Integer getRealistic() {
        return realistic;
    }

    public void setRealistic(Integer realistic) {
        this.realistic = realistic;
    }

    public Integer getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(Integer affiliation) {
        this.affiliation = affiliation;
    }

    public Integer getInfluence() {
        return influence;
    }

    public void setInfluence(Integer influence) {
        this.influence = influence;
    }

    public Integer getAchievement() {
        return achievement;
    }

    public void setAchievement(Integer achievement) {
        this.achievement = achievement;
    }

    public Integer getChild() {
        return child;
    }

    public void setChild(Integer child) {
        this.child = child;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getAdult() {
        return adult;
    }

    public void setAdult(Integer adult) {
        this.adult = adult;
    }

    public Integer getInteractive() {
        return interactive;
    }

    public void setInteractive(Integer interactive) {
        this.interactive = interactive;
    }

    public Integer getIntrospective() {
        return introspective;
    }

    public void setIntrospective(Integer introspective) {
        this.introspective = introspective;
    }

    public Integer getAnalytical() {
        return analytical;
    }

    public void setAnalytical(Integer analytical) {
        this.analytical = analytical;
    }
}
