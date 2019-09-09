package com.AbhiDev.edurecomm.presenters;

import android.util.Log;

import com.wireout.apiservices.Repository;
import com.wireout.common.AbstractCallback;
import com.wireout.common.EntitiesLoader;
import com.wireout.common.LikeEntityManager;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.Articles;
import com.wireout.models.Category;
import com.wireout.models.CourseList;
import com.wireout.models.Mentor;
import com.wireout.models.CategoryList;
import com.wireout.viewactions.CategoryViewAction;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Response;

public class CategoryPresenter extends BasePresenter {
    CategoryViewAction viewAction;

    public CategoryPresenter(CategoryViewAction viewAction, Repository repository){
        this.repository = repository;
        this.viewAction= viewAction;
    }

    public void likeCampaigners(int id){repository.likeCampaigners(id, new LikeEntityManager(viewAction));}
    public void getCampaigners(OnEntitiesReceivedListener<Mentor> listener){
        repository.getMentors(new HashMap<String, String>(),new EntitiesLoader<>(listener));
    }
    public void getCategory(OnEntitiesReceivedListener<CourseList> listener){
        repository.getCourses(new HashMap<String, String>(),new EntitiesLoader<>(listener));
    }
    public void getCategoryById(String id){
        repository.getCategoryById(id,new AbstractCallback(viewAction){
            @Override
            public void onResponse(Call call, Response response) {
                Category category = (Category) response.body();
                if(category!=null){
                    viewAction.initUi(category);
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
    public void getCategoriesList(OnEntitiesReceivedListener<CategoryList> listener){
        Log.d("categoryList","called");
        repository.getSchoolsList(new HashMap<String, String>(),new EntitiesLoader<>(listener));
    }
}
