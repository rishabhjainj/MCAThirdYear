package com.AbhiDev.edurecomm.Exams.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wireout.Activities.BaseActivity;
import com.wireout.R;
import com.wireout.models.exams.ExamSubCategory;

public class ExamInstructionActivity extends BaseActivity {

    Button startExam;
    ExamSubCategory exam;
    TextView noOfQues,difficulty,fullMarks;
    Intent i;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_inst_layout);
        noOfQues = findViewById(R.id.noOfQues);
        difficulty = findViewById(R.id.difficulty);
        fullMarks = findViewById(R.id.fullMarks);
        i = getIntent();
        exam = (ExamSubCategory) i.getSerializableExtra("exam");
        initUi(exam);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        startExam = findViewById(R.id.start_exam);
        startExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamInstructionActivity.this,ExamQuestionScreen.class);
                intent.putExtra("exam",exam);
                startActivity(intent);
            }
        });

    }

    public void initUi(ExamSubCategory exam){
        noOfQues.setText("Total Number of Questions: "+exam.getQuestions().size());
        difficulty.setText("Difficulty Level: "+exam.getQuestions().get(0).getDifficulty());
        fullMarks.setText("Full Marks: "+exam.getQuestions().size()*5);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
