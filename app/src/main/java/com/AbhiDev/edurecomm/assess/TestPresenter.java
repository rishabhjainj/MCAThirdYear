package com.AbhiDev.edurecomm.assess;

import com.wireout.common.EntitiesLoader;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.Assessment;
import com.wireout.models.career_analysis.BooleanQuestion;
import com.wireout.presenters.BasePresenter;

public class TestPresenter extends BasePresenter {
    public void getAssessmentSections(OnEntitiesReceivedListener<Assessment> listener){
        repository.getAssessmentSections(new EntitiesLoader<>(listener));
    }

}
