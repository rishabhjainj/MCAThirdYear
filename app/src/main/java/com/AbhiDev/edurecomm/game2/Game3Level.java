package com.AbhiDev.edurecomm.game2;

/**
 * Created by Paras on 6/29/2017.
 */

public class Game3Level {
    String leftText;
    String rightText;
    String leftColor;
    String rightColor;
    boolean isCorrect;

    Game3Level(String leftText, String leftColor, String rightText, String rightColor, Boolean isCorrect){
        this.leftText = leftText;
        this.leftColor = leftColor;
        this.rightColor = rightColor;
        this.rightText = rightText;
        this.isCorrect = isCorrect;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public String getLeftText() {
        return leftText;
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }

    public String getLeftColor() {
        return leftColor;
    }

    public void setLeftColor(String leftColor) {
        this.leftColor = leftColor;
    }

    public String getRightColor() {
        return rightColor;
    }

    public void setRightColor(String rightColor) {
        this.rightColor = rightColor;
    }
}
