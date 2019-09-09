
package com.AbhiDev.edurecomm.models;

import java.io.Serializable;
import java.util.List;

public class Career implements Serializable
{

    private Integer id;
    private List<School> schools;
    private List<RelatedCourse> courses ;
    private List<EducationalRequirement> educationalRequirements;
    private List<PersonalQuality> personalQualities;
    private List<Institution> institutions;
    private List<CareerOption> relatedCareerOptions;
    private String name;
    private Double popularity;
    private String salary;
    private String description;
    private String employment;
    private String image;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }


    public List<EducationalRequirement> getEducationalRequirements() {
        return educationalRequirements;
    }

    public void setEducationalRequirements(List<EducationalRequirement> educationalRequirements) {
        this.educationalRequirements = educationalRequirements;
    }

    public List<PersonalQuality> getPersonalQualities() {
        return personalQualities;
    }

    public void setPersonalQualities(List<PersonalQuality> personalQualities) {
        this.personalQualities = personalQualities;
    }

    public List<Institution> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(List<Institution> institutions) {
        this.institutions = institutions;
    }

    public List<CareerOption> getRelatedCareerOptions() {
        return relatedCareerOptions;
    }

    public void setRelatedCareerOptions(List<CareerOption> relatedCareerOptions) {
        this.relatedCareerOptions = relatedCareerOptions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public List<RelatedCourse> getCourses() {
        return courses;
    }

    public void setCourses(List<RelatedCourse> courses) {
        this.courses = courses;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
