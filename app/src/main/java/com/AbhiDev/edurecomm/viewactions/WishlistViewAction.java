package com.AbhiDev.edurecomm.viewactions;

import com.wireout.models.CourseList;
import com.wireout.models.Mentor;
import com.wireout.models.ShortTermCourse;
import com.wireout.models.University;

import java.util.List;

/**
 * Created by rahul on 18/4/18.
 */

public interface WishlistViewAction extends BaseViewAction {

    public void setWishListRecyclerView(List<University> universityDataList);
    public void setWishListCourses(List<CourseList> courseList);
    public void setWishListShortTermCourses(List<ShortTermCourse> shortTermCourseList);
    public void setWishListCampaigners(List<Mentor> campaignersList);


}
