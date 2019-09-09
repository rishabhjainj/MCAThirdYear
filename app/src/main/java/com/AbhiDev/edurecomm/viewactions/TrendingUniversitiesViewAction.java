package com.AbhiDev.edurecomm.viewactions;

import com.wireout.models.University;

import java.util.List;

/**
 * Created by rahul on 10/3/18.
 */

public interface TrendingUniversitiesViewAction extends BaseViewAction{

    public void setTrendingUniversitiesRecyclerView(List<University> universityDataList);
    void addToRecyclerView(List<University> universities);

}
