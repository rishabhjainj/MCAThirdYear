
package com.AbhiDev.edurecomm.models;

import java.io.Serializable;
import java.util.List;

public class CareerOption implements Serializable
{

    private Integer id;
    private String name;
    private Double popularity;
    private Integer ranking;
    private String salary;
    private String employment;
    private String description;
    private Object image;
    private List<Integer> schools;
    private List<Integer> educationalRequirements;
    private List<Integer> personalQualities;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public List<Integer> getSchools() {
        return schools;
    }

    public void setSchools(List<Integer> schools) {
        this.schools = schools;
    }

    public List<Integer> getEducationalRequirements() {
        return educationalRequirements;
    }

    public void setEducationalRequirements(List<Integer> educationalRequirements) {
        this.educationalRequirements = educationalRequirements;
    }

    public List<Integer> getPersonalQualities() {
        return personalQualities;
    }

    public void setPersonalQualities(List<Integer> personalQualities) {
        this.personalQualities = personalQualities;
    }

}
