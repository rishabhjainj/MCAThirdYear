package com.AbhiDev.edurecomm.presenters;

import android.content.Context;
import android.util.Log;


import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Rishabh on 4/19/2018.
 */

public class SearchPresenter {
    SearchViewAction viewAction;
    Context context;
    Repository repository;
    public SearchPresenter(SearchViewAction viewAction,Context context , Repository repository){
        this.viewAction = viewAction;
        this.context = context;
        this.repository = repository;
    }
    public void searchUniversity(Map<String,String> queryMap){
        viewAction.showLoader();
        repository.searchUniversity(queryMap, new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                viewAction.hideLoader();
                PaginatedResponse<Institution> arrayResponse = (PaginatedResponse<Institution>) response.body();
                if(arrayResponse!=null){
                    if(arrayResponse.getResults().size()==0){
                        viewAction.showMessage("No Results");
                    }
                    else
                    viewAction.setUniversityRecyclerView(arrayResponse.getResults());
                }
                else{
                    viewAction.showMessage("null");
                }
            }

        });
    }
    public void searchAmbassdors(Map<String,String> queryMap){
        viewAction.showLoader();
        repository.getCareer(queryMap, new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                viewAction.hideLoader();
                Log.d("responseCode",response.code()+","+response.message());
                PaginatedResponse<CareerList> arrayResponse = (PaginatedResponse<CareerList>) response.body();
                if(arrayResponse!=null){
                    if(arrayResponse.getResults().size()==0){
                        viewAction.showMessage("No Results");
                    }
                    else
                    viewAction.setCampaignersRecyclerView(arrayResponse.getResults());
                }
                else{
                    //viewAction.showMessage("null");
                }
            }

        });
    }
    public void searchCourses(Map<String,String> queryMap){
        viewAction.showLoader();
        repository.getCourses(queryMap, new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                viewAction.hideLoader();
                Log.d("responseCode",response.code()+","+response.message());
                PaginatedResponse<CourseList> arrayResponse = (PaginatedResponse<CourseList>) response.body();
                if(arrayResponse!=null){
                    if(arrayResponse.getResults().size()==0){
                        viewAction.showMessage("No Results");
                    }
                    else
                    viewAction.setCoursesRecyclerView(arrayResponse.getResults());
                }
                else{
                    //viewAction.showMessage("null");
                }
            }
        });
    }
    public void searchShortTermCourses(Map<String,String> queryMap){
        viewAction.showLoader();
        repository.getSchoolsList(queryMap, new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                viewAction.hideLoader();
                Log.d("responseCode",response.code()+","+response.message());
                PaginatedResponse<CategoryList> arrayResponse = (PaginatedResponse<CategoryList>) response.body();
                if(arrayResponse!=null){
                    if(arrayResponse.getResults().size()==0){
                        viewAction.showMessage("No Results");

                    }
                    else
                    viewAction.setShortTermCoursesRecyclerView(arrayResponse.getResults());
                }
                else{
                    //viewAction.showMessage("null");
                }
            }
        });
    }
    public void likeUniversity(int id){repository.likeUniversity(id, new LikeEntityManager(viewAction) );}

    public void likeCourse(int id){
        repository.likeCourse(id, new LikeEntityManager(viewAction));
    }

    public void likeShortTermCourse(int id){repository.likeShortTermCourse(id, new LikeEntityManager(viewAction) );}

    public void likeCampaigners(int id){repository.likeCampaigners(id, new LikeEntityManager(viewAction));}


}
