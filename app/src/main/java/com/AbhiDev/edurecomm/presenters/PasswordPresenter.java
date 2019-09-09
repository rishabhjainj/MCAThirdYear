package com.AbhiDev.edurecomm.presenters;

import com.wireout.apiservices.ResetPasswordResponse;
import com.wireout.apiservices.responses.ForgotPasswordResponse;
import com.wireout.common.AbstractCallback;
import com.wireout.viewactions.BaseViewAction;

import retrofit2.Call;
import retrofit2.Response;

public class PasswordPresenter extends BasePresenter{

    BaseViewAction baseViewAction;
    public PasswordPresenter(BaseViewAction baseViewAction){
        this.baseViewAction = baseViewAction;

    }
    public void forgotPassword(String email){
        repository.forgotPasswordRequest(new AbstractCallback(baseViewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                if(response!=null){
                    ForgotPasswordResponse forgotPasswordResponse = (ForgotPasswordResponse)response.body();
                    if(response.code()==200){
                        baseViewAction.showMessage( forgotPasswordResponse.getStatus());
                    }
                    else {
                        baseViewAction.showMessage("Invalid Email Address");
                    }

                }
                else{
                    baseViewAction.showMessage("null");
                }
            }
        },email);
    }

    public void resetPassword( String oldPassword, String newPassword){
        repository.resetPasswordRequest(new AbstractCallback(baseViewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                ResetPasswordResponse resetPasswordResponse = (ResetPasswordResponse)response.body();
                if(response.code()==200){
                    baseViewAction.showMessage( resetPasswordResponse.getStatus());
                }
                else{
                    baseViewAction.showMessage("Old password not correct.");
                }
            }
        }, oldPassword, newPassword);
    }
}
