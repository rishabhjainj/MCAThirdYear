package com.AbhiDev.edurecomm.models.career_analysis;

public class ExperienceDetail {
    String employer;
    String designation;
    String startDate;
    String endDate;

    public String getName() {
        return employer;
    }

    public void setName(String name) {
        this.employer = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        if(startDate.equals(""))
            this.startDate ="0000-00-00T00:00:00";
        else
            this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        if(endDate.equals(""))
            this.endDate ="0000-00-00T00:00:00";
        else
            this.endDate = endDate;
    }
}
