package com.AbhiDev.edurecomm.presenters;

import com.wireout.common.EntitiesLoader;
import com.wireout.common.EntityLoader;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.listeners.OnEntityReceivedListener;
import com.wireout.models.career_analysis.BooleanQuestion;
import com.wireout.models.career_analysis.CareerAnalysis;
import com.wireout.models.career_analysis.Report;

import java.util.HashMap;
import java.util.Map;

public class BooleanQuestionPresenter extends BasePresenter {

    public void getQuestionsForSections(OnEntitiesReceivedListener<BooleanQuestion> listener){
        repository.getQuestionsForSections(new EntitiesLoader<>(listener));
    }
    public void postAnalysis( OnEntityReceivedListener<CareerAnalysis> listener){
        repository.postAnalysis(new HashMap<String, String>(),new EntityLoader(listener));
    }
    public void postAnalysis(Map<String,String> map, OnEntityReceivedListener<CareerAnalysis> listener){
        repository.postAnalysis(map,new EntityLoader(listener));
    }
    public void getAnalysisReport(OnEntityReceivedListener<Report> listener){
        repository.getAnalysisReport(new EntityLoader(listener));
    }

}
