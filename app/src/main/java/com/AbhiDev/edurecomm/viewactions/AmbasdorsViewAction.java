package com.AbhiDev.edurecomm.viewactions;

import com.wireout.models.Mentor;

import java.util.List;

/**
 * Created by rahul on 10/3/18.
 */

public interface AmbasdorsViewAction extends BaseViewAction {

    public void setAmbassdorRecyclerView(List<Mentor> ambassdorsDataList);
    void addMoreMentors(List<Mentor> mentors);

}
