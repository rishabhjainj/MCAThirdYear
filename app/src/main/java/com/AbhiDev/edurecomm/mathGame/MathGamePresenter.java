package com.AbhiDev.edurecomm.mathGame;

import android.os.Handler;
import android.os.Message;


import com.wireout.common.EntityLoader;
import com.wireout.common.PrefManager;
import com.wireout.listeners.OnEntityReceivedListener;
import com.wireout.models.career_analysis.CareerAnalysis;
import com.wireout.presenters.BasePresenter;

import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Paras on 6/26/2017.
 */

public class MathGamePresenter extends BasePresenter{

    MathGameViewAction viewAction;

    int moves;
    int max = 9;
    int min = 0;
    int operand1;
    int operand2;
    int answer;
    int operator;
    public int correctResponses;   //false means wrong answer give, true means correct.
    int incorrectResponses;
    int time = 50; //seconds

    Handler handler;
    Runnable runnable;
    Handler mHandler;
    PrefManager prefManager;

    //preferences keys
    public static final String GAME4_QUESTION_COUNT = "game4_QuestionCount";
    public static final String GAME4_CORRECTLY_ANSWERED = "game4_CorrectlyAnswered";

    Timer timer;

    public void postAnalysis(Map<String,String> map, OnEntityReceivedListener<CareerAnalysis> listener){
        repository.postAnalysis(map,new EntityLoader(listener));
    }
    public  MathGamePresenter(final MathGameViewAction viewAction, PrefManager prefManager)
    {
        moves = 0;
        this.viewAction = viewAction;
        timer= new Timer();
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                decreaseTime();
            }
        };
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.obtainMessage(1).sendToTarget();
            }
        },0 , 1000);

        this.prefManager=prefManager;



    }

    public void nextProblem() {

        moves++;
        operand1 = getRandomNumber(min,max);
        operand2 = getRandomNumber(min,max);
        operator = getRandomNumber(1,3);
        System.out.println(operand1 + getStringOperator(operator) + operand2);
        answer = getCorrectAnswerForCurrentMove();
        viewAction.setProblem(operand1 + "",operand2 + "",getStringOperator(operator));
    }

    int getRandomNumber(int min, int max)
    {
        Random rn = new Random();
        return rn.nextInt(max - min + 1) + min;

    }

    String getStringOperator(int operator)
    {
        switch (operator)
        {
            case 1 : return "+";
            case 2 : return "-";
            case 3 : return "*";
            case 4 : return "÷";
            default: return null;
        }
    }

    int getCorrectAnswerForCurrentMove()
    {
        switch (operator)
        {
            case 1 : return operand1 + operand2;
            case 2 : return operand1 - operand2;
            case 3 : return operand1 * operand2;
            case 4 :
                if(operand2 != 0)
                    return operand1 / operand2;
                else if(operand1 != 0)
                     return operand2 / operand1;
                else skipProblem();
            default: return 0;
        }
    }

    void skipProblem()
    {
        moves--;
        nextProblem();
    }

    void submitAnswer(String answer)
    {
        if(!isNumeric(answer))
            viewAction.showToast("Hey! Answer should be all digits!");
        else{
            int userAnswer = Integer.parseInt(answer);
            if(userAnswer == this.answer)
            {
                correctResponses++;
                viewAction.showToast("Great! Your answer was correct. Try this one.");
            }
            else {
                viewAction.showToast("Oops! Your answer wasn't correct. Try this one.");
                incorrectResponses++;
            }

            nextProblem();
        }
    }

    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

    void decreaseTime(){
        if(time == 0){
            timer.cancel();
            timer.purge();

            //save data to preferences
            prefManager.saveString(GAME4_CORRECTLY_ANSWERED, correctResponses + "");
            prefManager.saveString(GAME4_QUESTION_COUNT, (correctResponses + incorrectResponses) + "");

            viewAction.showResults(correctResponses + "",incorrectResponses + "");
        } else
            time--;
        viewAction.updateTimer(time + "");
    }
}
