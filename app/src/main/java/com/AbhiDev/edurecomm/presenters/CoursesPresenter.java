package com.AbhiDev.edurecomm.presenters;

import com.wireout.apiservices.Repository;
import com.wireout.common.AbstractCallback;
import com.wireout.common.EntitiesLoader;
import com.wireout.common.LikeEntityManager;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.Articles;
import com.wireout.models.Course;
import com.wireout.models.CourseList;
import com.wireout.models.Mentor;
import com.wireout.models.University;
import com.wireout.responses.PaginatedResponse;
import com.wireout.viewactions.CourseViewAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class CoursesPresenter extends BasePresenter {
    OnEntitiesReceivedListener<Course> courseListener;
    CourseViewAction viewAction;

    public CoursesPresenter(CourseViewAction viewAction, Repository repository){
        this.repository = repository;
        this.viewAction= viewAction;
    }
    public void searchCourses(Map<String,String> queryMap,OnEntitiesReceivedListener<CourseList> listener){
        repository.getCourses(queryMap,new EntitiesLoader<>(listener));
    }

    public void loadMoreCourses(Map<String,String> map,int page,OnEntitiesReceivedListener<CourseList> listener){
        //viewAction.showMessage("load more called");
        map = new HashMap<>();
        map.put("page", page + "");
        repository.getCourses(map,new EntitiesLoader<>(listener));
    }
    public void getUniversityById(String id){
        repository.getUniversityById(id,new AbstractCallback(viewAction){
            @Override
            public void onResponse(Call call, Response response) {
                University university = (University) response.body();
                if(university!=null){
                    viewAction.feedUniName(university.getName());
                }
                else{
                    viewAction.showMessage("null");
                }
            }
        });
    }
    public void applyFilters(Map<String,String> map,ArrayList<String> categories,OnEntitiesReceivedListener<CourseList> listener){
        applyFilters(map,categories,1,listener);
    }
    public void applyFilters(Map<String,String> map,ArrayList<String> categories,int page ,OnEntitiesReceivedListener<CourseList> listener){
        map.put("page", page + "");
        repository.getCourses(map,categories,new EntitiesLoader<>(listener));
    }
    public void likeCourse(int id){
        repository.likeCourse(id, new LikeEntityManager(viewAction));
    }
    public void likeCampaigners(int id){repository.likeCampaigners(id, new LikeEntityManager(viewAction));}
    public void getCampaigners(OnEntitiesReceivedListener<Mentor> listener){
        repository.getMentors(new HashMap<String, String>(),new EntitiesLoader<>(listener));
    }
    public void getCourses(OnEntitiesReceivedListener<CourseList> listener){
        repository.getCourses(new HashMap<String, String>(),new EntitiesLoader<>(listener));
    }
    public void getCoursesById(String id){
        repository.getCourseById(id,new AbstractCallback(viewAction){
            @Override
            public void onResponse(Call call, Response response) {
                Course course = (Course)response.body();
                if(course!=null){
                    viewAction.initUi(course);
                }
                else{
                    viewAction.showMessage("null");
                }
            }
        });
    }

    public void getArticles(OnEntitiesReceivedListener<Articles> listener){
        repository.getArticles(new HashMap<String, String>(),new EntitiesLoader<>(listener));
    }
}
