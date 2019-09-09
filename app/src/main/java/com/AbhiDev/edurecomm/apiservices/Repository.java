package com.AbhiDev.edurecomm.apiservices;

import android.util.Log;

import com.wireout.common.AbstractCallback;
import com.wireout.common.Constants;
import com.wireout.models.Career;
import com.wireout.Exams.TestResponse;
import com.wireout.models.User;
import com.wireout.models.career_analysis.BooleanQuestionResponse;
import com.wireout.models.career_analysis.BooleanSectionResponse;
import com.wireout.models.career_analysis.CareerAnalysis;
import com.wireout.models.career_analysis.Section1;
import com.wireout.models.career_analysis.Section13;
import com.wireout.models.career_analysis.Section9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by sharda on 24/02/18.
 */

public class Repository {
    private static final String TAG = Repository.class.getSimpleName();
    ApiEndpoint apiService = ApiClient.getClient().create(ApiEndpoint.class);

    public Repository(){

    }

    private Map<String, RequestBody> getPartMap(Map<String, String> map){
        Map<String, RequestBody> newMap = new HashMap<>();
        //create request body map for Multipart Request
        for(String key : map.keySet())
            newMap.put(key, map.get(key) != null ? createPartFromString(map.get(key)) : createPartFromString(""));
        return newMap;
    }

    private RequestBody createPartFromString(String text){
        return  RequestBody.create(
                MediaType.parse("multipart/form-data"),  text );

    }

    /**
     *
     * @param map
     * @param callback
     *
     * <p>Map object should contain necessary POST parameters for user registration</p>
     */

    public void registerUser(Map<String, String> map, Callback callback){
        Log.d(TAG, "registerUser : " + map.toString());
        Call<User> call = apiService.registerUser(getPartMap(map));
        call.enqueue(callback);
    }

    /**
     *
     * @param map
     * @param callback
     *
     * <p>Expects username and password in map object.</p>
     */
    public void loginDefault(Map<String, String> map, Callback callback){

        map.put(Constants.OAUTH2_CLIENT_ID_PARAM_KEY, Constants.YANTRA_OAUTH2_CLIENT_ID);
        map.put(Constants.OAUTH2_CLIENT_SECRET_PARAM_KEY, Constants.YANTRA_OAUTH2_CLIENT_SECRET);
        map.put(Constants.OAUTH2_GRANT_TYPE_PARAM_KEY, Constants.PASSWORD_GRANT);

        Log.d(TAG, "loginDefault : " + map.toString());

        apiService.getToken(map).enqueue(callback);

    }

    /**
     *
     * @param facebookAccessToken
     * @param callback
     *
     * <p>Expects 'facebookAccessToken' string</p>
     */
    public void loginFacebook(String facebookAccessToken, Callback callback){
        Map<String, String> map = new HashMap<>();
        map.put(Constants.OAUTH2_CLIENT_ACCESS_TOKEN_PARAM_KEY, facebookAccessToken);
        map.put(Constants.OAUTH2_BACKEND_PARAM_KEY, Constants.FACEBOOK_AUTH_BACKEND);
        convertToken(map,callback);
    }

    /**
     *
     * @param googleAccessToken
     * @param callback
     *
     * <p>Expects 'googleAccessToken' string</p>
     */
    public void loginGoogle(String googleAccessToken, Callback callback){
        Map<String, String> map = new HashMap<>();
        map.put(Constants.OAUTH2_CLIENT_ACCESS_TOKEN_PARAM_KEY, googleAccessToken);
        map.put(Constants.OAUTH2_BACKEND_PARAM_KEY, Constants.GOOGLE_AUTH_BACKEND);
        convertToken(map,callback);
    }


