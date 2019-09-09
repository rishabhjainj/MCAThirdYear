package com.AbhiDev.edurecomm.presenters;


public class IntroPresenter {

    private IntroNavigator introNavigator;

    public IntroPresenter(IntroNavigator introNavigator){
        this.introNavigator = introNavigator;
    }

    public void start(PrefManager prefManager) {

        if(prefManager.isFirstTimeLaunch()){
            prefManager.setFirstTimeLaunch(false);
            introNavigator.startPreferencesActivity();
        }else{
            introNavigator.startMainActivity();
        }
    }
}
