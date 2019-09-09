package com.AbhiDev.edurecomm.Internships;

import com.wireout.Exams.CategoryModel;
import com.wireout.Exams.ExamViewAction;
import com.wireout.Exams.TestResponse;
import com.wireout.apiservices.Repository;
import com.wireout.common.AbstractCallback;
import com.wireout.common.EntitiesLoader;
import com.wireout.common.LikeEntityManager;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.Course;
import com.wireout.models.Institution;
import com.wireout.models.Internships;
import com.wireout.models.exams.ExamSubCategory;
import com.wireout.presenters.BasePresenter;
import com.wireout.responses.PaginatedResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class InternshipPresenter extends BasePresenter {
    InternshipViewAction viewAction;
    Repository repository;

    public InternshipPresenter(InternshipViewAction viewAction,Repository repository){
        this.repository = repository;
        this.viewAction = viewAction;

    }
    public void likeInternship(int id){repository.likeInternship(id, new LikeEntityManager(viewAction));}
    public void getInternships(OnEntitiesReceivedListener<Internships> listener){
        repository.getInternships(new HashMap<String, String>(),new EntitiesLoader<>(listener));
    }
    public void searchInternships(Map<String,String> queryMap){
        //viewAction.showLoader();
        repository.searchInternships(queryMap, new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                //viewAction.hideLoader();
                PaginatedResponse<Internships> arrayResponse = (PaginatedResponse<Internships>) response.body();
                if(arrayResponse!=null){
                    if(arrayResponse.getResults().size()==0){
                        viewAction.showMessage("No Results");
                        viewAction.setInternshipRecyclerView(arrayResponse.getResults());
                    }
                    else
                        viewAction.setInternshipRecyclerView(arrayResponse.getResults());
                }
                else{
                    viewAction.showMessage("null");
                }
            }

        });
    }
    public void getInternshipById(String id){
        repository.getInternshipById(id,new AbstractCallback(viewAction){
            @Override
            public void onResponse(Call call, Response response) {
                Internships internships = (Internships)response.body();
                if(internships!=null){
                    viewAction.initUi(internships);
                    //viewAction.showMessage("coming");
                }
                else{
                    viewAction.showMessage("null");
                }
            }
        });
    }
}
