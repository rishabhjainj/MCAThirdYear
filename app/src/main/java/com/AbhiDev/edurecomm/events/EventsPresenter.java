package com.AbhiDev.edurecomm.events;
import java.util.Map;

public class EventsPresenter {
    Repository repository;

    public EventsPresenter(Repository repository){
        this.repository = repository;
    }

    public void getEvents(Map<String,String> queryMap, OnEntitiesReceivedListener<Event> listener){
        repository.getEvents(queryMap,new EntitiesLoader<>(listener));
    }
}
