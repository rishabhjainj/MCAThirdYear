
package com.AbhiDev.edurecomm.models;

import java.io.Serializable;
import java.util.List;

public class School implements Serializable
{

    private Integer id;
    private List<Course> courses;
    private List<CareerOption> careerOptions;
    private List<Institution> institutions;
    private String name;
    private Double popularity;
    private String description;
    private Integer ranking;
    private String image;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<CareerOption> getCareerOptions() {
        return careerOptions;
    }

    public void setCareerOptions(List<CareerOption> careerOptions) {
        this.careerOptions = careerOptions;
    }

    public List<Institution> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(List<Institution> institutions) {
        this.institutions = institutions;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
