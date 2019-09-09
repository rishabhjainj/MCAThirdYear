package com.AbhiDev.edurecomm.presenters;

import android.util.Log;

import com.wireout.common.AbstractCallback;
import com.wireout.listeners.auth.OnCreateUserListener;
import com.wireout.models.User;
import com.wireout.viewactions.BaseViewAction;
import com.wireout.viewactions.SignUpViewAction;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author Paras Jain
 * @version 1.0
 * @since 2018-02-24
 */
public class SignUpPresenter extends BasePresenter {

    public static final String TAG = SignUpPresenter.class.getSimpleName();
    SignUpViewAction viewAction;
    OnCreateUserListener onCreateUserListener;

    public SignUpPresenter(OnCreateUserListener onCreateuserListener, SignUpViewAction viewAction){
        this.onCreateUserListener = onCreateuserListener;
        this.viewAction = viewAction;
    }

    public void signUp(Map<String, String> map){
        repository.registerUser(map, new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.code() == 201){
                    //user successfully registered!
                    viewAction.showMessage("Account created successfully!");
                    onCreateUserListener.onUserCreated((User) response.body());
                }
                else {
                    Log.d("errorsign", response.code() + " " + response.message());
                    if(response.code()==400){
                        viewAction.showMessage("Account already exists with this E-mail Id or Phone No.!");
                    }
                    else
                    viewAction.showMessage("There was some problem while creating your account! Please try again.");
                }
            }
        });
    }
}
