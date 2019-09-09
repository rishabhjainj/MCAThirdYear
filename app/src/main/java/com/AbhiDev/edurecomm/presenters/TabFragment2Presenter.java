package com.AbhiDev.edurecomm.presenters;

import com.wireout.Recommendation.TabFragmentViewAction;
import com.wireout.apiservices.Repository;
import com.wireout.common.AbstractCallback;
import com.wireout.common.EntitiesLoader;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.Career;
import com.wireout.models.DailyGoals;
import com.wireout.models.Slot;
import com.wireout.models.SlotBook;
import com.wireout.viewactions.CareerViewAction;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Response;

public class TabFragment2Presenter extends BasePresenter {

    TabFragmentViewAction viewAction;

    public TabFragment2Presenter(TabFragmentViewAction viewAction, Repository repository){
        this.viewAction = viewAction;
        this.repository = repository;
    }
    public void getSlots(OnEntitiesReceivedListener<Slot> listener){
        repository.getSlots(new HashMap<String, String>(),new EntitiesLoader<>(listener));
    }
    public void bookSlot(String callSlot,String mode,String contact){
       repository.bookSlot(new AbstractCallback(viewAction) {
           @Override
           public void onResponse(Call call, Response response) {
               viewAction.showMessage(response.message()+","+response.code());
               viewAction.confirmVisible();
           }
       },callSlot,mode,contact);
    }


}
