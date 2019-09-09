package com.AbhiDev.edurecomm.Activities.Analysis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.wireout.Activities.BackPressed;
import com.wireout.Activities.CareerAssesmentStartActivity;
import com.wireout.Activities.MainActivity;
import com.wireout.Activities.Recommendations;
import com.wireout.common.MyApplication;
import com.wireout.listeners.OnEntityReceivedListener;
import com.wireout.models.career_analysis.CareerAnalysis;
import com.wireout.models.career_analysis.Report;
import com.wireout.R;
import com.wireout.adapters.AnalysisStatusAdapter;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.career_analysis.BooleanQuestion;
import com.wireout.presenters.BooleanQuestionPresenter;

import java.util.ArrayList;
import java.util.List;

public class AnalysisStatusActivity extends BackPressed {

    AnalysisStatusAdapter adapter;
    RecyclerView analysisSectionItemsRecyclerView;
    List<Integer> sectionImages;
    public static final String ANALYSIS_ENTERED="Analysis_Entered";
    public static final String ANALYSIS_COMPLETED="Analysis_Completed";
    List<String> sectionNames;
    Intent intent;
    OnEntityReceivedListener<Report> reportListener;
    ImageView backBtn;
    Button viewReportBtn;
    List<String> sectionTime;
    ImageView exitSection;
    List<BooleanQuestion> allQuestions;
    OnEntityReceivedListener<CareerAnalysis> lifeChoicesListener;
    OnEntitiesReceivedListener<BooleanQuestion> listener;
    BooleanQuestionPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);
        allQuestions = new ArrayList<>();
        MyApplication.getInstance().prefManager.saveString(ANALYSIS_ENTERED,"true");
        reportListener = new OnEntityReceivedListener<Report>(this) {
            @Override
            public void onReceived(Report entity) {
                Gson gson = new Gson();
                String json = gson.toJson(entity,Report.class);
                MyApplication.getInstance().prefManager.saveString("report",json);
                //showMessage("saved report");
                startActivity(intent);
            }
        };
        listener = new OnEntitiesReceivedListener<BooleanQuestion>(this) {
            @Override
            public void onReceived(List<BooleanQuestion> allQuestions) {
                Log.d("sectionNames",allQuestions.size()+"");
                makeArrayListsFromQuestions(allQuestions);
                presenter.postAnalysis(lifeChoicesListener);

            }
        };
        lifeChoicesListener = new OnEntityReceivedListener<CareerAnalysis>(this) {
            @Override
            public void onReceived(CareerAnalysis entity) {
                initUi(entity);
            }
        };
        presenter = new BooleanQuestionPresenter();
        presenter.getQuestionsForSections(listener);



    }
    public void makeArrayListsFromQuestions(List<BooleanQuestion> allQuestions){
        this.allQuestions = allQuestions;
    }
    public void initUi(final CareerAnalysis status){
        setContentView(R.layout.activity_analysis_status);




        viewReportBtn = findViewById(R.id.reportbtn);
        viewReportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if((MyApplication.getInstance().prefManager.getString(AnalysisBioDataSection.HISTORYNGOALSSECTIONCOMPLETED)!=null)&&(MyApplication.getInstance().prefManager.getString(MeOrNotMeSection.MEORNOTMECOMPLETED)!=null)
//                        &&(MyApplication.getInstance().prefManager.getString(AbilitySection.ABILITYCOMPLETED)!=null)&&(MyApplication.getInstance().prefManager.getString(InterstSection.INTERESTCOMPLETED)!=null)
//                        &&(MyApplication.getInstance().prefManager.getString(LifeChoicesSection.LIFECHOICESSECTIONCOMPLETED)!=null)&&(MyApplication.getInstance().prefManager.getString(SectionEndActivity.VERBALABILITYCOMPLETED)!=null)&&
//                        (MyApplication.getInstance().prefManager.getString(SectionEndActivity.BRAINBOOSTERCOMPLETED)!=null)&&
//                (MyApplication.getInstance().prefManager.getString(YourEgogramSection.EGOGRAMCOMPLETED)!=null)&&(MyApplication.getInstance().prefManager.getString(EmotionalIntelligenceSection.EMOTIONALSECTIONCOMPLETED)!=null)&&(MyApplication.getInstance().prefManager.getString(SectionEndActivity.FLEXIBILITYGAMECOMPLETED)!=null)
//                        &&(MyApplication.getInstance().prefManager.getString(SectionEndActivity.MATHGAMECOMPLETED)!=null)&&(MyApplication.getInstance().prefManager.getString(MotivationalQuotientSection.MOTIVATIONALCOMPLETED)!=null)&&
//                        (MyApplication.getInstance().prefManager.getString(HandwritingSection.HANDWRITINGSECTIONCOMPLETED)!=null)) {
//                    if((MyApplication.getInstance().prefManager.getString(AnalysisBioDataSection.HISTORYNGOALSSECTIONCOMPLETED).equals("true"))&&(MyApplication.getInstance().prefManager.getString(MeOrNotMeSection.MEORNOTMECOMPLETED).equals("true"))&&
//                            (MyApplication.getInstance().prefManager.getString(AbilitySection.ABILITYCOMPLETED).equals("true"))&&(MyApplication.getInstance().prefManager.getString(InterstSection.INTERESTCOMPLETED).equals("true"))
//                            &&(MyApplication.getInstance().prefManager.getString(YourEgogramSection.EGOGRAMCOMPLETED).equals("true"))&&(MyApplication.getInstance().prefManager.getString(LifeChoicesSection.LIFECHOICESSECTIONCOMPLETED).equals("true"))
//                            &&(MyApplication.getInstance().prefManager.getString(SectionEndActivity.VERBALABILITYCOMPLETED).equals("true"))&&(MyApplication.getInstance().prefManager.getString(SectionEndActivity.BRAINBOOSTERCOMPLETED).equals("true"))
//                            &&(MyApplication.getInstance().prefManager.getString(EmotionalIntelligenceSection.EMOTIONALSECTIONCOMPLETED).equals("true"))&&(MyApplication.getInstance().prefManager.getString(SectionEndActivity.FLEXIBILITYGAMECOMPLETED).equals("true"))
//                            &&(MyApplication.getInstance().prefManager.getString(SectionEndActivity.MATHGAMECOMPLETED).equals("true")) &&(MyApplication.getInstance().prefManager.getString(MotivationalQuotientSection.MOTIVATIONALCOMPLETED).equals("true"))&&
//                            (MyApplication.getInstance().prefManager.getString(HandwritingSection.HANDWRITINGSECTIONCOMPLETED).equals("true"))) {
                if(status.getSection2()!=null&&status.getSection3()!=null&&status.getSection4()!=null&&status.getSection5()!=null
                        &&status.getSection6()!=null&&status.getSection7()!=null&&status.getSection8()!=null&&status.getSection9()!=null&&status.getSection10()!=null
                        &&status.getSection11()!=null&&status.getSection12()!=null){
                        MyApplication.getInstance().prefManager.saveString(ANALYSIS_COMPLETED,"true");
                        //presenter.getAnalysisReport(reportListener);
                        //Toast.makeText(getApplicationContext(),"Please Wait",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AnalysisStatusActivity.this,LoaderActivity.class));

                    }

                else{
                    MyApplication.getInstance().prefManager.saveString(ANALYSIS_COMPLETED,null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(AnalysisStatusActivity.this);
// Add the buttons
                    builder.setMessage("We recommend you to complete all sections before you can view your career report.");
                    builder.setPositiveButton("Resume", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    builder.setNegativeButton("View Sample Report", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MyApplication.getInstance().prefManager.saveString(ANALYSIS_COMPLETED,null);
                            MyApplication.getInstance().prefManager.saveString(ANALYSIS_COMPLETED,null);
                            Intent intent = new Intent(getApplicationContext(), Recommendations.class);
                            intent.putExtra("key", 1);
                            startActivity(intent);
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
        backBtn = findViewById(R.id.backbtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AnalysisStatusActivity.this,CareerAssesmentStartActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        exitSection = findViewById(R.id.endbtn);
        exitSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(AnalysisStatusActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        analysisSectionItemsRecyclerView = findViewById(R.id.analysis_sections_recycler_view);
        sectionImages = new ArrayList<>();
        sectionNames = new ArrayList<>();
        sectionTime = new ArrayList<>();

        sectionImages.add(R.drawable.ic_historygoals);
        sectionImages.add(R.drawable.ic_ability);
        sectionImages.add(R.drawable.ic_interest);
        sectionImages.add(R.drawable.ic_meornotme);
        sectionImages.add(R.drawable.ic_egogram);
        sectionImages.add(R.drawable.ic_lifechoices);
        sectionImages.add(R.drawable.ic_verbalaptitude);
        sectionImages.add(R.drawable.ic_brainbooster);
        sectionImages.add(R.drawable.ic_emotioalintelligence);
        sectionImages.add(R.drawable.ic_fexibility);
        sectionImages.add(R.drawable.ic_quickmaths);
        sectionImages.add(R.drawable.ic_motivationquotient);
        sectionImages.add(R.drawable.ic_comm_skills);
        sectionImages.add(R.drawable.ic_logical_reasoning);
        sectionImages.add(R.drawable.ic_handwriting);

        sectionNames.add("History & Goals");
        sectionNames.add("Me or Not Me");
        sectionNames.add("Ability");
        sectionNames.add("Interests");
        sectionNames.add("Your Egogram");
        sectionNames.add("Life Choices");
        sectionNames.add("Verbal Aptitude");
        sectionNames.add("Brain Teaser");
        sectionNames.add("Emotional Intelligence");
        sectionNames.add("Flexibility Game");
        sectionNames.add("Quick Maths");
        sectionNames.add("Motivational Quotient");
        sectionNames.add("Communication Skills");
        sectionNames.add("Logical Reasoning");
        sectionNames.add("Handwriting");

        sectionTime.add("2 min");
        sectionTime.add("40 sec");
        sectionTime.add("30 sec");
        sectionTime.add("30 sec");
        sectionTime.add("30 sec");
        sectionTime.add("10 sec");
        sectionTime.add("30 sec");
        sectionTime.add("1 min");
        sectionTime.add("1 min");
        sectionTime.add("45 sec");
        sectionTime.add("45 sec");
        sectionTime.add("1 min");
        sectionTime.add("5 min");
        sectionTime.add("5 min");
        sectionTime.add("30 sec");

        setAnalysisSectionItemsRecyclerView(sectionImages,sectionNames,sectionTime,status);
    }
    public void setAnalysisSectionItemsRecyclerView(List<Integer> sectionImages,List<String> sectionNames,List<String> time,CareerAnalysis status){

        analysisSectionItemsRecyclerView.hasFixedSize();
        analysisSectionItemsRecyclerView.removeAllViews();
        analysisSectionItemsRecyclerView.setNestedScrollingEnabled(false);
        analysisSectionItemsRecyclerView.removeAllViewsInLayout();
        adapter=new AnalysisStatusAdapter(this,sectionNames,sectionImages,time,allQuestions,status);

        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        analysisSectionItemsRecyclerView.setLayoutManager(layoutManager);
        analysisSectionItemsRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(AnalysisStatusActivity.this,CareerAssesmentStartActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
