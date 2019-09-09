package com.AbhiDev.edurecomm.viewactions;

import com.wireout.models.Career;
import com.wireout.models.CareerList;

import java.util.List;

public interface CareerViewAction extends BaseViewAction {
    void initUi(Career career);
    void initUi(Career career,Boolean hide);
    void addToRecyclerView(List<CareerList> careers);
}
