package com.AbhiDev.edurecomm.game2;

import android.os.Handler;
import android.os.Message;


import com.wireout.common.EntityLoader;
import com.wireout.common.PrefManager;
import com.wireout.listeners.OnEntityReceivedListener;
import com.wireout.models.career_analysis.CareerAnalysis;
import com.wireout.models.career_analysis.Section9;
import com.wireout.presenters.BasePresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Paras on 6/29/2017.
 */

public class Game3Presenter extends BasePresenter{

    Game3ViewAction viewAction;
    //String[] colorStrings = {"black","blue","green","gray","purple","red","yellow","pink","brown"};
    ArrayList<Game3Level> levelSet = new ArrayList<Game3Level>();
    Game3Level currLevel;
    PrefManager prefManager;
    public int correct, incorrect;
    Timer timer;
    Handler mHandler;
    int time = 96;

    //preference data keys
    public static final String GAME2_QUESTION_COUNT = "game2_QuestionCount";
    public static final String GAME2_CORRECTLY_ANSWERED = "game2_CorrectlyAnswered";



    public void postAnalysis(Map<String,String> map, OnEntityReceivedListener<CareerAnalysis> listener){
        repository.postAnalysis(map,new EntityLoader(listener));
    }
    Game3Presenter(Game3ViewAction viewAction, PrefManager prefManager){
        this.viewAction = viewAction;
        correct = incorrect = 0;
        this.prefManager = prefManager;
        initLevels();
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
    }


    void start(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.obtainMessage(1).sendToTarget();
            }
        },0 , 1000);
        nextMove();
    }

    void nextMove()
    {

        if(levelSet.isEmpty())
        {
            viewAction.gameOver(correct + "",incorrect + "");

            //save preferences data
            prefManager.saveString(GAME2_CORRECTLY_ANSWERED, correct + "");
            prefManager.saveString(GAME2_QUESTION_COUNT, (correct + incorrect) + "");

        } else{
            currLevel = levelSet.get(0);
            levelSet.remove(0);
            viewAction.showGameScreen(currLevel);
        }

    }

    void submitAndNextMove(Boolean response)
    {
        if(currLevel.isCorrect() == response){
            viewAction.showToast("Correct :)");
            correct++;
        } else {
            viewAction.showToast("Incorrect");
            incorrect++;
        }
        nextMove();
    }

    private void initLevels(){
        Game3Level level;
        level = new Game3Level("blue","black","red","blue",true);
        levelSet.add(level);
        level = new Game3Level("red","red","red","black",false);
        levelSet.add(level);
        level = new Game3Level("pink","yellow","yellow","pink",true);
        levelSet.add(level);
        level = new Game3Level("green","black","pink","green",true);
        levelSet.add(level);
        level = new Game3Level("green","yellow","yellow","blue",false);
        levelSet.add(level);
        level = new Game3Level("brown","red","brown","pink",false);
        levelSet.add(level);
        level = new Game3Level("brown","blue","yellow","brown",true);
        levelSet.add(level);
        level = new Game3Level("yellow","blue","yellow","brown",false);
        levelSet.add(level);
        level = new Game3Level("yellow","green","pink","yellow",true);
        levelSet.add(level);
        level = new Game3Level("pink","blue","yellow","pink",true);
        levelSet.add(level);
        level = new Game3Level("pink","blue","yellow","pink",true);
        levelSet.add(level);
        level = new Game3Level("black","blue","black","pink",false);
        levelSet.add(level);
        level = new Game3Level("blue","blue","yellow","yellow",false);
        levelSet.add(level);
        level = new Game3Level("green","blue","purple","green",true);
        levelSet.add(level);
        level = new Game3Level("green","blue","purple","yellow",false);
        levelSet.add(level);
        level = new Game3Level("yellow","pink","purple","blue",false);
        levelSet.add(level);
        level = new Game3Level("yellow","pink","purple","yellow",true);
        levelSet.add(level);
        level = new Game3Level("purple","pink","yellow","purple",true);
        levelSet.add(level);
        level = new Game3Level("purple","red","red","red",false);
        levelSet.add(level);
        level = new Game3Level("red","pink","yellow","red",true);
        levelSet.add(level);
        level = new Game3Level("brown","blue","purple","red",false);
        levelSet.add(level);
        level = new Game3Level("yellow","pink","purple","yellow",true);
        levelSet.add(level);
        level = new Game3Level("brown","blue","green","blue",false);
        levelSet.add(level);
        level = new Game3Level("yellow","red","blue","yellow",true);
        levelSet.add(level);
        level = new Game3Level("red","red","blue","yellow",false);
        levelSet.add(level);
        level = new Game3Level("blue","red","blue","pink",false);
        levelSet.add(level);
        level = new Game3Level("red","red","blue","yellow",false);
        levelSet.add(level);






        //shuffle the levels in array list in random fashion
        long seed = System.nanoTime();
        Collections.shuffle(levelSet,new Random(seed));


    }

    void decreaseTime(){
        if(time == 0){
            timer.cancel();
            timer.purge();
            //game over
            viewAction.gameOver(correct + "",incorrect + "");
        } else
            time--;
        viewAction.updateTimer((time-50) + "");
    }

    void skip(){
        nextMove();
    }


}
