package com.AbhiDev.edurecomm.presenters;

import android.os.Handler;

import com.wireout.common.AbstractCallback;
import com.wireout.common.EntitiesLoader;
import com.wireout.common.EntityLoader;
import com.wireout.listeners.AnalysisEventListener;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.listeners.OnEntityReceivedListener;
import com.wireout.models.Analysis.MCQ;
import com.wireout.models.career_analysis.CareerAnalysis;
import com.wireout.models.career_analysis.Report;
import com.wireout.models.career_analysis.Section1Response;
import com.wireout.models.career_analysis.Section9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by sharda on 10/03/18.
 */

public class AnalysisPresenter extends BasePresenter {

    private enum Screen {
        Intro, BioData,Preferences,Academics,HigherEducation,Professional,ExperienceScreen,ExamScore,HistoryEndScreen,MeOrNotMeQue1, EndScreenIntro,EmotionalIntro,Mood,MCQ1,MCQ2,MCQ3,MCQ4,MCQ5,MCQ6,MCQ7,SeekBarScreen,MCQ8,MCQ9,MCQ10,MCQ11,EndScreenEmotional,CriticalIntro
        ,Ques1,Ques2,Ques3,Ques4,Ques5,EndScreenCritical,LifeChoicesIntro,EndScreenLife,Choices,MeOrNotMeIntro,BrainBoosterIntro,PersonalIntro,Handwriting,EndScreenPersonal,EgoGramQue1,EgoGramQue2,EgoGramQue3,EgoGramQue4,EgoGramQue5,EgoGramQue6,EgoGramQue7
        ,EgoGramQue8,EgoGramQue9,EgoGramQue10,EgoGramQue11,EgoGramQue12,EgoGramQue13,EgoGramQue14,EgoGramIntroScreen;
    }


    public int sequence;
    public int lastQuestionId;
    ArrayList<Screen> screens;
    AnalysisEventListener viewAction;
    Map<Integer, MCQ> mcqMap;   //caches MCQs by ID.


    public void getAnalysisReport(OnEntityReceivedListener<Report> listener){
        repository.getAnalysisReport(new EntityLoader(listener));
    }
    public AnalysisPresenter(AnalysisEventListener viewAction){
        this.viewAction = viewAction;
        screens = new ArrayList<Screen>();
        sequence = -1;
        lastQuestionId = 0;
        mcqMap = new HashMap<Integer, MCQ>();
    }
    public void postAnalysis( OnEntityReceivedListener<CareerAnalysis> listener){
        repository.postAnalysis(new HashMap<String, String>(),new EntityLoader(listener));
    }

