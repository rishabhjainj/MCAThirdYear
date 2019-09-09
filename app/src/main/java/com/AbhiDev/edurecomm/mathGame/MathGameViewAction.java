package com.AbhiDev.edurecomm.mathGame;

/**
 * Created by Paras on 6/26/2017.
 */

public interface MathGameViewAction {

    void setProblem(String operand1, String operand2, String operator);
    void showToast(String message);
    void showResults(String correct, String incorrect);
    void updateTimer(String time);
}
