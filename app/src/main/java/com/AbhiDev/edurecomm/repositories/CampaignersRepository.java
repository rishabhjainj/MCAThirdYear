package com.AbhiDev.edurecomm.repositories;

import com.wireout.apiservices.ApiClient;
import com.wireout.apiservices.ApiEndpoint;

import java.util.Map;

import retrofit2.Callback;


/**
 * Created by rahul on 10/3/18.
 */

public class CampaignersRepository {

    ApiEndpoint apiEndpoint= ApiClient.getClient().create(ApiEndpoint.class);

    public void getCampaigners(Map<String,String> uriQuery, Callback callback)
    {
        apiEndpoint.getCampaigners(uriQuery).enqueue(callback);
    }

    public void likeCampaigners(int id, Callback callback){
        apiEndpoint.likeCampaigners(id).enqueue(callback);
    }
}
