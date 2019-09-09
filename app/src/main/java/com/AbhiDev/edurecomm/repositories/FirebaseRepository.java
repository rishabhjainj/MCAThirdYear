package com.AbhiDev.edurecomm.repositories;

import com.wireout.apiservices.ApiClient;
import com.wireout.apiservices.ApiEndpoint;
import com.wireout.apiservices.responses.GenericResponse;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Rishabh on 3/6/2018.
 */

public class FirebaseRepository {
    ApiEndpoint apiService = ApiClient.getClient().create(ApiEndpoint.class);

    public void sendRegistrationIdToServer(String token, Callback callback){
        Call<GenericResponse> call = apiService.sendTokenToServer(token);
        call.enqueue(callback);

    }

}
