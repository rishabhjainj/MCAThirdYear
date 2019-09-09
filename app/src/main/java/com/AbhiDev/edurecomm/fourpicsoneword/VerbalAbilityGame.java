package com.AbhiDev.edurecomm.fourpicsoneword;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.wireout.Activities.Analysis.AnalysisStatusActivity;
import com.wireout.Activities.Analysis.SectionEndActivity;

import com.wireout.Activities.BaseActivity;
import com.wireout.listeners.OnEntityReceivedListener;
import com.wireout.models.career_analysis.CareerAnalysis;
import com.wireout.R;
import com.wireout.common.PrefManager;
import com.wireout.models.career_analysis.BooleanQuestion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VerbalAbilityGame extends BaseActivity implements ViewAction  {

    ArrayList<CharactersTextView> charactersTextViews = new ArrayList<>();
    ArrayList<ImageView> imageViews = new ArrayList<>();
    ArrayList<TextView> gameWordTextViews = new ArrayList<>();
    LinearLayout gameWordContainer;
    OnEntityReceivedListener<CareerAnalysis> listener;
    Presenter presenter;
    CardView actionButton;
    BooleanQuestion meornotmeQuestions,abilityQuestions,interestQuestions,egogramQuestions,questions;
    TextView actionTextView;
    CardView hintButton;
    PrefManager prefManager;
    TextView exitSection;

    @Override
    public void onBackPressed() {
        showMessage("Back button has been disabled for analysis purpose.");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_4pics1word);
        listener = new OnEntityReceivedListener<CareerAnalysis>(this) {
            @Override
            public void onReceived(CareerAnalysis entity) {
                //showMessage("saved");
            }
        };
        if(getIntent().getSerializableExtra("questionsSection2")!=null) {
            meornotmeQuestions = (BooleanQuestion) getIntent().getSerializableExtra("questionsSection2");
        }
        else{
            showMessage("null");
        }
        if(getIntent().getSerializableExtra("questionsSection3")!=null)
            abilityQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection3");
        if(getIntent().getSerializableExtra("questionsSection4")!=null)
            interestQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection4");
        if(getIntent().getSerializableExtra("questionsSection5")!=null)
            egogramQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection5");
        if(getIntent().getSerializableExtra("questions")!=null)
            questions = (BooleanQuestion)getIntent().getSerializableExtra("questions");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prefManager = new PrefManager(this);
        presenter = new Presenter(this, prefManager);
        initViews();
        exitSection = findViewById(R.id.endbtn);
        exitSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VerbalAbilityGame.this, AnalysisStatusActivity.class);
                i.putExtra("questionsSection2",meornotmeQuestions);
                i.putExtra("questionsSection3",abilityQuestions);
                i.putExtra("questionsSection4",interestQuestions);
                i.putExtra("questionsSection5",egogramQuestions);
                i.putExtra("questions",questions);
                startActivity(i);
                finish();
            }
        });
        presenter.start();
    }

    void initViews(){

        gameWordContainer = (LinearLayout) findViewById(R.id.gameWordContainer);

        charactersTextViews.add((CharactersTextView) findViewById(R.id.character1));
        charactersTextViews.add((CharactersTextView) findViewById(R.id.character2));
        charactersTextViews.add((CharactersTextView) findViewById(R.id.character3));
        charactersTextViews.add((CharactersTextView) findViewById(R.id.character4));
        charactersTextViews.add((CharactersTextView) findViewById(R.id.character5));
        charactersTextViews.add((CharactersTextView) findViewById(R.id.character6));
        charactersTextViews.add((CharactersTextView) findViewById(R.id.character7));
        charactersTextViews.add((CharactersTextView) findViewById(R.id.character8));
        charactersTextViews.add((CharactersTextView) findViewById(R.id.character9));
        charactersTextViews.add((CharactersTextView) findViewById(R.id.character10));
        charactersTextViews.add((CharactersTextView) findViewById(R.id.character11));
        charactersTextViews.add((CharactersTextView) findViewById(R.id.character12));

        imageViews.add((ImageView) findViewById(R.id.image1));
        imageViews.add((ImageView) findViewById(R.id.image2));
        imageViews.add((ImageView) findViewById(R.id.image3));
        imageViews.add((ImageView) findViewById(R.id.image4));

        actionButton = (CardView) findViewById(R.id.actionButton);
        actionTextView = (TextView) findViewById(R.id.actionTextView);
        hintButton = (CardView) findViewById(R.id.hintButton);
        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.showHint();
            }
        });

    }

    void generatePlayWordBoxes(String correctAnswer){
        gameWordContainer.removeAllViews();
        gameWordTextViews.clear();
        for(int i = 0; i<correctAnswer.length(); i++){
            CardView cardView = new CardView(this);
            cardView.setCardElevation(5);
            cardView.setMinimumWidth(20);
            final TextView textView = new TextView(this);
            textView.setPadding(5,5,5,5);
            textView.setTextSize(40);
            textView.setTextColor(getResources().getColor(R.color.white));
            textView.setBackgroundColor(getResources().getColor(R.color.red));
            textView.setTypeface(null, Typeface.BOLD);
            textView.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(100, Toolbar.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams llp2 = new LinearLayout.LayoutParams(Toolbar.LayoutParams.FILL_PARENT, Toolbar.LayoutParams.FILL_PARENT);
            llp.setMargins(5, 5, 5, 5); // llp.setMargins(left, top, right, bottom);
            llp.gravity = Gravity.CENTER_HORIZONTAL;
            cardView.setLayoutParams(llp);
            cardView.setPadding(5,5,5,5);
            textView.setText("0");

            final Integer j = new Integer(i);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(CharactersTextView charactersTextView : charactersTextViews){
                        if(charactersTextView.getText().toString().equals(textView.getText().toString())){
                            textView.setText("");
                            charactersTextView.setVisibility(View.VISIBLE);
                            break;
                        }
                    }
                    String newPlayWord = "";
                    for(TextView textView1 : gameWordTextViews){
                        if(!textView1.getText().equals(""))
                            newPlayWord += textView.getText();
                    }
                    presenter.deselectCharacter(j,newPlayWord);
                }
            });
            gameWordTextViews.add(textView);
            cardView.addView(textView);
            gameWordContainer.addView(cardView);
            textView.setText("");
        }
    }

    @Override
    public void setScreen(GameLevelModel level) {
        generatePlayWordBoxes(level.getCorrectAnswer());

        int i;

        i=0;
        for(CharactersTextView charactersTextView : charactersTextViews){
            charactersTextView.setText(level.getCharacters().charAt(i++) + "");
            charactersTextView.setVisibility(View.VISIBLE);
        }

        i=0;
        for(ImageView imageView : imageViews){
            imageView.setImageResource(level.getImages()[i++]);
        }

        actionTextView.setText("SKIP");
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.next();
            }
        });

    }

    @Override
    public void showLevelSuccess() {
        showMessage("Correct Answer!");
    }

    @Override
    public void showLevelFailure() {
        showMessage("This isn't the correct word!");
    }

    @Override
    public void setNextButton() {
        actionTextView.setText("NEXT");
    }

    @Override
    public String updatePlayWord(String character) {
        int i = 0;
        String newPlayWord = "";
        for(TextView textView : gameWordTextViews){
            if(textView.getText().equals("")){
                textView.setText(character);
                newPlayWord += textView.getText();
                break;
            } else{
                newPlayWord += textView.getText();
            }

            i++;
        }
        return newPlayWord;
    }

    @Override
    public void gameOver() {
        //ToDo : pass gameOver screen here and uncomment the line below
        //setContentView(R.layout.activity_act_4pics1word);
        Intent i = new Intent(this, SectionEndActivity.class);
        i.putExtra("section","6");
        i.putExtra("questionsSection2",meornotmeQuestions);
        i.putExtra("questionsSection3",abilityQuestions);
        i.putExtra("questionsSection4",interestQuestions);
        i.putExtra("questionsSection5",egogramQuestions);
        i.putExtra("questions",questions);
        Map<String,String> map = new HashMap<>();
        map.put("section7",presenter.numCorrect+"");
        //showMessage("correct"+presenter.correct);
        presenter.postAnalysis(map, listener);
        startActivity(i);


    }

    @Override
    public void removeListenersFromTextViews(){
        for(TextView textView : gameWordTextViews){
            textView.setOnClickListener(null);
        }
    }

    @Override
    public void showHintScreen(ArrayList<String> jumbledWords) {
        Intent i = new Intent(VerbalAbilityGame.this, HintActivity.class);
        i.putExtra("jumbledWords", jumbledWords);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_hint, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.hint:
                presenter.showHint();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}