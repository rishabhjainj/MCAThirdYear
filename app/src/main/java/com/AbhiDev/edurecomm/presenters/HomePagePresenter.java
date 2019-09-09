package com.AbhiDev.edurecomm.presenters;

import android.content.Context;
import android.util.Log;

import com.wireout.common.AbstractCallback;
import com.wireout.common.EntitiesLoader;
import com.wireout.common.LikeEntityManager;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.BannerImages;
import com.wireout.models.Career;
import com.wireout.models.CareerList;
import com.wireout.models.CourseList;
import com.wireout.models.DailyGoals;
import com.wireout.models.Mentor;
import com.wireout.models.ShortTermCourse;
import com.wireout.models.University;
import com.wireout.repositories.HomePageRepository;
import com.wireout.responses.PaginatedResponse;
import com.wireout.viewactions.UniversityViewAction;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Rishabh on 3/9/2018.
 */

public class HomePagePresenter extends BasePresenter{
    UniversityViewAction viewAction;
    Context context;

    public HomePagePresenter(Context context,UniversityViewAction viewAction,HomePageRepository repository){
        this.viewAction = viewAction;
        this.context = context;
    }
    public void getCampaigners(OnEntitiesReceivedListener<Mentor> listener){
        repository.getMentors(new HashMap<String, String>(),new EntitiesLoader<>(listener));
    }
    public void getBannerImages(OnEntitiesReceivedListener<BannerImages> listener){
        repository.getBannerImages(new EntitiesLoader<>(listener));
    }
    public void getCareer(OnEntitiesReceivedListener<CareerList> listener){
        repository.getCareer(new HashMap<String, String>(),new EntitiesLoader<>(listener));
    }

    public void getDailyGoals(OnEntitiesReceivedListener<DailyGoals> listener){
        repository.getDailyGoals(new HashMap<String, String>(),new EntitiesLoader<>(listener));
    }
    public void getCareerTags(OnEntitiesReceivedListener<DailyGoals> listener){
        repository.getCareerTags(new HashMap<String, String>(),new EntitiesLoader<>(listener));
    }
//    public void getCourses(){
//        repository.getCourses(new HashMap<String, String>(), new AbstractCallback(viewAction) {
//            @Override
//            public void onResponse(Call call, Response response) {
//                Log.d("responseCode",response.code()+","+response.message());
//                PaginatedResponse<Course> arrayResponse = (PaginatedResponse<Course>) response.body();
//                if(arrayResponse!=null){
//                    viewAction.setCoursesRecyclerView(arrayResponse.getResults());
//                }
//                else{
//                    //viewAction.showMessage("null");
//                }
//            }
//        });
//    }
public void getCourses(OnEntitiesReceivedListener<CourseList> listener){
    repository.getCourses(new HashMap<String, String>(),new EntitiesLoader<>(listener));
}
    public void getShortTermCourses(){
        repository.getShortTermCourses(new HashMap<String, String>(), new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("responseCode",response.code()+","+response.message());
                PaginatedResponse<ShortTermCourse> arrayResponse = (PaginatedResponse<ShortTermCourse>) response.body();
                if(arrayResponse!=null){
                    viewAction.setShortTermCoursesRecyclerView(arrayResponse.getResults());
                }
                else{
                    //viewAction.showMessage("null");
                }
            }
        });
    }
//    public void getCampaigners(){
//        repository.getCampaigners(new HashMap<String, String>(), new AbstractCallback(viewAction) {
//            @Override
//            public void onResponse(Call call, Response response) {
//                Log.d("responseCode",response.code()+","+response.message());
//                PaginatedResponse<Mentor> arrayResponse = (PaginatedResponse<Mentor>) response.body();
//                if(arrayResponse!=null){
//                    viewAction.setCampaignersRecyclerView(arrayResponse.getResults());
//                }
//                else{
//                    //viewAction.showMessage("null");
//                }
//            }
//
//        });
//    }
    public void likeUniversity(int id){repository.likeUniversity(id, new LikeEntityManager(viewAction) );}

    public void likeCourse(int id){
        repository.likeCourse(id, new LikeEntityManager(viewAction));
    }

    public void likeShortTermCourse(int id){repository.likeShortTermCourse(id, new LikeEntityManager(viewAction) );}

    public void likeCampaigners(int id){repository.likeCampaigners(id, new LikeEntityManager(viewAction));}
    
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
