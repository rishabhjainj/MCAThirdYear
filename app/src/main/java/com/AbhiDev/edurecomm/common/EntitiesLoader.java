package com.AbhiDev.edurecomm.common;

import android.util.Log;

import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.responses.PaginatedResponse;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by sharda on 17/03/18.
 */

public class EntitiesLoader<T> extends AbstractCallback{

    OnEntitiesReceivedListener<T> listener;
    public static final String TAG = EntitiesLoader.class.getSimpleName();

    public EntitiesLoader(OnEntitiesReceivedListener<T> listener){
        super(listener);
        this.listener = listener;
    }
    @Override
    public void onResponse(Call call, Response response) {
        Log.d(TAG, response.code() + " " + response.message());
        if(response.code() >= 200&&response.code()<=299) {
            PaginatedResponse<T> paginatedResponse = (PaginatedResponse<T>) response.body();
            listener.onReceived(paginatedResponse.getResults());
        }
        else
            listener.showNetworkError("Please check your connection!");
    }
}