    /**
     *
     * @param map
     * @param callback
     *
     * Expects 'token' and 'backend' in map object. Called by loginFacebook and loginGoogle.
     */
    protected void convertToken(Map<String, String> map, Callback callback){
        map.put(Constants.OAUTH2_CLIENT_ID_PARAM_KEY, Constants.YANTRA_OAUTH2_CLIENT_ID);
        map.put(Constants.OAUTH2_CLIENT_SECRET_PARAM_KEY, Constants.YANTRA_OAUTH2_CLIENT_SECRET);
        map.put(Constants.OAUTH2_GRANT_TYPE_PARAM_KEY, Constants.CONVERT_TOKEN_GRANT);
        Log.d(TAG, "convertToken: " + map.toString());
        apiService.convertToken(map).enqueue(callback);
    }

    public void getCurrentUser(Callback callback){
        apiService.getCurrentUser().enqueue(callback);
    }

    public void getCourses(Map<String,String> uriQuery, AbstractCallback callback){
        apiService.getCourses(uriQuery).enqueue(callback);

    }
    public void getCourses(Map<String,String> uriQuery, ArrayList<String> categories, AbstractCallback callback){
        apiService.getCourses(uriQuery,categories).enqueue(callback);
    }

    public void getExamSubCategories(Map<String,String> uriQuery,AbstractCallback callback){
        apiService.getExamSubCategories(uriQuery).enqueue(callback);
    }
    public void getExamCategories(AbstractCallback callback){
        apiService.getExamCategories().enqueue(callback);
    }
    public void getCareer(Map<String,String> uriQuery, AbstractCallback callback){
        apiService.getCareer(uriQuery).enqueue(callback);

    }
    public void getSlots(Map<String,String> uriQuery, AbstractCallback callback){
        apiService.getSlots(uriQuery).enqueue(callback);

    }
    public void getCareer(Map<String,String> uriQuery,ArrayList<String> categories, AbstractCallback callback){
        apiService.getCareer(uriQuery,categories).enqueue(callback);

    }

    public void getDailyGoals(Map<String,String> uriQuery, AbstractCallback callback){
        apiService.getDailyGoals(uriQuery).enqueue(callback);

    }
    public void searchUniversity(Map<String,String> uriQuery, Callback callback){
        apiService.searchUniversities(uriQuery).enqueue(callback);

    }
    public void searchInternships(Map<String,String> uriQuery, Callback callback){
        apiService.searchInternships(uriQuery).enqueue(callback);

    }
    public void getVideos(Map<String,String> uriQuery, Callback callback){
        apiService.getVideos(uriQuery).enqueue(callback);
    }
    public void getShortTermCourses(Map<String,String> uriQuery, Callback callback){
        apiService.getShortTermCourses(uriQuery).enqueue(callback);
    }
    public void getTags(Map<String,String> uriQuery, Callback callback){
        apiService.getTags(uriQuery).enqueue(callback);
    }
    public void getArticles(Map<String,String> uriQuery, AbstractCallback callback){
        apiService.getArticles(uriQuery).enqueue(callback);
    }
    public void getCareerTags(Map<String,String> uriQuery, AbstractCallback callback){
        apiService.getCareerTags(uriQuery).enqueue(callback);
    }
    public void getMentors(Map<String,String> uriQuery, AbstractCallback callback){
        apiService.getMentors(uriQuery).enqueue(callback);
    }

    public void getTedVideos(Map<String,String> uriQuery, AbstractCallback callback){
        apiService.getTedVideos(uriQuery).enqueue(callback);
    }
    public void getUniversities(Map<String,String> uriQuery, AbstractCallback callback){
        apiService.getUniversities(uriQuery).enqueue(callback);
    }
    public void getCampaigners(Map<String,String> uriQuery, Callback callback){
        apiService.getCampaigners(uriQuery).enqueue(callback);
    }
    public void likeUniversity(int id, Callback callback){
        apiService.likeUniversity(id).enqueue(callback);
    }
    public void getCourseById(String id, Callback callback){
        apiService.getCourseById(id).enqueue(callback);
    }
    public void getCareerById(String id, Callback callback){
        apiService.getCareerById(id).enqueue(callback);
    }
    public void getUniversityById(String id, Callback callback){
        apiService.getUniversityById(id).enqueue(callback);
    }
    public void getCategoryById(String id, Callback callback){
        apiService.getCategoryById(id).enqueue(callback);
    }
    public void getSchoolsList(Map<String ,String> uriQuery,Callback callback){
        apiService.getSchoolsList(uriQuery).enqueue(callback);
    }


