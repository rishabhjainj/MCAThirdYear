package com.AbhiDev.edurecomm.game2;

/**
 * Created by Paras on 6/29/2017.
 */

public interface Game3ViewAction {

    void showGameScreen(Game3Level currLevel);
    void showToast(String message);
    void updateTimer(String time);
    void gameOver(String correct, String incorrect);
}
