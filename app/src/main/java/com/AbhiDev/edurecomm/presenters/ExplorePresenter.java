package com.AbhiDev.edurecomm.presenters;

import com.wireout.common.EntitiesLoader;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.Tag;
import com.wireout.viewactions.ExploreViewAction;

import java.util.Map;

/**
 * Created by Rishabh on 3/31/2018.
 */

public class ExplorePresenter extends BasePresenter{
    OnEntitiesReceivedListener<Tag> tagReceivedListener;
    ExploreViewAction viewAction;

    public ExplorePresenter(OnEntitiesReceivedListener<Tag> tagReceivedListener, ExploreViewAction viewAction){
        this.viewAction = viewAction;
        this.tagReceivedListener = tagReceivedListener;
    }

    public void loadTags(Map<String, String> uriQuery){
        repository.getTags(uriQuery, new EntitiesLoader<>(tagReceivedListener));
    }
}
