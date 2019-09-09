package com.AbhiDev.edurecomm.presenters;

import com.wireout.apiservices.Repository;
import com.wireout.common.EntitiesLoader;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.Articles;

import java.util.HashMap;
import java.util.Map;

public class TrendingArticlesPresenter extends BasePresenter {
    OnEntitiesReceivedListener<Articles> articlesListner;
    TrendingArticlesViewAction viewAction;

    public TrendingArticlesPresenter(Repository repository){
        this.repository = repository;
    }

    public void getArticles(OnEntitiesReceivedListener<Articles> listener){
        repository.getArticles(new HashMap<String, String>(),new EntitiesLoader<>(listener));
    }
    public void loadMoreArticles(int page,OnEntitiesReceivedListener<Articles> listener){
        Map<String,String> uriQuery = new HashMap<>();
        uriQuery.put("page",page+"");
        repository.getArticles(uriQuery,new EntitiesLoader<>(listener));
    }

}
