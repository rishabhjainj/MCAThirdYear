package com.AbhiDev.edurecomm.models.career_analysis;

import android.util.Log;

import com.wireout.apiservices.Repository;
import com.wireout.common.EntityLoader;
import com.wireout.listeners.OnEntityReceivedListener;

import java.util.Map;

import okhttp3.MultipartBody;

public class Section13 extends AbstractSection<Section13>{

    String writingHand;
    String image; //image URL

    public String getWritingHand() {
        return writingHand;
    }

    public void setWritingHand(String writingHand) {
        this.writingHand = writingHand;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Section13(int numScreens) {
        super(numScreens);
    }

    @Override
    boolean isScreenValidated(int screenId) {
        return false;
    }

    @Override
    public void submit(OnEntityReceivedListener<Section13> listener) {
        Log.d("Section13", "Not supported");
        return;
    }

    /** need to submit handwriting image using PartMap
     * Refer Repository method and corresponding ApiEndpoint method
     */
    public void submit(Map<String,String> map, MultipartBody.Part imageBody, OnEntityReceivedListener<Section13> listener) {
        (new Repository()).postAnalysisSection13(map, imageBody, new EntityLoader(listener));
    }
}
