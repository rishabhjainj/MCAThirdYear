package com.AbhiDev.edurecomm.viewactions;


import com.wireout.models.Course;
import com.wireout.models.Mentor;
import com.wireout.models.ShortTermCourse;
import com.wireout.models.University;

import java.util.List;

/**
 * Created by Rishabh on 2/18/2018.
 */

public interface UniversityViewAction extends BaseViewAction {
    public void setCoursesRecyclerView(List<Course> courses);
    public void setUniversitiesRecyclerView(List<University> universitiesi);
    public void setShortTermCoursesRecyclerView(List<ShortTermCourse> shortTermCourses);
    public void setCampaignersRecyclerView(List<Mentor> campaigners);
    public void setLikedUniversity(int id);


}
