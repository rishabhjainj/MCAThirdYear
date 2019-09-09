package com.AbhiDev.edurecomm.randomized_feeds;


import com.wireout.apiservices.Repository;
import com.wireout.common.EntitiesLoader;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.RandomizedFeed;

public class RandomizedFeedsPresenter {

    Repository repository;

    public RandomizedFeedsPresenter(Repository repository){
        this.repository = repository;
    }

    public void getRandomizedFeeds(OnEntitiesReceivedListener<RandomizedFeed> listener){
        repository.getRandomizedFeeds(new EntitiesLoader<>(listener));
    }


}
