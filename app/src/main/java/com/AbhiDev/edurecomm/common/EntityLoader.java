package com.AbhiDev.edurecomm.common;

import android.util.Log;

import com.wireout.listeners.OnEntityReceivedListener;

import retrofit2.Call;
import retrofit2.Response;

public class EntityLoader<T> extends AbstractCallback {

    OnEntityReceivedListener<T> listener;
    public static final String TAG = EntityLoader.class.getSimpleName();

    public EntityLoader(OnEntityReceivedListener<T> listener) {
        super(listener);
        this.listener = listener;
    }

    @Override
    public void onResponse(Call call, Response response) {
        Log.d(TAG, response.code() + " " + response.message());
        if(response.code() >= 200&&response.code()<=299) {
            T entity = (T) response.body();
            listener.onReceived(entity);
        }
        else
            listener.showNetworkError("Please check your connection!");

    }
}
