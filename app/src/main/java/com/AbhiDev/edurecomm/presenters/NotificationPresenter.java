package com.AbhiDev.edurecomm.presenters;

import com.wireout.apiservices.Repository;
import com.wireout.common.EntitiesLoader;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.Notification;

public class NotificationPresenter extends BasePresenter {
    public NotificationPresenter(Repository repository){
        this.repository = repository;
    }

    public void getNotifications(OnEntitiesReceivedListener<Notification> listener){
        repository.getNotifications(new EntitiesLoader<>(listener));
    }
}
