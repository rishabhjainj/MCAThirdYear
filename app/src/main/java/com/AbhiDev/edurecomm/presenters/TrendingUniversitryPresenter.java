package com.AbhiDev.edurecomm.presenters;

import android.content.Context;

import com.wireout.apiservices.Repository;
import com.wireout.common.AbstractCallback;
import com.wireout.common.EntitiesLoader;
import com.wireout.common.LikeEntityManager;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.University;
import com.wireout.responses.PaginatedResponse;
import com.wireout.viewactions.TrendingUniversitiesViewAction;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by rahul on 10/3/18.
 */

public class TrendingUniversitryPresenter {

    TrendingUniversitiesViewAction viewAction;
    Context context;
    Repository repository;

    public TrendingUniversitryPresenter(TrendingUniversitiesViewAction viewAction, Context context, Repository repository) {
        this.viewAction = viewAction;
        this.context = context;
        this.repository = repository;
    }

    public void loadMoreUniversities(int page,OnEntitiesReceivedListener<University> listener){
        Map<String,String> uriQuery = new HashMap<>();
        uriQuery.put("page",page+"");
        repository.getUniversities(uriQuery, new EntitiesLoader<>(listener));
    }
    public void likeUniversity(int id){
        repository.likeUniversity(id, new LikeEntityManager(viewAction));
    }
    public void getUniversities(OnEntitiesReceivedListener<University> listener) {
        repository.getUniversities(new HashMap<String, String>(), new EntitiesLoader<>(listener));
    }
}