    public void likeCourse(int id, Callback callback){
        apiService.likeCourse(id).enqueue(callback);
    }
    public void likeShortTermCourse(int id, Callback callback){
        apiService.likeShortTermCourse(id).enqueue(callback);
    }

    public void likeCampaigners(int id, Callback callback){
        apiService.likeCampaigners(id).enqueue(callback);
    }
    public void likeInternship(int id, Callback callback){
        apiService.likeInternship(id).enqueue(callback);
    }

    public void getRandomizedFeeds(AbstractCallback callback){
        apiService.getRandomizedFeeds().enqueue(callback);
    }
    public void getNotifications(AbstractCallback callback){
        apiService.getNotifications().enqueue(callback);
    }
    public void getBannerImages(AbstractCallback callback){
        apiService.getBannerImages().enqueue(callback);
    }
    public void getQuestionsForSections(AbstractCallback callback){
        apiService.getQuestionsForSections().enqueue(callback);
    }
    public void getAssessmentSections(AbstractCallback callback){
        apiService.getAssessmentSections().enqueue(callback);
    }

    public void getEvents(Map<String,String> queryMap,AbstractCallback callback){
        apiService.getEvents(queryMap).enqueue(callback);
    }
    public void forgotPasswordRequest(AbstractCallback callback, String email){
        apiService.forgotPasswordRequest(email).enqueue(callback);
    }

    public void sendBioDataSectionData(AbstractCallback callback,Map<String,String> map){
        apiService.sendBioDataSectionData(
                map.get("name"),
                map.get("dob"),
                map.get("gender"),
                map.get("jobStatus"),
                map.get("country"),
                map.get("modeOfStudy"),
                map.get("year"),
                map.get("highestEducationLevel"),
                map.get("interest"),
                map.get("subject"),
                map.get("pastTime"),
                map.get("gameCategory"),
                map.get("yearOfPassing10"),
                map.get("yearOfPassing12"),
                map.get("marks10"),
                map.get("marks12")
        ).enqueue(callback);
    }
    public void resetPasswordRequest(AbstractCallback callback, String oldPassword, String newPassword){
        apiService.resetPasswordRequest(oldPassword, newPassword).enqueue(callback);
    }
    public void bookSlot(AbstractCallback callback,String callSlot,String mode, String contact){
        apiService.bookSlot(callSlot, mode,contact).enqueue(callback);
    }


    public void postAnalysisSection1(Section1 section, Callback callback){
        apiService.postAnalysisSection1(section).enqueue(callback);
    }

    public void postAnalysisSection9(Section9 section, Callback callback){
        apiService.postAnalysisSection9(section).enqueue(callback);
    }

    public void postAnalysisSection13(Map<String,String> map, MultipartBody.Part imageBody, Callback callback){
        apiService.postAnalysisSection13(getPartMap(map), imageBody).enqueue(callback);
    }

    public void postAnalysis(Map<String,String> map, Callback callback){
        apiService.postAnalysis(map).enqueue(callback);
    }

    public void getAnalysis(Callback callback){
        apiService.getAnalysis().enqueue(callback);
    }

    public void postBooleanQuestionResponse(BooleanSectionResponse bqr, Callback callback){
        apiService.postBooleanQuestionResponse(bqr).enqueue(callback);
    }
    public void postTestResponse(TestResponse bqr, Callback callback){
        apiService.postTestResponse(bqr).enqueue(callback);
    }

    public void getAnalysisReport(Callback callback){
        apiService.getAnalysisReport().enqueue(callback);
    }
    public void getInternships(Map<String,String> queryMap,Callback callback){
        apiService.getInternships(queryMap).enqueue(callback);
    }
    public void getInternshipById(String id, Callback callback){
        apiService.getInternshipById(id).enqueue(callback);
    }

}
