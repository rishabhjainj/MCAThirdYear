package com.AbhiDev.edurecomm.Activities.Analysis;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.google.gson.Gson;
import com.mvc.imagepicker.ImagePicker;
import com.squareup.picasso.Picasso;
import com.wireout.Activities.Recommendations;
import com.wireout.common.ImageCompressor;
import com.wireout.listeners.OnEntityReceivedListener;
import com.wireout.models.career_analysis.CareerAnalysis;
import com.wireout.models.career_analysis.Report;
import com.wireout.models.career_analysis.Section13;
import com.wireout.presenters.BooleanQuestionPresenter;
import com.wireout.R;
import com.wireout.common.MyApplication;
import com.wireout.listeners.AnalysisEventListener;
import com.wireout.models.career_analysis.BooleanQuestion;
import com.wireout.presenters.AnalysisPresenter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.wireout.Activities.Analysis.AnalysisStatusActivity.ANALYSIS_COMPLETED;

public class HandwritingSection extends AnalysisActivity implements AnalysisEventListener {

    AnalysisPresenter presenter;
    Section13 section13;
    OnEntityReceivedListener<Report> reportListener;
    RadioButton left,right;
    Intent intent;
    OnEntityReceivedListener<CareerAnalysis> lifeChoicesListener;
    int count = 0;
    ImageView backgroundImage;
    Bitmap bitmap;
    String writingHand;
    MultipartBody.Part filePart;
    OnEntityReceivedListener<Section13> listener;
    BooleanQuestion meornotmeQuestions,abilityQuestions,interestQuestions,egogramQuestions,motivationalQuestions,questions;
    public static final String HANDWRITINGSECTIONCOMPLETED = "Handwriting_section_completed";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        section13 = new Section13(1);
        MyApplication.getInstance().prefManager.saveString(HANDWRITINGSECTIONCOMPLETED,"true");
        listener = new OnEntityReceivedListener<Section13>(this) {
            @Override
            public void onReceived(Section13 entity) {
                showMessage("saved");
            }
        };
        reportListener = new OnEntityReceivedListener<Report>(this) {
            @Override
            public void onReceived(Report entity) {
                Gson gson = new Gson();
                String json = gson.toJson(entity,Report.class);
                MyApplication.getInstance().prefManager.saveString("report",json);

            }
        };
        lifeChoicesListener = new OnEntityReceivedListener<CareerAnalysis>(this) {
            @Override
            public void onReceived(CareerAnalysis status) {
                if(status.getSection2()!=null&&status.getSection3()!=null&&status.getSection4()!=null&&status.getSection5()!=null
                        &&status.getSection6()!=null&&status.getSection7()!=null&&status.getSection8()!=null&&status.getSection9()!=null&&status.getSection10()!=null
                        &&status.getSection11()!=null&&status.getSection12()!=null){
                    MyApplication.getInstance().prefManager.saveString(ANALYSIS_COMPLETED,"true");
                    //presenter.getAnalysisReport(reportListener);
                    //Toast.makeText(getApplicationContext(),"Please Wait",Toast.LENGTH_SHORT).show();
//                    intent = new Intent(getApplicationContext(), Recommendations.class);
//                    intent.putExtra("key", 1);
//                    startActivity(intent);
                    startActivity(new Intent(HandwritingSection.this,LoaderActivity.class));


                }

                else{
                    MyApplication.getInstance().prefManager.saveString(ANALYSIS_COMPLETED,null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(HandwritingSection.this);
// Add the buttons
                    builder.setMessage("We recommend you to complete all sections before you can view your career report.");
                    builder.setPositiveButton("Resume", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startActivity(new Intent(HandwritingSection.this,AnalysisStatusActivity.class));
                            finish();
                        }
                    });
                    builder.setNegativeButton("View Sample Report", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), Recommendations.class);
                            intent.putExtra("key", 1);
                            startActivity(intent);
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }
        };
        presenter = new AnalysisPresenter(this);
        if(getIntent().getSerializableExtra("questionsSection2")!=null)
            meornotmeQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection2");
        if(getIntent().getSerializableExtra("questionsSection3")!=null)
            abilityQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection3");
        if(getIntent().getSerializableExtra("questionsSection4")!=null)
            interestQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection4");
        if(getIntent().getSerializableExtra("questionsSection5")!=null)
            egogramQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection5");
        if(getIntent().getSerializableExtra("questions")!=null)
            questions = (BooleanQuestion)getIntent().getSerializableExtra("questions");
        presenter.startHandwriting();


    }
    @Override
    public void onBackPressed() {
        if(count==0){
            Intent intent = new Intent(HandwritingSection.this, LogicalReasoningSection.class);
            intent.putExtra("questionsSection2",meornotmeQuestions);
            intent.putExtra("questionsSection3",abilityQuestions);
            intent.putExtra("questionsSection4",interestQuestions);
            intent.putExtra("questionsSection5",egogramQuestions);
            intent.putExtra("questions",questions);
            startActivity(intent);
            finish();
        }
        else
        showMessage("Back button has been disabled for analysis purpose.");
    }

    @Override
    public void showPersonalIntroScreen() {
        count=0;
        setContentView(R.layout.intro_screen_layout);
//        backgroundIntro = findViewById(R.id.background);
//        backgroundIntro.setBackground(getResources().getDrawable(R.drawable.handwritinganalysis_start_screen));
        backgroundImage = findViewById(R.id.imageView);
        Picasso.with(this).load(R.drawable.handwritinganalysis_start_screen).into(backgroundImage);
        startSection = (Button) findViewById(R.id.start);
        skipSection = findViewById(R.id.skip);
        skipSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HandwritingSection.this, AnalysisStatusActivity.class));
                finish();
            }
        });
        startSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHandwritingScreen();
            }
        });
    }

    @Override
    public void showHandwritingScreen() {
        count=1;
        setContentView(R.layout.handwriting_screen_layout);
        next =(Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHandwritingSectionEndScreen();
            }
        });
        upload = findViewById(R.id.uploadImage);
        exitSection = findViewById(R.id.endbtn);
        exitSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HandwritingSection.this, AnalysisStatusActivity.class));
                finish();
            }
        });

        try {
            ImagePicker.setMinQuality(600, 600);
            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onPickImage(upload);


                }
            });
