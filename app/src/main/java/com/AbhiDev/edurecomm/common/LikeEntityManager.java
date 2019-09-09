package com.AbhiDev.edurecomm.common;

import android.util.Log;

import com.wireout.apiservices.responses.LikeResponse;
import com.wireout.viewactions.BaseViewAction;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by sharda on 30/03/18.
 */

public class LikeEntityManager extends AbstractCallback{

    public LikeEntityManager(BaseViewAction viewAction) {
        super(viewAction);
    }

    @Override
    public void onResponse(Call call, Response response) {
        if(response!=null) {
            LikeResponse likeResponse = (LikeResponse) response.body();
            if (likeResponse != null) {
                if (likeResponse.getLiked()) {
                    Log.d("responselike", "added");
                    viewAction.showMessage("Added to Wishlist");
                } else
                    viewAction.showMessage("Removed from Wishlist");
            } else {
                viewAction.showMessage("null");
            }
        }
    }
}
