package com.AbhiDev.edurecomm.fourpicsoneword;

import com.wireout.viewactions.BaseViewAction;

import java.util.ArrayList;

/**
 * Created by sharda on 24/07/17.
 */

public interface ViewAction extends BaseViewAction {
    void setScreen(GameLevelModel level);
    void showLevelSuccess();
    void showLevelFailure();
    void setNextButton();
    String updatePlayWord(String character);
    void gameOver();
    void removeListenersFromTextViews();
    void showHintScreen(ArrayList<String> jumbledWords);
}
