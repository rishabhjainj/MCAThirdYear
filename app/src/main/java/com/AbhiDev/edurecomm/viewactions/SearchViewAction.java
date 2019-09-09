package com.AbhiDev.edurecomm.viewactions;

import com.wireout.models.Career;
import com.wireout.models.CareerList;
import com.wireout.models.CategoryList;
import com.wireout.models.CourseList;
import com.wireout.models.Institution;
import com.wireout.models.Mentor;

import java.util.List;

/**
 * Created by Rishabh on 4/19/2018.
 */

public interface SearchViewAction extends BaseViewAction {
    public void setUniversityRecyclerView(List<Institution> universityDataList);
    public void setCoursesRecyclerView(List<CourseList> courses);
    public void setShortTermCoursesRecyclerView(List<CategoryList> shortTermCourses);
    public void setCampaignersRecyclerView(List<CareerList> campaigners);
    public void showLoader();
    public void addGenStaticData();
    public void hideLoader();
}
