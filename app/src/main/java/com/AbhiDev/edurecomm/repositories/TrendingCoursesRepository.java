package com.AbhiDev.edurecomm.repositories;

import com.wireout.apiservices.ApiClient;
import com.wireout.apiservices.ApiEndpoint;

import java.util.Map;

import retrofit2.Callback;

/**
 * Created by rahul on 10/3/18.
 */

public class TrendingCoursesRepository {

    ApiEndpoint apiEndpoint= ApiClient.getClient().create(ApiEndpoint.class);

    public void getCourses(Map<String, String> uriQuery, Callback callback)
    {
        apiEndpoint.getCourses(uriQuery).enqueue(callback);
    }
    public void likeCourse(int id, Callback callback){
        apiEndpoint.likeCourse(id).enqueue(callback);
    }
}
