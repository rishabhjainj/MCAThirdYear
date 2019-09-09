package com.AbhiDev.edurecomm.viewactions;

import com.wireout.models.Course;
import com.wireout.models.CourseList;

import java.util.List;

public interface CourseViewAction extends BaseViewAction {
    void initUi(Course course);
    void feedUniName(String name);
    void addToRecyclerView(List<CourseList> courseLists);
}
