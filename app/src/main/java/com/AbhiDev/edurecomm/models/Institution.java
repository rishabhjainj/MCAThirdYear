
package com.AbhiDev.edurecomm.models;

import java.io.Serializable;
import java.util.List;

public class Institution implements Serializable
{

    private Integer id;
    private Integer views;
    private Integer numLikes;
    private Boolean userLiked;
    private List<String> images;
    private String name;
    private String shortLocation;
    private String contact;
    private String websiteUrl;
    private Double rating;
    private String logo;
    private String featureImage;
    private String admissionProcedure;
    private String ownedType;
    private String univType;
    private String description;
    private Integer ranking;
    private Integer location;
    private List<Integer> schools;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(Integer numLikes) {
        this.numLikes = numLikes;
    }

    public Boolean getUserLiked() {
        return userLiked;
    }

    public void setUserLiked(Boolean userLiked) {
        this.userLiked = userLiked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortLocation() {
        return shortLocation;
    }

    public void setShortLocation(String shortLocation) {
        this.shortLocation = shortLocation;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }

    public String getAdmissionProcedure() {
        return admissionProcedure;
    }

    public void setAdmissionProcedure(String admissionProcedure) {
        this.admissionProcedure = admissionProcedure;
    }

    public String getOwnedType() {
        return ownedType;
    }

    public void setOwnedType(String ownedType) {
        this.ownedType = ownedType;
    }

    public String getUnivType() {
        return univType;
    }

    public void setUnivType(String univType) {
        this.univType = univType;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }


    public List<Integer> getSchools() {
        return schools;
    }

    public void setSchools(List<Integer> schools) {
        this.schools = schools;
    }

}
