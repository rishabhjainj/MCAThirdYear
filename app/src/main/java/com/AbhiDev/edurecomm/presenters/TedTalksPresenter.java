package com.AbhiDev.edurecomm.presenters;

import com.wireout.apiservices.Repository;
import com.wireout.common.EntitiesLoader;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.TedYouTube;

import java.util.HashMap;
import java.util.Map;

public class TedTalksPresenter extends BasePresenter{

    public TedTalksPresenter(Repository repository){
        this.repository = repository;
    }

    public void getTedVideos(OnEntitiesReceivedListener<TedYouTube> listener){
        repository.getTedVideos(new HashMap<String, String>(),new EntitiesLoader<>(listener));
    }
    public void loadMoreTed(int page, OnEntitiesReceivedListener<TedYouTube> listener){
        Map<String,String> uriQuery = new HashMap<>();
        uriQuery.put("page",page+"");
        repository.getTedVideos(uriQuery,new EntitiesLoader<>(listener));
    }

}
