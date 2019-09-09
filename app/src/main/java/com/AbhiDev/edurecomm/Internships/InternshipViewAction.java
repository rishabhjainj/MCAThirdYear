package com.AbhiDev.edurecomm.Internships;

import com.wireout.models.Internships;
import com.wireout.viewactions.BaseViewAction;

import java.util.List;

public interface InternshipViewAction extends BaseViewAction {
    public void clearRecyclerView();
    public void initUi(Internships internships);
    public void setInternshipRecyclerView(List<Internships> internships);
}
