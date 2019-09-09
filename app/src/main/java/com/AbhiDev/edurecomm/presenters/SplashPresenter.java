package com.AbhiDev.edurecomm.presenters;


import com.wireout.common.PrefManager;
import com.wireout.view.SplashNavigator;

/**
 * Created by nikhiljain on 1/27/17.
 */

public class SplashPresenter {
    private SplashNavigator splashNavigator;

    public SplashPresenter(SplashNavigator splashNavigator) {
        this.splashNavigator = splashNavigator;
    }


    public void start(PrefManager prefManager) {


         if (prefManager.isFirstTimeLaunch()) {
            splashNavigator.startIntroActivity();
        }
        else if (!prefManager.isFirstTimeLaunch()) {
             if(prefManager.isLoggedIn()){
                 splashNavigator.startMainActivity();
             }
             else
            splashNavigator.startLoginActivity();
//        } else if(!prefManager.isFirstTimeLaunch() && !prefManager.isNotLogin()) {
//            splashNavigator.startMainActivity();
//        }
        }
    }
}
