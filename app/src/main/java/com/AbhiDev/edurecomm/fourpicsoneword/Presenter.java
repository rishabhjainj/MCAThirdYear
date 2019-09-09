package com.AbhiDev.edurecomm.fourpicsoneword;

import android.util.Log;


import com.wireout.common.EntityLoader;
import com.wireout.listeners.OnEntityReceivedListener;
import com.wireout.models.career_analysis.CareerAnalysis;
import com.wireout.presenters.BasePresenter;
import com.wireout.R;
import com.wireout.common.PrefManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

/**
 * Created by sharda on 24/07/17.
 */

public class Presenter extends BasePresenter{
    int charactersSelected;
    int currentLevelIndex;
    public int numCorrect;
    boolean levelFinished = false, hintViewed = false;
    String playWord = ""; //word that user has formed so far
    ArrayList<GameLevelModel> gameLevels;
    ViewAction viewAction;
    PrefManager prefManager;

    //preferences key
    public static final String GAME3_CORRECTLY_ANSWERED= "game3_Level";

    public void postAnalysis(Map<String,String> map, OnEntityReceivedListener<CareerAnalysis> listener){
        repository.postAnalysis(map,new EntityLoader(listener));
    }
    public Presenter(ViewAction viewAction, PrefManager prefManager){
        playWord = "";
        currentLevelIndex = 0;
        charactersSelected = 0;
        this.viewAction = viewAction;
        this.prefManager = prefManager;
        initializeLevels();

    }

    void start(){
        viewAction.setScreen(gameLevels.get(currentLevelIndex));
    }


    void next(){
        levelFinished = false;
        playWord = "";
        charactersSelected = 0;

        currentLevelIndex++;
        if(currentLevelIndex == gameLevels.size()){
            viewAction.gameOver();
            //save data to preferences
            prefManager.saveString(GAME3_CORRECTLY_ANSWERED, numCorrect + "");
        } else viewAction.setScreen(gameLevels.get(currentLevelIndex));
    }


    void characterSelectEventHandler(String character){

        if(!levelFinished){
            charactersSelected++;
            playWord = viewAction.updatePlayWord(character);
//
//            if(playWord.length() != 0){
//                String newPlayWord = "";
//                for(int i=0; i<index;i++)
//                    newPlayWord += playWord.charAt(i);
//                newPlayWord += character;
//                for(int i=index; i<playWord.length(); i++)
//                    newPlayWord += playWord.charAt(i);
//                playWord = newPlayWord;
//            } else
//                playWord = character + "";
//
            Log.d("Playword", playWord);
            if(gameLevels.get(currentLevelIndex).correctAnswer.length() == playWord.length()){
                evaluate();
            }
        }


    }

    private void evaluate(){
        if(playWord.toLowerCase().equals(gameLevels.get(currentLevelIndex).correctAnswer.toLowerCase()))
        {
            viewAction.showLevelSuccess();
            viewAction.removeListenersFromTextViews();
            viewAction.setNextButton();
            numCorrect++;
            levelFinished = true;

        } else{
            viewAction.showLevelFailure();
        }
    }

    private void initializeLevels(){
        gameLevels = new ArrayList<>();
        GameLevelModel gameLevelModel = new GameLevelModel(
                R.drawable.game5_level1_1,
                R.drawable.game5_level1_2,
                R.drawable.game5_level1_3,
                R.drawable.game5_level1_4,
                "sign",
                "qwcnasdfghji"
        );

        gameLevels.add(gameLevelModel);

        gameLevelModel = new GameLevelModel(
                R.drawable.game5_level2_1,
                R.drawable.game5_level2_2,
                R.drawable.game5_level2_3,
                R.drawable.game5_level2_4,
                "round",
                "frxcdvbunmoz"
        );

        gameLevels.add(gameLevelModel);

        gameLevelModel = new GameLevelModel(
                R.drawable.game5_level3_1,
                R.drawable.game5_level3_2,
                R.drawable.game5_level3_3,
                R.drawable.game5_level3_4,
                "light",
                "grzcltiunhoy"
        );

        gameLevels.add(gameLevelModel);

        gameLevelModel = new GameLevelModel(
                R.drawable.game5_level4_1,
                R.drawable.game5_level4_2,
                R.drawable.game5_level4_3,
                R.drawable.game5_level4_4,
                "mammal",
                "aracmvblnmom"
        );

        gameLevels.add(gameLevelModel);

        gameLevelModel = new GameLevelModel(
                R.drawable.game5_level5_1,
                R.drawable.game5_level5_2,
                R.drawable.game5_level5_3,
                R.drawable.game5_level5_4,
                "wave",
                "arwcdvbunmoe"
        );

        gameLevels.add(gameLevelModel);

        gameLevelModel = new GameLevelModel(
                R.drawable.game5_level6_1,
                R.drawable.game5_level6_2,
                R.drawable.game5_level6_3,
                R.drawable.game5_level6_4,
                "cross",
                "arscdvswnmoe"
        );

        gameLevels.add(gameLevelModel);

        gameLevelModel = new GameLevelModel(
                R.drawable.game5_level7_1,
                R.drawable.game5_level7_2,
                R.drawable.game5_level7_3,
                R.drawable.game5_level7_4,
                "letter",
                "eqstdesrkptl"
        );

        gameLevels.add(gameLevelModel);

        gameLevelModel = new GameLevelModel(
                R.drawable.game5_level8_1,
                R.drawable.game5_level8_2,
                R.drawable.game5_level8_3,
                R.drawable.game5_level8_4,
                "record",
                "oqrtdersfgtc"
        );

        gameLevels.add(gameLevelModel);

        gameLevelModel = new GameLevelModel(
                R.drawable.game5_level9_1,
                R.drawable.game5_level9_2,
                R.drawable.game5_level9_3,
                R.drawable.game5_level9_4,
                "vehicle",
                "lqvederhbitc"
        );

        gameLevels.add(gameLevelModel);
    }

    void deselectCharacter(int index, String newPlayWord){
        charactersSelected--;
//        String newPlayWord = "";
//        for(int i=0; i<index; i++)
//            newPlayWord += playWord.charAt(i) + "";
//        for(int i=index+1; i<playWord.length(); i++)
//            newPlayWord += playWord.charAt(i) + "";
//        playWord = newPlayWord;
        Log.d("Playword", playWord + "index : " + index);
        playWord = newPlayWord;
    }

    void showHint(){
        String correctAnswer = gameLevels.get(currentLevelIndex).correctAnswer;
        String characters[] = correctAnswer.split("");
        ArrayList<String> characterArrayList = new ArrayList<>();
        ArrayList<String> jumbledWords= new ArrayList<>();
        for(String character : characters)
            characterArrayList.add(character);
        while(jumbledWords.size() < 3){
            Collections.shuffle(characterArrayList, new Random(System.nanoTime()));
            String jumbledWord = "";
            for(String character : characterArrayList)
                jumbledWord += character;
            if(!jumbledWord.equals(correctAnswer))
                jumbledWords.add(jumbledWord);
        }

        viewAction.showHintScreen(jumbledWords);
    }
}