    public void postAnalysis(Map<String,String> map, OnEntityReceivedListener<CareerAnalysis> listener){
        repository.postAnalysis(map,new EntityLoader(listener));
    }
    public void sendSection1Data(Map<String,String> map){
        repository.sendBioDataSectionData(new AbstractCallback(viewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                viewAction.showMessage("recieved response");
            }
        },map);
    }
    public void startMeOrNotMe(){
        screens.add(Screen.MeOrNotMeIntro);
        screens.add(Screen.MeOrNotMeQue1);
        sequence= -1;
        next();
    }


    public void startEgoGram(){
        screens.add(Screen.EgoGramIntroScreen);
        screens.add(Screen.EgoGramQue1);
        screens.add(Screen.EgoGramQue2);
        screens.add(Screen.EgoGramQue3);
        screens.add(Screen.EgoGramQue4);
        screens.add(Screen.EgoGramQue5);
        screens.add(Screen.EgoGramQue6);
        screens.add(Screen.EgoGramQue7);
        screens.add(Screen.EgoGramQue8);
        screens.add(Screen.EgoGramQue9);
        screens.add(Screen.EgoGramQue10);
        screens.add(Screen.EgoGramQue11);
        screens.add(Screen.EgoGramQue12);
        screens.add(Screen.EgoGramQue13);
        screens.add(Screen.EgoGramQue14);
        sequence = -1;
        next();




    }
    public void startEmotional(){
        screens.add(Screen.EmotionalIntro);
        screens.add(Screen.Mood);
        screens.add(Screen.MCQ1);
        screens.add(Screen.MCQ2);
        screens.add(Screen.MCQ3);
        screens.add(Screen.MCQ4);
        screens.add(Screen.MCQ5);
        screens.add(Screen.MCQ6);
        screens.add(Screen.MCQ7);
        screens.add(Screen.SeekBarScreen);
        screens.add(Screen.MCQ8);
        screens.add(Screen.MCQ10);
        screens.add(Screen.MCQ11);
        sequence=-1;
        next();
    }

    public void startHandwriting(){
        screens.add(Screen.PersonalIntro);
        screens.add(Screen.Handwriting);
        sequence= -1;
        next();
    }
    public void startLifeChoices(){
        screens.add(Screen.LifeChoicesIntro);
        screens.add(Screen.Choices);
        sequence = -1;
        next();
    }
    public void startBioData(){
        screens.add(Screen.Intro);
        screens.add(Screen.BioData); // 1. personal detail screen
        screens.add(Screen.Preferences);//2. preferences screen
        screens.add(Screen.Academics); //3. qualification screen
        screens.add(Screen.HigherEducation); //4. higher edu screen
        screens.add(Screen.Professional); //5. professional certification screen
        screens.add(Screen.ExperienceScreen); //6. experience screen
        screens.add(Screen.ExamScore);//7. exam score screen
        screens.add(Screen.HistoryEndScreen);
        sequence =-1;
        next();
    }

    public void start(int i){


        screens.add(Screen.Intro);
        screens.add(Screen.BioData); // 1. personal detail screen
        screens.add(Screen.Preferences);//2. preferences screen
        screens.add(Screen.Academics); //3. qualification screen
        screens.add(Screen.HigherEducation); //4. higher edu screen
        screens.add(Screen.Professional); //5. professional certification screen
        screens.add(Screen.ExperienceScreen); //6. experience screen
        screens.add(Screen.ExamScore);//7. exam score screen
        screens.add(Screen.EndScreenIntro);
        screens.add(Screen.EmotionalIntro);
        screens.add(Screen.Mood);
        screens.add(Screen.MCQ1);
        screens.add(Screen.MCQ2);
        screens.add(Screen.MCQ3);
        screens.add(Screen.MCQ4);
        screens.add(Screen.MCQ5);
        screens.add(Screen.MCQ6);
        screens.add(Screen.MCQ7);
        screens.add(Screen.SeekBarScreen);
        screens.add(Screen.MCQ9);
        screens.add(Screen.EndScreenEmotional);
        screens.add(Screen.CriticalIntro);
        screens.add(Screen.Ques1);
        screens.add(Screen.Ques2);
        screens.add(Screen.Ques3);
        screens.add(Screen.Ques4);
        screens.add(Screen.Ques5);
        screens.add(Screen.EndScreenCritical);
        screens.add(Screen.LifeChoicesIntro);
        screens.add(Screen.Choices);
        screens.add(Screen.EndScreenLife);
        screens.add(Screen.BrainBoosterIntro);
        screens.add(Screen.PersonalIntro);
        screens.add(Screen.Handwriting);
        screens.add(Screen.EndScreenPersonal);
        if(i==0) {
            sequence = -1;
            next();
        }
      else if(i==1)
        {
            sequence = 30;
        }
        else{
            sequence = 2;
        }

    }

    public void next() {


            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (++sequence < screens.size()) {
                    switch (screens.get(sequence)) {
                        case Intro:
                            lastQuestionId++;
                            viewAction.showIntroScreen();
                            break;
                        case BioData:
                            lastQuestionId++;
                            viewAction.showBioDataScreen();
                            break;
                        case Preferences:
                            lastQuestionId++;
                            viewAction.showPreferencesScreen();
                            break;
                        case Academics:
                            lastQuestionId++;
                            viewAction.showAcademicsScreen();
                            break;
                        case HigherEducation:
                            lastQuestionId++;
                            viewAction.showHigherEducationScreen();
                            break;
                        case Professional:
                            lastQuestionId++;
                            viewAction.showProfessionalCertficationScreen();
                            break;
                        case ExperienceScreen:
                            lastQuestionId++;
                            viewAction.showExperienceScreen();
                            break;
                        case ExamScore:
                            lastQuestionId++;
                            viewAction.showExamScoreScreen();
                        case HistoryEndScreen:
                            lastQuestionId++;
                            viewAction.showHistoryAndGoalsSectionEndScreen();
                            break;
                        case MeOrNotMeQue1:
                            lastQuestionId++;
                            viewAction.showMeOrNotMeQue1();
                            break;
                        case BrainBoosterIntro:
                            lastQuestionId++;
                            viewAction.showBrainBoosterIntroScreen();
                            break;
                        case EndScreenIntro:
                            lastQuestionId++;
                            viewAction.showEndScreenIntro();
                            break;
                        case EndScreenEmotional:
                            lastQuestionId++;
                            viewAction.showEndScreenEmotional();
                            break;
                        case MeOrNotMeIntro:
                            lastQuestionId++;
                            viewAction.showMeOrNotMeIntro();
                            break;
                        case EndScreenCritical:
                            lastQuestionId++;
                            viewAction.showEndScreenCritical();
                            break;
                        case EndScreenLife:
                            lastQuestionId++;
                            viewAction.showEndScreenLife();
                            break;
                        case EndScreenPersonal:
                            lastQuestionId++;
                            viewAction.showEndScreenPersonal();
                            break;
                        case EmotionalIntro:
                            lastQuestionId++;
                            viewAction.showEmotionalIntroScreen();
                            break;
                        case Mood:
                            lastQuestionId++;
                            viewAction.showMoodScreen();
                            break;
                        case MCQ1:
                            lastQuestionId++;
                            viewAction.showQuestion1Screen(getMCQ());
                            break;
                        case MCQ2:
                            lastQuestionId++;
                            viewAction.showQuestion2Screen(getMCQ());
                            break;
                        case MCQ3:
                            lastQuestionId++;
                            viewAction.showQuestion3Screen(getMCQ());
                            break;
                        case MCQ4:
                            lastQuestionId++;
                            viewAction.showQuestion4Screen(getMCQ());
                            break;
                        case MCQ5:
                            lastQuestionId++;
                            viewAction.showQuestion5Screen(getMCQ());
                            break;
                        case MCQ6:
                            lastQuestionId++;
                            viewAction.showQuestion6Screen(getMCQ());
                            break;
                        case MCQ7:
                            lastQuestionId++;
                            viewAction.showQuestion7Screen(getMCQ());
                            break;
                        case SeekBarScreen:
                            lastQuestionId++;
                            viewAction.showSeekBarScreen();
                            break;
                        case MCQ8:
                            lastQuestionId++;
                            viewAction.showQuestion8Screen();
                            break;
                        case MCQ9:
                            lastQuestionId++;
                            viewAction.showQuestion9Screen(getMCQ());
                            break;
                        case MCQ10:
                            lastQuestionId++;
                            viewAction.showQuestion10Screen();
                            break;
                        case MCQ11:
                            lastQuestionId++;
                            viewAction.showQuestion11Screen();
                            break;
                        case CriticalIntro:
                            lastQuestionId++;
                            viewAction.showCriticalIntroScreen();
                            break;
                        case Ques1:
                            lastQuestionId++;
                            viewAction.showCriticalQues1Screen();
                            break;
                        case Ques2:
                            lastQuestionId++;
                            viewAction.showCriticalQues2Screen();
                            break;
                        case Ques3:
                            lastQuestionId++;
                            viewAction.showCriticalQues3Screen();
                            break;
                        case Ques4:
                            lastQuestionId++;
                            viewAction.showCriticalQues4Screen();
                            break;
                        case Ques5:
                            lastQuestionId++;
                            viewAction.showCriticalQues5Screen();
                            break;
                        case LifeChoicesIntro:
                            lastQuestionId++;
                            viewAction.showLifeChoicesIntroScreen();
                            break;
                        case Choices:
                            lastQuestionId++;
                            viewAction.showLifeChoicesScreen();
                            break;
                        case PersonalIntro:
                            lastQuestionId++;
                            viewAction.showPersonalIntroScreen();
                            break;
                        case Handwriting:
                            lastQuestionId++;
                            viewAction.showHandwritingScreen();
                            break;
                        case EgoGramIntroScreen:
                            lastQuestionId++;
                            viewAction.showEgoGramIntroScreen();
                            break;
                        case EgoGramQue1:
                            lastQuestionId++;
                            viewAction.showEgoGramQue1Screen();
                            break;
                        case EgoGramQue2:
                            lastQuestionId++;
                            viewAction.showEgoGramQue2Screen();
                            break;
                        case EgoGramQue3:
                            lastQuestionId++;
                            viewAction.showEgoGramQue3Screen();
                            break;
                        case EgoGramQue4:
                            lastQuestionId++;
                            viewAction.showEgoGramQue4Screen();
                            break;
                        case EgoGramQue5:
                            lastQuestionId++;
                            viewAction.showEgoGramQue5Screen();
                            break;
                        case EgoGramQue6:
                            lastQuestionId++;
                            viewAction.showEgoGramQue6Screen();
                            break;
                        case EgoGramQue7:
                            lastQuestionId++;
                            viewAction.showEgoGramQue7Screen();
                            break;
                        case EgoGramQue8:
                            lastQuestionId++;
                            viewAction.showEgoGramQue8Screen();
                            break;
                        case EgoGramQue9:
                            lastQuestionId++;
                            viewAction.showEgoGramQue9Screen();
                            break;
                        case EgoGramQue10:
                            lastQuestionId++;
                            viewAction.showEgoGramQue10Screen();
                            break;
                        case EgoGramQue11:
                            lastQuestionId++;
                            viewAction.showEgoGramQue11Screen();
                            break;
                        case EgoGramQue12:
                            lastQuestionId++;
                            viewAction.showEgoGramQue12Screen();
                            break;
                        case EgoGramQue13:
                            lastQuestionId++;
                            viewAction.showEgoGramQue13Screen();
                            break;
                        case EgoGramQue14:
                            lastQuestionId++;
                            viewAction.showEgoGramQue14Screen();
                            break;

                    }
                } else {
                    //viewAction.showMessage("analysis completed"+sequence);
                    sequence--;
                }


            }
            }, 200);


        }

    public void previous(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(--sequence >= 0) {

                    switch (screens.get(sequence)) {
                        case Intro:
                            viewAction.showIntroScreen();
                            break;
                        case BioData:

                            viewAction.showBioDataScreen();
                            break;
                        case Preferences:

                            viewAction.showPreferencesScreen();
                            break;
                        case Academics:

                            viewAction.showAcademicsScreen();
                            break;
                        case HigherEducation:
                            viewAction.showHigherEducationScreen();
                            break;
                        case Professional:
                            viewAction.showProfessionalCertficationScreen();
                            break;
                        case ExperienceScreen:
                            viewAction.showExperienceScreen();
                            break;
                        case ExamScore:
                            viewAction.showExamScoreScreen();
                            break;
                        case  HistoryEndScreen:
                            viewAction.showHistoryAndGoalsSectionEndScreen();
                            break;
                        case BrainBoosterIntro:

                            viewAction.showBrainBoosterIntroScreen();
                            break;
                        case EmotionalIntro:

                            viewAction.showEmotionalIntroScreen();
                            break;
                        case EndScreenIntro:
                            viewAction.showEndScreenIntro();
                            break;
                        case EndScreenEmotional:

                            viewAction.showEndScreenEmotional();
                            break;
                        case EndScreenCritical:

                            viewAction.showEndScreenCritical();
                            break;
                        case EndScreenLife:

                            viewAction.showEndScreenLife();
                            break;
                        case EndScreenPersonal:

                            viewAction.showEndScreenPersonal();
                            break;
                        case Mood:

                            viewAction.showMoodScreen();
                            break;
                        case MCQ1:

                            viewAction.showQuestion1Screen(getMCQ());
                            break;
                        case MCQ2:

                            viewAction.showQuestion2Screen(getMCQ());
                            break;
                        case MCQ3:

                            viewAction.showQuestion3Screen(getMCQ());
                            break;
                        case MCQ4:

                            viewAction.showQuestion4Screen(getMCQ());
                            break;
                        case MCQ5:
                            lastQuestionId++;
                            viewAction.showQuestion5Screen(getMCQ());
                            break;
                        case CriticalIntro:

                            viewAction.showCriticalIntroScreen();
                            break;
                        case Ques1:

                            viewAction.showCriticalQues1Screen();
                            break;
                        case Ques2:

                            viewAction.showCriticalQues2Screen();
                            break;
                        case Ques3:

                            viewAction.showCriticalQues3Screen();
                            break;
                        case Ques4:

                            viewAction.showCriticalQues4Screen();
                            break;
                        case MCQ6:
                            viewAction.showQuestion6Screen(getMCQ());
                            break;
                        case MCQ7:
                            viewAction.showQuestion7Screen(getMCQ());
                            break;
                        case SeekBarScreen:
                            viewAction.showSeekBarScreen();
                            break;
                        case MCQ9:
                            viewAction.showQuestion9Screen(getMCQ());
                            break;
                        case Ques5:

                            viewAction.showCriticalQues5Screen();
                            break;
                        case LifeChoicesIntro:

                            viewAction.showLifeChoicesIntroScreen();
                            break;

                        case Choices:

                            viewAction.showLifeChoicesScreen();
                            break;
                        case PersonalIntro:

                            viewAction.showPersonalIntroScreen();
                            break;
                        case Handwriting:

                            viewAction.showHandwritingScreen();
                            break;

                    }

                }
                else{
                    viewAction.finishActivity();
                }

            }
        }, 200);

    }

    public MCQ getMCQ(){
        //first check if the MCQ lies in the cache map;
        MCQ mcq = mcqMap.get(lastQuestionId);
        if(mcq != null) return mcq;

        //if not present in cache
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        // get next MCQ from API using the lastQuestionId, put the MCQ in map cache
        return  mcq;
    }

}
