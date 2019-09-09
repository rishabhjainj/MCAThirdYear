package com.AbhiDev.edurecomm.Exams.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.wireout.Activities.BaseActivity;
import com.wireout.Activities.MainActivity;
import com.wireout.Exams.AnswerStatusAdapter;
import com.wireout.Exams.ExamQuestionModel;
import com.wireout.Exams.QuestionResponse;
import com.wireout.Exams.ResultQuestionAdapter;
import com.wireout.R;
import com.wireout.models.exams.ExamSubCategory;
import com.wireout.models.exams.Question;

import java.util.ArrayList;
import java.util.List;

public class ExamResultActivity extends BaseActivity {

    ResultQuestionAdapter adapter;
    TextView timeText,noOfQues,correctText,incorrectText,unattemptedText;
    List<Question> questions;
    PieChart mChart;
    TextView correctMarks;
    protected Typeface mTfLight,mTfRegular;
    List<QuestionResponse> responseList;
    ImageView backBtn;
    RecyclerView statusRecycler;
    ExamSubCategory exam;
    RecyclerView recyclerView;
    TextView marks;
    AnswerStatusAdapter statusAdapter;
    int correctAnswers=0,incorrectAnswers=0,unAttempted=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_screen);
        timeText = findViewById(R.id.timeText);
        marks = findViewById(R.id.marks);
        correctMarks = findViewById(R.id.correctMarks);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        //toolbar.setBackgroundColor(getResources().getColor(R.color.filter_blue));
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        backBtn = findViewById(R.id.backbtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExamResultActivity.this, ExamSubCategoryListActivity.class);
                i.putExtra("category",exam.getCategory().getTitle());
// set the new task and clear flags
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
            }
        });
        noOfQues = findViewById(R.id.noOfQues);
        unattemptedText = findViewById(R.id.unattemptedText);
        incorrectText = findViewById(R.id.incorrectText);
        correctText = findViewById(R.id.correctText);
        statusRecycler = findViewById(R.id.status_recyview);
        recyclerView = findViewById(R.id.recyclerview);
        questions = new ArrayList<>();
        Intent i = getIntent();

        responseList = (List<QuestionResponse>)i.getSerializableExtra("answers");

        exam = (ExamSubCategory) i.getSerializableExtra("questions");
        questions = exam.getQuestions();


        for(int c=0;c<questions.size();c++){
            if(responseList.get(c).getAnswer().equals("na")){
                unAttempted++;
            }
            else if(responseList.get(c).getAnswer().equals(questions.get(c).getCorrectAnswer())){
                correctAnswers++;
            }
            else{
                incorrectAnswers++;
            }
        }

        correctMarks.setText("+"+correctAnswers*questions.get(0).getMarks()+"");
        marks.setText(correctAnswers*questions.get(0).getMarks()+"");
        unattemptedText.setText("Not Attempted: "+unAttempted);
        incorrectText.setText("Incorrect: "+incorrectAnswers);
        timeText.setText("Time: "+exam.getTime()+" min");
        noOfQues.setText("No. of questions: "+questions.size());
        correctText.setText("Correct: "+correctAnswers);
        mChart = findViewById(R.id.chart);
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);
        mChart.setDragDecelerationFrictionCoef(0.95f);
        mChart.setCenterTextTypeface(mTfLight);
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(40f);
        mTfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
        mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
        mChart.setTransparentCircleRadius(60f);
        mChart.setDrawCenterText(true);
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);
        setData(correctAnswers,incorrectAnswers,unAttempted,3, 100);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        Legend l=mChart.getLegend();
        l.setEnabled(false);
        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);
        mChart.setEntryLabelTypeface(mTfRegular);
        mChart.setEntryLabelTextSize(12f);
        mChart.setEntryLabelColor(R.color.black);

       // setQuestionsRecyclerView();
        setStatusRecyclerView();
    }
    public void setStatusRecyclerView(){
        statusRecycler.setHasFixedSize(true);
        statusRecycler.removeAllViews();
        statusRecycler.setNestedScrollingEnabled(false);
        statusRecycler.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(5, LinearLayoutManager.VERTICAL); // (int spanCount, int orientation)
        statusRecycler.setLayoutManager(mStaggeredVerticalLayoutManager);
        statusAdapter =
                new AnswerStatusAdapter(this,questions,responseList);
        statusRecycler.setAdapter(statusAdapter);
        statusRecycler.setLayoutManager(mStaggeredVerticalLayoutManager);

        statusAdapter.notifyDataSetChanged();
    }
//    public void setQuestionsRecyclerView(){
//        recyclerView.setHasFixedSize(true);
//        recyclerView.removeAllViews();
//        recyclerView.setNestedScrollingEnabled(false);
//        recyclerView.removeAllViewsInLayout();
//        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL); // (int spanCount, int orientation)
//        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
//        adapter =
//                new ResultQuestionAdapter(this,questions,responseList);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
//
//        adapter.notifyDataSetChanged();
//    }
    private void setData(int correct,int incorrect,int unAttempted, int count, float range) {

        float mult = range;
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();


        entries.add(new PieEntry((float) (correct),"",
                getResources().getDrawable(R.drawable.ic_star_selected)));

        entries.add(new PieEntry((float) (incorrect),"",
                getResources().getDrawable(R.drawable.ic_star_selected)));

        entries.add(new PieEntry((float) (unAttempted),"",
                getResources().getDrawable(R.drawable.ic_star_selected)));
//
//        entries.add(new PieEntry((float) (29),"",
//                getResources().getDrawable(R.drawable.ic_star_selected)));


        PieDataSet dataSet = new PieDataSet(entries, " ");
        dataSet.setColors(new int[]{R.color.green500,R.color.red500,R.color.blue},this);
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(5f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);
        // dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueTextColor(R.color.black);
        // add a lot of colors

//        ArrayList<Integer> colors = new ArrayList<Integer>();
//
//
//        for (int c : ColorTemplate.COLORFUL_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.PASTEL_COLORS)
//            colors.add(c);
//
//        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setValueTextSize(20f);
        dataSet.setValueTextColor(R.color.black);
        //dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(0f);
        data.setValueTextColor(R.color.black);
        data.setValueTypeface(mTfLight);
        mChart.setData(data);
        mChart.highlightValues(null);
        mChart.invalidate();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(ExamResultActivity.this, ExamSubCategoryListActivity.class);
        i.putExtra("category",exam.getCategory().getTitle());
// set the new task and clear flags
        //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();

    }
}