//
        }
        catch (Exception e){
            Log.d("exc upload image",e+"");
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(left.isChecked())
                    writingHand = "left";
                else if(right.isChecked())
                    writingHand = "right";
                else
                    writingHand ="not specified";
                if(bitmap!=null){
                    try {


                        ImageCompressor imageCompressor = new ImageCompressor(getApplicationContext());

                        Uri uri = imageCompressor.compress(bitmap);
                        Log.d("fileuri", uri + "");

                        if (uri == null) {
                            showMessage("Unable to save image to internal storage for upload. Please check Write Permissions for Funcandi in Settings. ");
                            return;
                        }

                        File file = new File(uri.getPath());
                        filePart = MultipartBody.Part.createFormData("uploadFile", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));

                    }
                    catch (Exception e){
                        showMessage("There was some problem submitting your result!");
                    }
                    Map<String,String> map  = new HashMap<>();
                    map.put("writingHand",writingHand);
                    section13.submit(map,filePart,listener);
                }
                else{
                    //showMessage("bitmap is null");
                    Map<String,String> map  = new HashMap<>();
                    map.put("writingHand",writingHand);
                    section13.submit(map,filePart,listener);
                }
               // presenter.next();
                showHandwritingSectionEndScreen();

            }
        });
        final ImagePopup imagePopup = new ImagePopup(this);
        imagePopup.setBackgroundColor(getResources().getColor(R.color.blue));
        imagePopup.setWindowHeight(100);
        imagePopup.setWindowWidth(800);
        imagePopup.setHideCloseIcon(true);
        imagePopup.setImageOnClickClose(true);

        final ImageView texty= (ImageView) findViewById(R.id.sample);

        texty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Initiate Popup view **/
                imagePopup.initiatePopup(getResources().getDrawable(R.drawable.handwriting_sample));
                // imagePopup.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        });


    }
    public void showHandwritingSectionEndScreen(){
        setContentView(R.layout.analysis_section_end_screen);
        LinearLayout bottomLayout;
        TextView sectionName;
        TextView sectionDuration;
        ImageView exitSection;
        TextView completedText;
        final ImageView continueSection;
        sectionDuration = findViewById(R.id.duration);
        sectionName = findViewById(R.id.section_name);
        bottomLayout = findViewById(R.id.bottomLayout);
        completedText = findViewById(R.id.completedText);
        exitSection = findViewById(R.id.exit);
        continueSection = findViewById(R.id.continue_next);
        sectionDuration.setVisibility(View.GONE);
        bottomLayout.setVisibility(View.GONE);
        sectionName.setVisibility(View.GONE);
        completedText.setText("You have successfully completed this section.Click Continue to go to Report.");
        continueSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if((MyApplication.getInstance().prefManager.getString(AnalysisBioDataSection.HISTORYNGOALSSECTIONCOMPLETED)!=null)&&(MyApplication.getInstance().prefManager.getString(MeOrNotMeSection.MEORNOTMECOMPLETED)!=null)
//                        &&(MyApplication.getInstance().prefManager.getString(AbilitySection.ABILITYCOMPLETED)!=null)&&(MyApplication.getInstance().prefManager.getString(InterstSection.INTERESTCOMPLETED)!=null)
//                        &&(MyApplication.getInstance().prefManager.getString(LifeChoicesSection.LIFECHOICESSECTIONCOMPLETED)!=null)&&(MyApplication.getInstance().prefManager.getString(SectionEndActivity.VERBALABILITYCOMPLETED)!=null)&&
//                        (MyApplication.getInstance().prefManager.getString(SectionEndActivity.BRAINBOOSTERCOMPLETED)!=null)&&
//                        (MyApplication.getInstance().prefManager.getString(YourEgogramSection.EGOGRAMCOMPLETED)!=null)&&(MyApplication.getInstance().prefManager.getString(EmotionalIntelligenceSection.EMOTIONALSECTIONCOMPLETED)!=null)&&(MyApplication.getInstance().prefManager.getString(SectionEndActivity.FLEXIBILITYGAMECOMPLETED)!=null)
//                        &&(MyApplication.getInstance().prefManager.getString(SectionEndActivity.MATHGAMECOMPLETED)!=null)&&(MyApplication.getInstance().prefManager.getString(MotivationalQuotientSection.MOTIVATIONALCOMPLETED)!=null)&&
//                        (MyApplication.getInstance().prefManager.getString(HandwritingSection.HANDWRITINGSECTIONCOMPLETED)!=null)) {
//                    if((MyApplication.getInstance().prefManager.getString(AnalysisBioDataSection.HISTORYNGOALSSECTIONCOMPLETED).equals("true"))&&(MyApplication.getInstance().prefManager.getString(MeOrNotMeSection.MEORNOTMECOMPLETED).equals("true"))&&
//                            (MyApplication.getInstance().prefManager.getString(AbilitySection.ABILITYCOMPLETED).equals("true"))&&(MyApplication.getInstance().prefManager.getString(InterstSection.INTERESTCOMPLETED).equals("true"))
//                            &&(MyApplication.getInstance().prefManager.getString(YourEgogramSection.EGOGRAMCOMPLETED).equals("true"))&&(MyApplication.getInstance().prefManager.getString(LifeChoicesSection.LIFECHOICESSECTIONCOMPLETED).equals("true"))
//                            &&(MyApplication.getInstance().prefManager.getString(SectionEndActivity.VERBALABILITYCOMPLETED).equals("true"))&&(MyApplication.getInstance().prefManager.getString(SectionEndActivity.BRAINBOOSTERCOMPLETED).equals("true"))
//                            &&(MyApplication.getInstance().prefManager.getString(EmotionalIntelligenceSection.EMOTIONALSECTIONCOMPLETED).equals("true"))&&(MyApplication.getInstance().prefManager.getString(SectionEndActivity.FLEXIBILITYGAMECOMPLETED).equals("true"))
//                            &&(MyApplication.getInstance().prefManager.getString(SectionEndActivity.MATHGAMECOMPLETED).equals("true")) &&(MyApplication.getInstance().prefManager.getString(MotivationalQuotientSection.MOTIVATIONALCOMPLETED).equals("true"))&&
//                            (MyApplication.getInstance().prefManager.getString(HandwritingSection.HANDWRITINGSECTIONCOMPLETED).equals("true"))) {
                presenter.postAnalysis(lifeChoicesListener);

            }

        });
        exitSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HandwritingSection.this, AnalysisStatusActivity.class));
                finish();
            }
        });

    }
    public boolean saveImageToInternalStorage(Bitmap image) {

        try {
// Use the compress method on the Bitmap object to write image to
// the OutputStream
            FileOutputStream fos = this.openFileOutput("desiredFilename.png", Context.MODE_PRIVATE);

// Writing the bitmap to the output stream
            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
            Toast.makeText(this,"stored",Toast.LENGTH_SHORT).show();

            return true;
        } catch (Exception e) {
            Log.e("saveToInternalStorage()", e.getMessage());

            return false;
        }
    }
    public Bitmap getThumbnail(String filename) {


        Bitmap thumbnail = null;

// If no file on external storage, look in internal storage

        try {
            File filePath = this.getFileStreamPath(filename);
            FileInputStream fi = new FileInputStream(filePath);
            thumbnail = BitmapFactory.decodeStream(fi);
        } catch (Exception ex) {
            Log.e("getThumbnail() internal", ex.getMessage());
        }

        return thumbnail;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        bitmap = ImagePicker.getImageFromResult(this, requestCode, resultCode, data);
        saveImageToInternalStorage(bitmap);
        bitmap = getThumbnail("desiredFilename.png");


        if(bitmap!=null){
            upload.setImageBitmap(bitmap);


        }

        super.onActivityResult(requestCode, resultCode, data);

    }


}
