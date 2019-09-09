package com.AbhiDev.edurecomm.Exams;

import com.wireout.apiservices.Repository;
import com.wireout.common.EntitiesLoader;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.Course;
import com.wireout.models.CourseList;
import com.wireout.models.exams.ExamSubCategory;
import com.wireout.presenters.BasePresenter;
import com.wireout.viewactions.CourseViewAction;

import junit.framework.Test;

import java.util.Map;

public class ExamPresenter extends BasePresenter {
    ExamViewAction viewAction;
    Repository repository;

    public ExamPresenter(ExamViewAction viewAction,Repository repository){
        this.repository = repository;
        this.viewAction = viewAction;

    }
    public void submitTestResponse(OnEntitiesReceivedListener<TestResponse> listener){

    }
    public void getExamCategories(OnEntitiesReceivedListener<CategoryModel> listener){
        repository.getExamCategories(new EntitiesLoader<>(listener));
    }
    public void getExamSubCategories(OnEntitiesReceivedListener<ExamSubCategory> listener, Map<String,String> query){
        repository.getExamSubCategories(query,new EntitiesLoader<>(listener));
    }
}
