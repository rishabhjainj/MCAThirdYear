package com.AbhiDev.edurecomm.repositories;

import com.wireout.apiservices.ApiClient;
import com.wireout.apiservices.ApiEndpoint;

import java.util.Map;

import retrofit2.Callback;

/**
 * Created by rahul on 10/3/18.
 */

public class TrendingUniversitiesRepository {

    ApiEndpoint apiEndpoint= ApiClient.getClient().create(ApiEndpoint.class);

    public void getUniversities(Map<String, String> uriQuery,Callback callback)
    {
        apiEndpoint.getUniversities(uriQuery).enqueue(callback);
    }

}
