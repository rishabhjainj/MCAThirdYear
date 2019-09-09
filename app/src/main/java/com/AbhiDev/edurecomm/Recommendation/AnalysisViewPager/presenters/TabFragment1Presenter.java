package com.AbhiDev.edurecomm.Recommendation.AnalysisViewPager.presenters;

import android.content.Context;
import android.util.Log;

import com.wireout.common.EntityLoader;
import com.wireout.listeners.OnEntityReceivedListener;
import com.wireout.models.Career;
import com.wireout.models.CareerList;
import com.wireout.models.Course;
import com.wireout.models.CourseList;
import com.wireout.models.ShortTermCourse;
import com.wireout.models.University;
import com.wireout.models.career_analysis.Report;
import com.wireout.responses.PaginatedResponse;
import com.wireout.Recommendation.TabFragmentViewAction;
import com.wireout.common.AbstractCallback;
import com.wireout.common.EntitiesLoader;
import com.wireout.common.LikeEntityManager;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.presenters.BasePresenter;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by sharda on 17/03/18.
 */

public class TabFragment1Presenter extends BasePresenter {
    OnEntitiesReceivedListener<ShortTermCourse> shortTermCoursesListener;
    OnEntitiesReceivedListener<Course> coursesListener;
    TabFragmentViewAction viewAction;
    public TabFragment1Presenter(Context context, TabFragmentViewAction viewAction
                                 ){
        this.viewAction = viewAction;
        this.shortTermCoursesListener = shortTermCoursesListener;
        this.coursesListener = coursesListener;
    }
    public void getCourses(OnEntitiesReceivedListener<CourseList> listener){
        repository.getCourses(new HashMap<String, String>(),new EntitiesLoader<>(listener));
    }

    public void getCareer(OnEntitiesReceivedListener<CareerList> listener){
        repository.getCareer(new HashMap<String, String>(),new EntitiesLoader<>(listener));
    }
    public void likeCourse(int id){
        repository.likeCourse(id, new LikeEntityManager(viewAction));
    }
    public void getAnalysisReport(OnEntityReceivedListener<Report> listener){
        repository.getAnalysisReport(new EntityLoader(listener));
    }
    public void likeShortTermCourse(int id){repository.likeShortTermCourse(id, new LikeEntityManager(viewAction) );}

    public void loadShortTermCourses(Map<String, String> uriQuery){
        repository.getShortTermCourses(uriQuery, new EntitiesLoader<>(shortTermCoursesListener));
    }

    public void loadCourses(Map<String, String> uriQuery){
        repository.getCourses(uriQuery, new EntitiesLoader<>(coursesListener));
    }
    public void getUniversities(){
        repository.getUniversities(new HashMap<String, String>(), new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("responseCode",response.code()+","+response.message());
                PaginatedResponse<University> arrayResponse = (PaginatedResponse<University>) response.body();
                if(arrayResponse!=null){
                    viewAction.setUniversitiesRecyclerView(arrayResponse.getResults());
                }
                else{
                    //viewAction.showMessage("null");
                }
            }
        });
    }
}
