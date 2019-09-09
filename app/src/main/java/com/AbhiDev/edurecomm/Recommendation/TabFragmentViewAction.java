package com.AbhiDev.edurecomm.Recommendation;



import com.wireout.models.University;
import com.wireout.viewactions.BaseViewAction;

import java.util.List;

/**
 * Created by Paras on 8/19/2017.
 */

public interface TabFragmentViewAction extends BaseViewAction{

    public void startAnalysisNotDone();
    void confirmVisible();
    public void setUniversitiesRecyclerView(List<University> universities);

}
