package com.AbhiDev.edurecomm.Activities.Analysis;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import com.wireout.listeners.OnEntityReceivedListener;
import com.wireout.models.career_analysis.ExperienceDetail;
import com.wireout.models.career_analysis.HigherEducationDetail;
import com.wireout.models.career_analysis.Section1;
import com.wireout.R;
import com.wireout.common.MultiSelectSpinner;
import com.wireout.common.MyApplication;
import com.wireout.listeners.AnalysisEventListener;
import com.wireout.models.career_analysis.BooleanQuestion;
import com.wireout.presenters.AnalysisPresenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AnalysisBioDataSection extends AnalysisActivity implements AnalysisEventListener {

    Spinner highestEducation,jobStatusSpinner;
    EditText designation;
    int records;
    Spinner marksSpinner;
    LayoutInflater inflater;
    AnalysisPresenter presenter;
    String higherEduViewList="";
    LinearLayout gameInstLayout;
    ArrayAdapter<String> marks12typeAdapter;
    ArrayAdapter<String> marks10typeAdapter;
    EditText name;
    EditText fromEditText;
    OnEntityReceivedListener<Section1> listener;
    String myGender;
    LinearLayout addViewHereLayout;
    List<HigherEducationDetail> higherEducationDetailList = new ArrayList<>();
    List<ExperienceDetail> experienceDetailList = new ArrayList<>();
    Spinner modeSpinner;
    Gson gson;
    EditText college,year,marks,course;
    String json;
    ImageView skipButton;
    MultiSelectSpinner categorySpinner;
    Spinner countrySpinner;
    EditText marks10,marks12,yearof10,yearof12;
    TextView gameInstructions;
    MultiSelectSpinner subjectSpinner,gameSpinner,pastTimeSpinner;
    ImageView instruct;
    ImageView startButton;
    Section1 section1;
    Spinner spinner;
    ArrayList<HigherEducationDetail> professionalDetailsList= new ArrayList<>();
    Spinner subjectSpinnerr;
    TextView textStart;
    ImageView gameImage;
    TextView gameName;
    AnalysisActivity obj;
    TextView textSkip;
    ArrayList<View> higherEducationViews;
    ArrayList<View> professionalViews;
    ArrayList<View> experienceViews;
    ArrayList<View> examScoreViews;
    EditText toEditText,yearOfAdmission;
    BooleanQuestion meornotmeQuestions,abilityQuestions,interestQuestions,egogramQuestions,motivationalQuestions;

    public static final String HISTORYNGOALSSECTIONCOMPLETED = "History_and_goals_section_completed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        section1 = new Section1(3);
        MyApplication.getInstance().prefManager.saveString(HISTORYNGOALSSECTIONCOMPLETED,"true");
       //setContentView(R.layout.activity_analysis_bio_data_section);
        presenter = new AnalysisPresenter(this);
        presenter.startBioData();
        obj = new AnalysisActivity();

        listener = new OnEntityReceivedListener<Section1>(this) {
            @Override
            public void onReceived(Section1 entity) {
               // showMessage("saved");
            }
        };
        if(getIntent().getSerializableExtra("questionsSection2")!=null)
            meornotmeQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection2");
        if(getIntent().getSerializableExtra("questionsSection3")!=null)
            abilityQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection3");
        if(getIntent().getSerializableExtra("questionsSection4")!=null)
            interestQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection4");
        if(getIntent().getSerializableExtra("questionsSection5")!=null)
            egogramQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questionsSection5");
        if(getIntent().getSerializableExtra("questions")!=null)
            motivationalQuestions = (BooleanQuestion)getIntent().getSerializableExtra("questions");


        //showBioDataScreen();

    }
    @Override
    public void showIntroScreen() {
        setContentView(R.layout.intro_screen_layout);
          startSection = (Button) findViewById(R.id.start);
          skipSection = findViewById(R.id.skip);

        skipSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AnalysisBioDataSection.this,MeOrNotMeSection.class);
                intent.putExtra("questionsSection2",meornotmeQuestions);
                intent.putExtra("questionsSection3",abilityQuestions);
                intent.putExtra("questionsSection4",interestQuestions);
                intent.putExtra("questionsSection5",egogramQuestions);
                intent.putExtra("questions",motivationalQuestions);
                startActivity(intent);
                finish();
            }
        });
        startSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });

    }
    public void setColorOnLayout(int color,int mode){
        //gameInstLayout.setBackgroundColor(color);
        gameInstructions.setTextColor(color);
        instruct.setColorFilter(color);
        gameName.setTextColor(color);
        textStart.setTextColor(color);
        startButton.setColorFilter(color);
        if(mode==1){
            skipButton.setColorFilter(color);
            textSkip.setTextColor(color);
        }
    }
    public void exitSection(){
        exitSection = findViewById(R.id.endbtn);
        exitSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AnalysisBioDataSection.this, AnalysisStatusActivity.class));
                finish();
            }
        });
    }
//
    @Override
    public void showBioDataScreen() {
        //higheest education study info
        setContentView(R.layout.bio_data_layout);
        yearOfAdmission = findViewById(R.id.input_yoadmission);
        exitSection();
        modeSpinner = findViewById(R.id.modeSpinner);
        modeSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> modeArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.modeofstudy));
        modeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSpinner.setAdapter(modeArrayAdapter);

        highestEducation = findViewById(R.id.spinnerEducationLevel);
        jobStatusSpinner = findViewById(R.id.jobStatusSpinner);
        highestEducation.setOnItemSelectedListener(this);
        ArrayAdapter<String> higherEduArray = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.highesteducationoptions));
        higherEduArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        highestEducation.setAdapter(higherEduArray);

        jobStatusSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> jobStatusArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.jobstatuses));
        jobStatusArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobStatusSpinner.setAdapter(jobStatusArrayAdapter);

        List<String> countries = new ArrayList<>();
        countries.add( "Afghanistan");
        countries.add("Albania");
        countries.add( "Algeria");
        countries.add( "Andorra");
        countries.add("Angola");
        countries.add("Anguilla");
        countries.add( "Antigua & Barbuda");
        countries.add( "Argentina");
        countries.add( "Australia");
        countries.add( "Austria");
        countries.add( "Bahamas");
        countries.add( "Bahrain");
        countries.add("Bangladesh");
        countries.add( "Barbados");
        countries.add( "Belarus");
        countries.add( "Belgium");
        countries.add("Belize");
        countries.add( "Benin");
        countries.add( "Bermuda");
        countries.add( "Bhutan");
        countries.add( "Bolivia");
        countries.add( "Brazil");
        countries.add("Bulgaria");
        countries.add("Cambodia");
        countries.add("Cameroon");
        countries.add("Canada");
        countries.add("Cape Verde");
        countries.add( "Cayman Islands");
        countries.add("Central African Republic");
        countries.add("Chad");
        countries.add("Chile");
        countries.add("China");
        countries.add("Colombia");
        countries.add("Comoros");
        countries.add("Congo");
        countries.add("Costa Rica");
        countries.add("Croatia");
        countries.add("Cuba");
        countries.add("Cyprus");
        countries.add("Czech Republic");
        countries.add("Denmark");
        countries.add("Djibouti");
        countries.add("Dominica");
        countries.add("Ecuador");
        countries.add("Egypt");
        countries.add("El Salvador");
        countries.add("Equatorial Guinea");
        countries.add("Estonia");
        countries.add("Ethiopia");
        countries.add("Fiji");
        countries.add("Finland");
        countries.add("France");
        countries.add("French Guiana");
        countries.add("Gabon");
        countries.add("Gambia");
        countries.add("Georgia");
        countries.add("Germany");
        countries.add("Ghana");
        countries.add("Great Britain");
        countries.add("Greece");
        countries.add("Grenada");
        countries.add("Guadeloupe");
        countries.add("Guatemala");
        countries.add("Guinea");
        countries.add("Haiti");
        countries.add("Honduras");
        countries.add("Hungary");
        countries.add("Iceland");
        countries.add("India");
        countries.add("Indonesia");
        countries.add("Iran");
        countries.add("Iraq");
        countries.add("Italy");
        countries.add("Jamaica");
        countries.add("Japan");
        countries.add("Jordan");
        countries.add("Kazakhstan");
        countries.add("Kenya");
        countries.add("Kosovo");
        countries.add("Kuwait");
        countries.add("Laos");
        countries.add("Latvia");
        countries.add("Lebanon");
        countries.add("Libya");
        countries.add("Lithuania");
        countries.add("Luxembourg");
        countries.add("Madagascar");
        countries.add("Malawi");
        countries.add("Malaysia");
        countries.add("Maldives");
        countries.add("Mali");
        countries.add("Malta");
        countries.add("Mauritius");
        countries.add("Mexico");
        countries.add("Monaco");
        countries.add("Mongolia");
        countries.add("Morocco");
        countries.add("Namibia");
        countries.add("Nepal");
        countries.add("Netherlands");
        countries.add("New Zealand");
        countries.add("Niger");
        countries.add("Nigeria");
        countries.add("Korea");
        countries.add("Norway");
        countries.add("Oman");
        countries.add("Pacific Islands");
        countries.add("Pakistan");
        countries.add("Panama");
        countries.add("Peru");
        countries.add("Philippines");
        countries.add("Poland");
        countries.add("Portugal");
        countries.add("Qatar");
        countries.add("Romania");
        countries.add("Russian Federation");
        countries.add("Samoa");
        countries.add("Saudi Arabia");
        countries.add("Serbia");
        countries.add("Slovak Republic ");
        countries.add("South Africa");
        countries.add("Spain");
        countries.add("Sri Lanka");
        countries.add("Sweden");
        countries.add("Switzerland");
        countries.add("Syria");
        countries.add("Tajikistan");
        countries.add("Tanzania");
        countries.add("Thailand");
        countries.add("Togo");
        countries.add("Tunisia");
        countries.add("Turkey");
        countries.add("Uganda");
        countries.add("Uzbekistan");
        countries.add("United Arab Emirates");
        countries.add("United States of America");
        countries.add("Venezuela");
        countries.add("Vietnam");
        countries.add("Yemen");
        countries.add("Zambia");
        countries.add("Zimbabwe");
        countrySpinner = findViewById(R.id.countrySpinner);
        countrySpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> countryArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, countries);
        countryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(countryArrayAdapter);
        String myString = "India"; //the value you want the position for

        ArrayAdapter myAdap = (ArrayAdapter) countrySpinner.getAdapter(); //cast to an ArrayAdapter

        int spinnerPosition = myAdap.getPosition(myString);

//set the default according to value
        countrySpinner.setSelection(spinnerPosition);


        setProgressBarData(14);
        next = (Button) findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);


        dateEditText = (EditText)findViewById(R.id.dob);
        gender = (RadioGroup)findViewById(R.id.gender);
        girl = (RadioButton)findViewById(R.id.radiogirl);
        boy = (RadioButton)findViewById(R.id.radioboy);
        name = (EditText)findViewById(R.id.input_name);
        age = (EditText)findViewById(R.id.dob);
        girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boy.setChecked(false);
                myGender = "female";
            }
        });
        boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girl.setChecked(false);
                myGender = "male";
            }
        });



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                json = MyApplication.getInstance().prefManager.getString("section1");
                gson = new Gson();
                if(json!=null) {
                    section1 = (Section1) gson.fromJson(json, Section1.class);
                    //showMessage("Already saved data will be ovewritten");
                }
                section1.setName(name.getText().toString());
                section1.setGender(myGender);

                try {
                    Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateEditText.getText().toString());
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String parsedDate = formatter.format(initDate);
                    section1.setDob(parsedDate+"T00:00:00.000000Z");
                }
                catch (ParseException e){

                }
                section1.setCountry(countrySpinner.getSelectedItem().toString()+"");
                section1.setJobStatus(jobStatusSpinner.getSelectedItem().toString()+"");
                section1.setHigherEducationLevel(highestEducation.getSelectedItem().toString()+"");
                section1.setModeOfStudy(modeSpinner.getSelectedItem().toString()+"");
                try {
                    if (yearOfAdmission.getText().toString().equals("")) {
                        section1.setYear(0);
                    } else
                        section1.setYear(Integer.parseInt(yearOfAdmission.getText().toString() + ""));
                }
                catch (Exception e){
                    Log.d("yearerror","error"+e);
                }
                saveSection1DataToPreferences(section1,1);
//                if(!section1.isScreenValidated(1)){
//                    //showMessage("All Fields are Required!");
//                }
//                else{
//                    //showMessage("Validated!");
//                }
                presenter.next();


            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        if(MyApplication.getInstance().prefManager.getString("section1")!=null){
            if(MyApplication.getInstance().prefManager.getString("screen1")!=null)
            setDataBack(1);
        }

    }
    public String changeDateFormat(String date){
        try {
            Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String parsedDate = formatter.format(initDate);
            return (parsedDate+"T00:00:00.000000Z");
        }
        catch (ParseException e){
            return "2018-01-01T00:00:00.000000Z";
        }

    }
    public String getNormalDate(String changedDate){
        try {
            if(changedDate.equals("2018-01-01T00:00:00.000000Z")){
                return "";
            }
            else {
                String date;
                if (changedDate.length() > 10) {
                    date = changedDate.substring(0, 10);
                    Date initDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String parsedDate = formatter.format(initDate);
                    return parsedDate;
                } else {
                    return null;
                }
            }
        } catch (ParseException e) {

            return "";
        }
    }
    public void saveSection1DataToPreferences(Section1 section1,int screen){
        switch (screen) {
            case 1:
               // showMessage("saving data for this screen");
                Gson gson = new Gson();
                String json = gson.toJson(section1);
                MyApplication.getInstance().prefManager.saveString("section1", json);
                MyApplication.getInstance().prefManager.saveString("screen1", "true");
            break;
            case 2:
               // showMessage("saving data for preferences screen");
                gson = new Gson();
                json = gson.toJson(section1);
                MyApplication.getInstance().prefManager.saveString("section1", json);
                MyApplication.getInstance().prefManager.saveString("screen2", "true");
                break;
            case 3:
                gson = new Gson();
                json = gson.toJson(section1);
                MyApplication.getInstance().prefManager.saveString("section1", json);
                MyApplication.getInstance().prefManager.saveString("screen3", "true");
                break;
            case 4:
                gson = new Gson();
                json = gson.toJson(section1);
                MyApplication.getInstance().prefManager.saveString("section1",json);
                MyApplication.getInstance().prefManager.saveString("screen4", "true");
                break;

            case 5:
                gson = new Gson();
                json = gson.toJson(section1);
                MyApplication.getInstance().prefManager.saveString("section1",json);
                MyApplication.getInstance().prefManager.saveString("screen5", "true");
                break;
            case 6:
                gson = new Gson();
                json = gson.toJson(section1);
                MyApplication.getInstance().prefManager.saveString("section1",json);
                MyApplication.getInstance().prefManager.saveString("screen6", "true");
                break;
        }

    }
    public void setDataBack(int screen){
        switch (screen) {
            case 1: // bio data
                String json = MyApplication.getInstance().prefManager.getString("section1");
                Gson gson = new Gson();
                if(json!=null) {
                    Section1 section1 = (Section1) gson.fromJson(json, Section1.class);
                    myGender = section1.getGender();
                    try {
                        if (Integer.parseInt(section1.getYear().toString()) == 0) {
                            yearOfAdmission.setText("");
                        } else
                            yearOfAdmission.setText(section1.getYear() + "");
                    }
                    catch (Exception e){
                        Log.d("yearerror","error"+e);
                    }
                    name.setText(section1.getName());
                    ArrayAdapter myAdap = (ArrayAdapter) countrySpinner.getAdapter(); //cast to an ArrayAdapter
                    int spinnerPosition = myAdap.getPosition(section1.getCountry());
                    countrySpinner.setSelection(spinnerPosition);

                    myAdap = (ArrayAdapter) highestEducation.getAdapter();
                    spinnerPosition = myAdap.getPosition(section1.getHigherEducationLevel());
                    highestEducation.setSelection(spinnerPosition);

                    myAdap = (ArrayAdapter) modeSpinner.getAdapter();
                    spinnerPosition = myAdap.getPosition(section1.getModeOfStudy());
                    modeSpinner.setSelection(spinnerPosition);

                    myAdap = (ArrayAdapter) jobStatusSpinner.getAdapter();
                    spinnerPosition = myAdap.getPosition(section1.getJobStatus());
                    jobStatusSpinner.setSelection(spinnerPosition);

                    String gender = section1.getGender();
                    //showMessage(section1.getGender());
                    if (gender != null)
                        if (gender.equals("male")) {
                            boy.setChecked(true);
                            girl.setChecked(false);
                        }
                        else {
                            boy.setChecked(false);
                            girl.setChecked(true);
                        }


                    if (section1.getDob() != null) {
                        try {
                            String date = section1.getDob().substring(0, 10);
                            Date initDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            String parsedDate = formatter.format(initDate);
                            dateEditText.setText(parsedDate);
                        } catch (ParseException e) {

                        }
                    }
                }
            break;
            case 2: // preferences screen
                json = MyApplication.getInstance().prefManager.getString("section1");
                gson = new Gson();
                List<String> items = new ArrayList<>();
                section1 = (Section1) gson.fromJson(json, Section1.class);
                //showMessage(MyApplication.getInstance().prefManager.getString("section1"));
               // showMessage("setting data");
                //showMessage(section1.getSubject());
                if(section1.getSubject()!=null) {
                   // showMessage("subject is there");
                    items = Arrays.asList(section1.getSubject().split("\\s*,\\s*"));
                    subjectSpinner.setSelection(items);
                }
                if(section1.getGameCategory()!=null) {
                    items = Arrays.asList(section1.getGameCategory().split("\\s*,\\s*"));
                    gameSpinner.setSelection(items);
                }
                if(section1.getPastTime()!=null) {
                    items = Arrays.asList(section1.getPastTime().split("\\s*,\\s*"));
                    pastTimeSpinner.setSelection(items);
                }
                if(section1.getInterest()!=null) {
                    items = Arrays.asList(section1.getInterest().split("\\s*,\\s*"));
                    categorySpinner.setSelection(items);
                }
                break;
            case 3: //academics screen
                json = MyApplication.getInstance().prefManager.getString("section1");
                gson = new Gson();
                section1 = (Section1) gson.fromJson(json, Section1.class);
                if(section1.getYearOfPassing10()!=null)
                yearof10.setText(section1.getYearOfPassing10()+"");
                if(section1.getYearOfPassing12()!=null)
                yearof12.setText(section1.getYearOfPassing12()+"");
                if(section1.getMarks10()!=null)
                marks10.setText(section1.getMarks10()+"");
                if(section1.getMarks12()!=null)
                marks12.setText(section1.getMarks12()+"");
                ArrayAdapter myAdap = (ArrayAdapter) spinner.getAdapter(); //cast to an ArrayAdapter
                int spinnerPosition = myAdap.getPosition(section1.getMarks10Type());
                spinner.setSelection(spinnerPosition);

                myAdap = (ArrayAdapter)subjectSpinnerr.getAdapter();
                spinnerPosition = myAdap.getPosition(section1.getMarks12Type());
               // showMessage(section1.getMarks12Type());
               // showMessage(spinnerPosition+"");
                subjectSpinnerr.setSelection(spinnerPosition);

                break;
            case 4://higher education
                json = MyApplication.getInstance().prefManager.getString("section1");
                gson = new Gson();
                section1 = (Section1)gson.fromJson(json,Section1.class);
                if(section1.getHigherEducationDetails()!=null){
                    List<HigherEducationDetail> higherEducationDetails = section1.getHigherEducationDetails();

                    if(higherEducationDetails.size()==1){
                        HigherEducationDetail higherEducationDetail = higherEducationDetails.get(0);
                        //showMessage(higherEducationDetail.getCourse().toString());
                        course.setText(higherEducationDetail.getCourse()+"");
                        college.setText(higherEducationDetail.getInstitute()+"");
                        if(higherEducationDetail.getYear()!=null)
                        year.setText(higherEducationDetail.getYear()+"");
                        if(higherEducationDetail.getMarks()!=null)
                        marks.setText(higherEducationDetail.getMarks()+"");
                        myAdap = (ArrayAdapter) marksSpinner.getAdapter(); //cast to an ArrayAdapter
                        spinnerPosition = myAdap.getPosition(higherEducationDetail.getMarksType());
                        marksSpinner.setSelection(spinnerPosition);
                    }
                    else{
                       // showMessage(higherEducationDetails.size()+"");
                        HigherEducationDetail higherEducationDetail = higherEducationDetails.get(0);
                       // showMessage(higherEducationDetail.getCourse().toString());
                        int count=0;
                        course.setText(higherEducationDetail.getCourse()+"");
                        college.setText(higherEducationDetail.getInstitute()+"");
                        if(higherEducationDetail.getYear()!=null)
                            year.setText(higherEducationDetail.getYear()+"");
                        if(higherEducationDetail.getMarks()!=null)
                            marks.setText(higherEducationDetail.getMarks()+"");
                        myAdap = (ArrayAdapter) marksSpinner.getAdapter(); //cast to an ArrayAdapter
                        spinnerPosition = myAdap.getPosition(higherEducationDetail.getMarksType());
                        marksSpinner.setSelection(spinnerPosition);
                        for(HigherEducationDetail higherEducation:higherEducationDetails){
                            count++;
                           if(count>1){
                                LayoutInflater inflater = LayoutInflater.from(AnalysisBioDataSection.this);
                                View inflatedLayout = inflater.inflate(R.layout.higher_edu_layout, null, false);
                                higherEducationViews.add(inflatedLayout);
                                addViewHereLayout.addView(inflatedLayout);
                                Spinner marksSpinner = inflatedLayout.findViewById(R.id.marksSpinner);
                                String[] state = {"Percentage", "Marks", "CGPA", "SGPA"};

                                ArrayAdapter<String> siblings_array = new ArrayAdapter<>(AnalysisBioDataSection.this, android.R.layout.simple_spinner_item, state);
                                siblings_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                marksSpinner.setAdapter(siblings_array);
                                EditText college = inflatedLayout.findViewById(R.id.college1);
                                EditText course = inflatedLayout.findViewById(R.id.course1);
                                EditText year = inflatedLayout.findViewById(R.id.passyear1);
                                EditText marks = inflatedLayout.findViewById(R.id.marks1);

                                college.setText(higherEducation.getInstitute());
                                course.setText(higherEducation.getCourse());
                                year.setText(higherEducation.getYear()+"");
                                marks.setText(higherEducation.getMarks()+"");
                                myAdap = (ArrayAdapter) marksSpinner.getAdapter(); //cast to an ArrayAdapter
                                spinnerPosition = myAdap.getPosition(higherEducation.getMarksType());
                                marksSpinner.setSelection(spinnerPosition);

                            }


                        }
                    }
                }
                break;
            case 5: // professional certification
                json = MyApplication.getInstance().prefManager.getString("section1");
                gson = new Gson();
                section1 = (Section1)gson.fromJson(json,Section1.class);
                if(section1.getProfessionalDetails()!=null){
                    List<HigherEducationDetail> professionalDetailList = section1.getProfessionalDetails();

                    if(professionalDetailList.size()==1){
                        HigherEducationDetail professionalDetail = professionalDetailList.get(0);
                       // showMessage(professionalDetail.getCourse().toString()+"only 1");
                        course.setText(professionalDetail.getCourse()+"");
                        college.setText(professionalDetail.getInstitute()+"");
                        if(professionalDetail.getYear()!=null)
                        year.setText(professionalDetail.getYear()+"");
                        if(professionalDetail.getMarks()!=null)
                        marks.setText(professionalDetail.getMarks()+"");
                        myAdap = (ArrayAdapter) marksSpinner.getAdapter(); //cast to an ArrayAdapter
                        spinnerPosition = myAdap.getPosition(professionalDetail.getMarksType());
                        marksSpinner.setSelection(spinnerPosition);
                    }
                    else{
                       // showMessage(professionalDetailList.size()+"real size of retrieved");
                        HigherEducationDetail professionalDetail = professionalDetailList.get(0);
                        //showMessage(professionalDetail.getCourse().toString());
                        int count=0;
                        course.setText(professionalDetail.getCourse()+"");
                        college.setText(professionalDetail.getInstitute()+"");
                        if(professionalDetail.getYear()!=null)
                            year.setText(professionalDetail.getYear()+"");
                        if(professionalDetail.getMarks()!=null)
                            marks.setText(professionalDetail.getMarks()+"");
                        myAdap = (ArrayAdapter) marksSpinner.getAdapter(); //cast to an ArrayAdapter
                        spinnerPosition = myAdap.getPosition(professionalDetail.getMarksType());
                        marksSpinner.setSelection(spinnerPosition);
                        for(HigherEducationDetail professional:professionalDetailList){

                            count++;
                            if(count>1){
                               // showMessage("adding layout");
                                LayoutInflater inflater = LayoutInflater.from(AnalysisBioDataSection.this);
                                View inflatedLayout= inflater.inflate(R.layout.professional_certification_secondary_layout, null, false);
                                addViewHereLayout.addView(inflatedLayout);
                                professionalViews.add(inflatedLayout);
                                dateEditText = inflatedLayout.findViewById(R.id.dob);


                                Spinner marksSpinner = inflatedLayout.findViewById(R.id.marksSpinner);
                                String[] state = {"Percentage","Marks","CGPA","SGPA"};

                                ArrayAdapter<String> siblings_array = new ArrayAdapter<>(AnalysisBioDataSection.this, android.R.layout.simple_spinner_item, state);
                                siblings_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                marksSpinner.setAdapter(siblings_array);

                                EditText college = inflatedLayout.findViewById(R.id.college1);
                                EditText course = inflatedLayout.findViewById(R.id.course1);
                                EditText year = inflatedLayout.findViewById(R.id.passyear1);
                                EditText marks = inflatedLayout.findViewById(R.id.marks1);

                                college.setText(professional.getInstitute());
                                course.setText(professional.getCourse());
                                if(professional.getYear()!=null)
                                    year.setText(professional.getYear()+"");
                                if(professional.getMarks()!=null)
                                    marks.setText(professional.getMarks()+"");
                                myAdap = (ArrayAdapter) marksSpinner.getAdapter(); //cast to an ArrayAdapter
                                spinnerPosition = myAdap.getPosition(professional.getMarksType());
                                marksSpinner.setSelection(spinnerPosition);
                            }
                            else{
                               // showMessage("coutn con");
                            }


                        }
                    }
                }
                break;
            case 6:
                json = MyApplication.getInstance().prefManager.getString("section1");
                gson = new Gson();
                section1 = (Section1)gson.fromJson(json,Section1.class);
                if(section1.getExperienceDetails()!=null){
                    List<ExperienceDetail> experienceDetails = section1.getExperienceDetails();

                    if(experienceDetails.size()==1){
                        ExperienceDetail experienceDetail = experienceDetails.get(0);
                        // showMessage(professionalDetail.getCourse().toString()+"only 1");
                        name.setText(experienceDetail.getName()+"");
                        designation.setText(experienceDetail.getDesignation()+"");
                        dateEditText.setText(getNormalDate(experienceDetail.getStartDate()+""));
                        toEditText.setText(getNormalDate(experienceDetail.getEndDate()+""));
                    }
                    else{
                        // showMessage(professionalDetailList.size()+"real size of retrieved");
                        ExperienceDetail experienceDetail = experienceDetails.get(0);
                        //showMessage(professionalDetail.getCourse().toString());
                        int count=0;
                        name.setText(experienceDetail.getName()+"");
                        designation.setText(experienceDetail.getDesignation()+"");
                        dateEditText.setText(getNormalDate(experienceDetail.getStartDate()+""));
                        toEditText.setText(getNormalDate(experienceDetail.getEndDate()+""));
                        for(ExperienceDetail experience:experienceDetails){

                            count++;
                            if(count>1){
                                // showMessage("adding layout");
                                LayoutInflater inflater = LayoutInflater.from(AnalysisBioDataSection.this);
                                View inflatedLayout= inflater.inflate(R.layout.experience_secondary_layout, null, false);
                                addViewHereLayout.addView(inflatedLayout);
                                experienceViews.add(inflatedLayout);


                                EditText name = inflatedLayout.findViewById(R.id.college1);
                                EditText designation = inflatedLayout.findViewById(R.id.course1);
                                EditText toEditText = inflatedLayout.findViewById(R.id.to_edit_text);
                                fromEditText = inflatedLayout.findViewById(R.id.date);

                                name.setText(experience.getName());
                                designation.setText(experience.getDesignation());
                                fromEditText.setText(getNormalDate(experience.getStartDate()+""));
                                toEditText.setText(getNormalDate(experience.getEndDate()+""));
                            }
                            else{
                                // showMessage("coutn con");
                            }


                        }
                    }
                }
                break;


        }




    }
        @Override
    public void showPreferencesScreen() {
        setContentView(R.layout.favorite_preferences_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        exitSection();
        setProgressBarData(28);

        categorySpinner= findViewById(R.id.spinner1);
        subjectSpinner = findViewById(R.id.spinner);
        gameSpinner = findViewById(R.id.spinner2);
        pastTimeSpinner = findViewById(R.id.spinner3);



        gameSpinner.setOnItemSelectedListener(this);
        pastTimeSpinner.setOnItemSelectedListener(this);

        List<String> streamList = new ArrayList<>();
        streamList.add("0");
        streamList.add("1");
        streamList.add(">1");

        String[] categoryItems={"Arts","Business","Architecture","Data Science","Designing","Engineering","IT and Computer Science","Language","Finance","Law","Lifestyle","Medical","Personality","Maths","Marketing","Test Preparation"};
        categorySpinner.setItems(categoryItems);
        categorySpinner.hasNoneOption(true);
        categorySpinner.setListener(this);

        String[] subject={"Maths","Science","Social Studies","English","Art and Humanities","Physical Education","Economics","Computer Science","Other"};
        subjectSpinner.setItems(subject);
        subjectSpinner.hasNoneOption(true);
        subjectSpinner.setListener(this);


        String[] game = {"Action", "Strategy","Puzzle","English","Adventure","Role-Playing","Educational","Racing", "Board Games","Creativity", "Other"};
        gameSpinner.setItems(game);
        gameSpinner.hasNoneOption(true);
        gameSpinner.setListener(this);

        String[] pastTime = {"Games","Music and Dance","Art and Craft","Reading","Shopping","Partying","Travelling","Watching TV","Other"};
        pastTimeSpinner.setItems(pastTime);
        pastTimeSpinner.hasNoneOption(true);
        pastTimeSpinner.setListener(this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gson = new Gson();
                if(MyApplication.getInstance().prefManager.getString("section1")!=null) {
                   // showMessage("already saved data found");
                    json = MyApplication.getInstance().prefManager.getString("section1");
                    section1 = gson.fromJson(json, Section1.class);
                  //  showMessage(MyApplication.getInstance().prefManager.getString("section1"));
                }

                section1.setInterest(categorySpinner.getSelectedItemsAsString()+"");
                section1.setSubject(subjectSpinner.getSelectedItemsAsString()+"");
                section1.setPastTime(pastTimeSpinner.getSelectedItemsAsString()+"");
                section1.setGameCategory(gameSpinner.getSelectedItemsAsString()+"");
                saveSection1DataToPreferences(section1,2);
//                if(!section1.isScreenValidated(2)){
//                   // showMessage("All Fields are Required!");
//                }
//                else {
//
//                }
                presenter.next();
            }
        });
            if(MyApplication.getInstance().prefManager.getString("section1")!=null){
                //showMessage("section1 not null");
                if(MyApplication.getInstance().prefManager.getString("screen2")!=null) {
                    //showMessage("screen 2 data is there");
                    setDataBack(2);
                    //showMessage("settting data back");
                }
            }


    }
    @Override
    public void showAcademicsScreen() {
        setContentView(R.layout.academics_layout);

        spinner = findViewById(R.id.spinner0);
        marks10 = findViewById(R.id.marks);
        yearof10 = findViewById(R.id.pass_year10);
        marks12 = findViewById(R.id.marks12);
        subjectSpinnerr = findViewById(R.id.spinner1);
        yearof12 = findViewById(R.id.pass_year);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gson = new Gson();
                if(MyApplication.getInstance().prefManager.getString("section1")!=null) {
                    json = MyApplication.getInstance().prefManager.getString("section1");
                    section1 = gson.fromJson(json, Section1.class);
                }
                if(!marks10.getText().toString().equals("")&&marks10.getText()!=null)
                    section1.setMarks10(Float.parseFloat(marks10.getText().toString()));
                if(!marks12.getText().toString().equals("")&&marks12.getText()!=null)
                    section1.setMarks12(Float.parseFloat(marks12.getText().toString()));
                if(!yearof10.getText().toString().equals("")&&yearof10.getText()!=null)
                    section1.setYearOfPassing10(Integer.parseInt(yearof10.getText().toString()));
                if(!yearof12.getText().toString().equals("")&& yearof12.getText()!=null)
                    section1.setYearOfPassing12(Integer.parseInt(yearof12.getText().toString()+""));

                section1.setMarks10Type(spinner.getSelectedItem().toString());
                section1.setMarks12Type(subjectSpinnerr.getSelectedItem().toString());

//                section1.setYearOfPassing12(Integer.parseInt(yearof12.getText().toString()+""));
//                section1.setYearOfPassing10(Integer.parseInt(yearof10.getText().toString()+""));
//                section1.setYearOfPassing12(Integer.parseInt(yearof12.getText().toString()+""));
//                section1.setMarks12(Float.parseFloat(marks12.getText().toString()+""));
//                section1.setMarks10(Float.parseFloat(marks10.getText().toString()+""));
                saveSection1DataToPreferences(section1,3);
//                if(section1.isScreenValidated(3)){
//
//
//                }
//                else{
//                   // showMessage("All Fields are Required!");
//                }
                presenter.next();

            }
        });
        spinner.setOnItemSelectedListener(this);

        String[] state = {"Percentage","Marks","CGPA","SGPA"};

        marks10typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, state);
        marks10typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(marks10typeAdapter);

        marks12typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, state);
        marks12typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        subjectSpinnerr.setAdapter(marks12typeAdapter);
        if(MyApplication.getInstance().prefManager.getString("section1")!=null)
            if(MyApplication.getInstance().prefManager.getString("screen3")!=null)
            setDataBack(3);
        setProgressBarData(42);
        exitSection();

        //String exam12 = subjectSpinner.getSelectedItem().toString();

    }
    public void addViewToHigherEduScreen(){
        LayoutInflater inflater = LayoutInflater.from(AnalysisBioDataSection.this);
        View inflatedLayout= inflater.inflate(R.layout.higher_edu_layout, null, false);
        addViewHereLayout.addView(inflatedLayout);
        higherEducationViews.add(inflatedLayout);


        marksSpinner = inflatedLayout.findViewById(R.id.marksSpinner);
        String[] state = {"Percentage","Marks","CGPA","SGPA"};

        ArrayAdapter<String> siblings_array = new ArrayAdapter<>(AnalysisBioDataSection.this, android.R.layout.simple_spinner_item, state);
        siblings_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        marksSpinner.setAdapter(siblings_array);

    }

    @Override
    public void showHigherEducationScreen() {
        records=0;
        setContentView(R.layout.higher_education_screen);
        course = findViewById(R.id.course1);
        college = findViewById(R.id.college1);
        year = findViewById(R.id.passyear1);
        marks = findViewById(R.id.marks1);

        final LinearLayout mainRelativeLayout = findViewById(R.id.mainRelativeLayout);
        higherEducationViews = new ArrayList<>();
        RelativeLayout addViews = findViewById(R.id.add);
        exitSection();
        marksSpinner = findViewById(R.id.marksSpinner);
        String[] state = {"Percentage","Marks","CGPA","SGPA"};
        ArrayAdapter<String> siblings_array = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, state);
        siblings_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        marksSpinner.setAdapter(siblings_array);
        addViewHereLayout = findViewById(R.id.addViewsHere);
        addViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//            if(course.getText()!=null&&!course.getText().toString().equals("")&&college.getText()!=null&&!college.getText().toString().equals("")&&!year.getText().toString().equals("")&&year.getText()!=null&&marks.getText()!=null&&!marks.getText().toString().equals("")) {
//                LayoutInflater inflater = LayoutInflater.from(AnalysisBioDataSection.this);
//                View inflatedLayout = inflater.inflate(R.layout.higher_edu_layout, null, false);
//                addViewHereLayout.addView(inflatedLayout);
//                higherEducationViews.add(inflatedLayout);
//                marksSpinner = inflatedLayout.findViewById(R.id.marksSpinner);
//                String[] state = {"Percentage", "Marks", "CGPA", "SGPA"};
//                ArrayAdapter<String> siblings_array = new ArrayAdapter<>(AnalysisBioDataSection.this, android.R.layout.simple_spinner_item, state);
//                siblings_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                marksSpinner.setAdapter(siblings_array);
//                //addViewToHigherEduScreen();
//            }
//            else{
//                showMessage("Please complete above record first!!");
//            }
                if(records==0) {
                    if(course.getText()!=null&&!course.getText().toString().equals("")&&college.getText()!=null&&!college.getText().toString().equals("")&&!year.getText().toString().equals("")&&year.getText()!=null&&marks.getText()!=null&&!marks.getText().toString().equals("")) {
                        records++;
                        inflater = LayoutInflater.from(AnalysisBioDataSection.this);
                        View inflatedLayout = inflater.inflate(R.layout.higher_edu_layout, null, false);
                        addViewHereLayout.addView(inflatedLayout);
                        higherEducationViews.add(inflatedLayout);
                        marksSpinner = inflatedLayout.findViewById(R.id.marksSpinner);
                        String[] state = {"Percentage", "Marks", "CGPA", "SGPA"};
                        ArrayAdapter<String> siblings_array = new ArrayAdapter<>(AnalysisBioDataSection.this, android.R.layout.simple_spinner_item, state);
                        siblings_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        marksSpinner.setAdapter(siblings_array);
                    } else {
                        showMessage("Please complete above record first!!");
                    }
                }
                else{
                    //do for inflated layouts

                    View myView = higherEducationViews.get(records-1);
                    EditText college = myView.findViewById(R.id.college1);
                    EditText course = myView.findViewById(R.id.course1);
                    EditText year = myView.findViewById(R.id.passyear1);
                    EditText marks = myView.findViewById(R.id.marks1);
                    if(course.getText()!=null&&!course.getText().toString().equals("")&&college.getText()!=null&&!college.getText().toString().equals("")&&!year.getText().toString().equals("")&&year.getText()!=null&&marks.getText()!=null&&!marks.getText().toString().equals("")) {
                        records++;
                        inflater = LayoutInflater.from(AnalysisBioDataSection.this);
                        View inflatedLayout = inflater.inflate(R.layout.higher_edu_layout, null, false);
                        addViewHereLayout.addView(inflatedLayout);
                        higherEducationViews.add(inflatedLayout);
                        marksSpinner = inflatedLayout.findViewById(R.id.marksSpinner);
                        String[] state = {"Percentage", "Marks", "CGPA", "SGPA"};
                        ArrayAdapter<String> siblings_array = new ArrayAdapter<>(AnalysisBioDataSection.this, android.R.layout.simple_spinner_item, state);
                        siblings_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        marksSpinner.setAdapter(siblings_array);
                    } else {
                        showMessage("Please complete above record first!!");
                    }
                }
            }
        });


        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
//        prevButton = (ImageView)findViewById(R.id.prev);
//        prevButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                presenter.previous();
//            }
//        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HigherEducationDetail higherEducationDetail1 = new HigherEducationDetail();
                if(college.getText() != null && course.getText() != null && year.getText() != null && marks.getText()!=null) {
                    if(!college.getText().equals(""))
                        higherEducationDetail1.setInstitute(college.getText().toString());
                    if(!course.getText().equals(""))
                        higherEducationDetail1.setCourse(course.getText().toString());
                    if(!marks.getText().toString().equals("")&&marks.getText()!=null&&!marks.getText().toString().equals("null"))
                        higherEducationDetail1.setMarks(Float.parseFloat(marks.getText()+""));
                    if(!year.getText().toString().equals("")&&year.getText()!=null&&!year.getText().toString().equals("null"))
                        higherEducationDetail1.setYear(Integer.parseInt(year.getText().toString()));
                    higherEducationDetail1.setMarksType(marksSpinner.getSelectedItem().toString());
                }
                higherEducationDetailList.add(higherEducationDetail1);
                for(View myView : higherEducationViews){
                    higherEduViewList =higherEduViewList+","+myView.getTag()+"";
                    EditText college = myView.findViewById(R.id.college1);
                    EditText course = myView.findViewById(R.id.course1);
                    EditText year = myView.findViewById(R.id.passyear1);
                    EditText marks = myView.findViewById(R.id.marks1);
                    Spinner marksSpinner = myView.findViewById(R.id.marksSpinner);
                    if(college.getText() != null && course.getText() != null && year.getText() != null && marks.getText()!=null) {
                       // showMessage(college.getText()+","+course.getText()+","+year.getText()+","+marks.getText());
                        HigherEducationDetail higherEducationDetail = new HigherEducationDetail();
                        if(!college.getText().equals(""))
                            higherEducationDetail.setInstitute(college.getText().toString());
                        if(!course.getText().equals(""))
                            higherEducationDetail.setCourse(course.getText().toString());
                        if(!marks.getText().toString().equals("")&&marks.getText()!=null&&!marks.getText().toString().equals("null"))
                            higherEducationDetail.setMarks(Float.parseFloat(marks.getText()+""));
                        if(!year.getText().toString().equals("")&&year.getText()!=null&&!year.getText().toString().equals("null"))
                            higherEducationDetail.setYear(Integer.parseInt(year.getText().toString()));
                        higherEducationDetail.setMarksType(marksSpinner.getSelectedItem().toString());
                        higherEducationDetailList.add(higherEducationDetail);
                    }
                }

                gson = new Gson();
                if(MyApplication.getInstance().prefManager.getString("section1")!=null) {
                    json = MyApplication.getInstance().prefManager.getString("section1");
                    section1 = gson.fromJson(json, Section1.class);
                }
                section1.setHigherEducationDetails(higherEducationDetailList);
               // showMessage(higherEducationDetailList.size()+")");
                saveSection1DataToPreferences(section1,4);


                presenter.next();
                higherEducationDetailList.clear();
            }
        });
        if(MyApplication.getInstance().prefManager.getString("section1")!=null)
            if(MyApplication.getInstance().prefManager.getString("screen4")!=null) {
                setDataBack(4);
                //showMessage("setting back");
            }

        setProgressBarData(56);
    }


    @Override
    public void showProfessionalCertficationScreen() {
        records = 0;
        setContentView(R.layout.professional_certification_layout);
        next = (Button)findViewById(R.id.next1);
        dateEditText = findViewById(R.id.dob);
        skip = (Button)findViewById(R.id.skip1);
        course = findViewById(R.id.course1);
        college = findViewById(R.id.college1);
        year = findViewById(R.id.passyear1);
        marks = findViewById(R.id.marks1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });

        professionalViews = new ArrayList<>();
        addViewHereLayout = findViewById(R.id.addViewsHere);
        RelativeLayout addViews = findViewById(R.id.add);

        marksSpinner = findViewById(R.id.marksSpinner);
        String[] state = {"Percentage","Marks","CGPA","SGPA"};

        ArrayAdapter<String> siblings_array = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, state);
        siblings_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        marksSpinner.setAdapter(siblings_array);

        addViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //LayoutInflater inflater = LayoutInflater.from(AnalysisBioDataSection.this);
                //View inflatedLayout= inflater.inflate(R.layout.professional_certification_secondary_layout, null, false);
               // addViewHereLayout.addView(inflatedLayout);
                //professionalViews.add(inflatedLayout);
                //dateEditText = inflatedLayout.findViewById(R.id.dob);


//                Spinner marksSpinner = inflatedLayout.findViewById(R.id.marksSpinner);
//                String[] state = {"Percentage","Marks","CGPA","SGPA"};
//
//                ArrayAdapter<String> siblings_array = new ArrayAdapter<>(AnalysisBioDataSection.this, android.R.layout.simple_spinner_item, state);
//                siblings_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                marksSpinner.setAdapter(siblings_array);

                if(records==0) {
                    if (course.getText() != null && !course.getText().toString().equals("") && college.getText() != null && !college.getText().toString().equals("") && !year.getText().toString().equals("") && year.getText() != null && marks.getText() != null && !marks.getText().toString().equals("")) {
                        records++;
                        inflater = LayoutInflater.from(AnalysisBioDataSection.this);
                        View inflatedLayout = inflater.inflate(R.layout.professional_certification_secondary_layout, null, false);
                        addViewHereLayout.addView(inflatedLayout);
                        professionalViews.add(inflatedLayout);
                        dateEditText = inflatedLayout.findViewById(R.id.dob);
                        Spinner marksSpinner = inflatedLayout.findViewById(R.id.marksSpinner);
                        String[] state = {"Percentage","Marks","CGPA","SGPA"};

                        ArrayAdapter<String> siblings_array = new ArrayAdapter<>(AnalysisBioDataSection.this, android.R.layout.simple_spinner_item, state);
                        siblings_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        marksSpinner.setAdapter(siblings_array);
                    } else {
                        showMessage("Please complete above record first!!");
                    }
                }
                else{
                    //do for inflated layouts

                    View myView = professionalViews.get(records-1);
                    EditText college = myView.findViewById(R.id.college1);
                    EditText course = myView.findViewById(R.id.course1);
                    EditText year = myView.findViewById(R.id.passyear1);
                    EditText marks = myView.findViewById(R.id.marks1);
                    if (course.getText() != null && !course.getText().toString().equals("") && college.getText() != null && !college.getText().toString().equals("") && !year.getText().toString().equals("") && year.getText() != null && marks.getText() != null && !marks.getText().toString().equals("")) {
                        records++;
                        inflater = LayoutInflater.from(AnalysisBioDataSection.this);
                        View inflatedLayout = inflater.inflate(R.layout.professional_certification_secondary_layout, null, false);
                        addViewHereLayout.addView(inflatedLayout);
                        professionalViews.add(inflatedLayout);
                        dateEditText = inflatedLayout.findViewById(R.id.dob);
                        Spinner marksSpinner = inflatedLayout.findViewById(R.id.marksSpinner);
                        String[] state = {"Percentage","Marks","CGPA","SGPA"};

                        ArrayAdapter<String> siblings_array = new ArrayAdapter<>(AnalysisBioDataSection.this, android.R.layout.simple_spinner_item, state);
                        siblings_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        marksSpinner.setAdapter(siblings_array);
                    } else {
                        showMessage("Please complete above record first!!");
                    }
                }




            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HigherEducationDetail higherEducationDetail1 = new HigherEducationDetail();
                if(college.getText() != null && course.getText() != null && year.getText() != null && marks.getText()!=null) {
                    if(!college.getText().equals(""))
                        higherEducationDetail1.setInstitute(college.getText().toString());
                    if(!course.getText().equals(""))
                        higherEducationDetail1.setCourse(course.getText().toString());
                    if(!marks.getText().equals("") &&!marks.getText().toString().equals("null")&& !TextUtils.isEmpty(marks.getText())) {
                        higherEducationDetail1.setMarks(Float.parseFloat(marks.getText() + ""));

                    }
                    if(!year.getText().equals("")&&!year.getText().toString().equals("null")&& !TextUtils.isEmpty(year.getText()))
                        higherEducationDetail1.setYear(Integer.parseInt(year.getText().toString()));
                    higherEducationDetail1.setMarksType(marksSpinner.getSelectedItem().toString());
                }

                professionalDetailsList.add(higherEducationDetail1);
                for(View myView : professionalViews){
                    Spinner marksSpinner = myView.findViewById(R.id.marksSpinner);
                    EditText college = myView.findViewById(R.id.college1);
                    EditText course = myView.findViewById(R.id.course1);
                    EditText year = myView.findViewById(R.id.passyear1);
                    EditText marks = myView.findViewById(R.id.marks1);
                    if(college.getText() != null && course.getText() != null && year.getText() != null && marks.getText()!=null) {
                       // showMessage(college.getText()+","+course.getText()+","+year.getText()+","+marks.getText());
                        HigherEducationDetail higherEducationDetail = new HigherEducationDetail();
                        if(!college.getText().equals(""))
                            higherEducationDetail.setInstitute(college.getText().toString());
                        if(!course.getText().equals(""))
                            higherEducationDetail.setCourse(course.getText().toString());
                        if(!marks.getText().equals("")&&!marks.getText().equals("null") && !TextUtils.isEmpty(marks.getText()))
                            higherEducationDetail.setMarks(Float.parseFloat(marks.getText()+""));
                        if(!year.getText().equals("")&&!year.getText().equals("null")&& !TextUtils.isEmpty(year.getText()))
                            higherEducationDetail.setYear(Integer.parseInt(year.getText().toString()));
                        higherEducationDetail.setMarksType(marksSpinner.getSelectedItem().toString());
                        professionalDetailsList.add(higherEducationDetail);
                    }
                }
                gson = new Gson();
                if(MyApplication.getInstance().prefManager.getString("section1")!=null) {
                    json = MyApplication.getInstance().prefManager.getString("section1");
                    section1 = gson.fromJson(json, Section1.class);
                }
                section1.setProfessionalDetails(professionalDetailsList);
                //showMessage(professionalDetailsList.size()+"size while saving");
                saveSection1DataToPreferences(section1,5);
                presenter.next();
            }
        });
        exitSection();


        if(MyApplication.getInstance().prefManager.getString("section1")!=null)
            if(MyApplication.getInstance().prefManager.getString("screen5")!=null) {
                setDataBack(5);
               // showMessage("setting back");
            }

        EditText fbLink,linkedInLink;
        fbLink = findViewById(R.id.fb_link);
        linkedInLink = findViewById(R.id.linkedin_link);
        professionalDetailsList.clear();

        setProgressBarData(70);
    }

    @Override
    public void showExperienceScreen() {
        records = 0;
        setContentView(R.layout.experience_layout);
        toEditText = findViewById(R.id.to_text);
        designation = findViewById(R.id.course1);
        name = findViewById(R.id.college1);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        dateEditText = (EditText)findViewById(R.id.dob);

        final ExperienceDetail experienceDetail = new ExperienceDetail();
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });

        exitSection();
        experienceViews = new ArrayList<>();
        addViewHereLayout = findViewById(R.id.addViewsHere);
        RelativeLayout addViews = findViewById(R.id.add);


        addViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                inflater = LayoutInflater.from(AnalysisBioDataSection.this);
//                View inflatedLayout = inflater.inflate(R.layout.experience_secondary_layout, null, false);
//                addViewHereLayout.addView(inflatedLayout);
//                experienceViews.add(inflatedLayout);
//                fromEditText = inflatedLayout.findViewById(R.id.date);
//                toEditText = inflatedLayout.findViewById(R.id.to_edit_text);
                if(records==0) {
                    if (name.getText() != null && !name.getText().toString().equals("") && toEditText.getText() != null && !toEditText.getText().toString().equals("") && !dateEditText.getText().toString().equals("") && dateEditText.getText() != null && designation.getText() != null && !designation.getText().toString().equals("")) {
                        records++;
                        inflater = LayoutInflater.from(AnalysisBioDataSection.this);
                        View inflatedLayout = inflater.inflate(R.layout.experience_secondary_layout, null, false);
                        addViewHereLayout.addView(inflatedLayout);
                        experienceViews.add(inflatedLayout);
                        fromEditText = inflatedLayout.findViewById(R.id.date);
                        toEditText = inflatedLayout.findViewById(R.id.to_edit_text);
                    } else {
                        showMessage("Please complete above record first!!");
                    }
                }
                else{
                    //do for inflated layouts

                    View myView = experienceViews.get(records-1);
                    EditText name = myView.findViewById(R.id.college1);
                    EditText designation = myView.findViewById(R.id.course1);
                    EditText startDate = myView.findViewById(R.id.date);
                    EditText endDate = myView.findViewById(R.id.to_edit_text);
                    if (name.getText() != null && !name.getText().toString().equals("") && startDate.getText() != null && !startDate.getText().toString().equals("") && !endDate.getText().toString().equals("") && endDate.getText() != null && designation.getText() != null && !designation.getText().toString().equals("")) {

                        records++;
                        inflater = LayoutInflater.from(AnalysisBioDataSection.this);
                        View inflatedLayout = inflater.inflate(R.layout.experience_secondary_layout, null, false);
                        addViewHereLayout.addView(inflatedLayout);
                        experienceViews.add(inflatedLayout);
                        fromEditText = inflatedLayout.findViewById(R.id.date);
                        toEditText = inflatedLayout.findViewById(R.id.to_edit_text);
                    } else {
                        showMessage("Please complete above record first!!");
                    }
                }



            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                experienceDetail.setName(name.getText().toString());
                experienceDetail.setDesignation(designation.getText().toString());
                experienceDetail.setStartDate(changeDateFormat(dateEditText.getText().toString()));
                experienceDetail.setEndDate(changeDateFormat(toEditText.getText().toString()));
                experienceDetailList.add(experienceDetail);

                for(View myView : experienceViews){
                    EditText name = myView.findViewById(R.id.college1);
                    EditText designation = myView.findViewById(R.id.course1);
                    EditText startDate = myView.findViewById(R.id.date);
                    EditText endDate = myView.findViewById(R.id.to_edit_text);
                    if(name.getText() != null && course.getText() != null && year.getText() != null && marks.getText()!=null) {
                        // showMessage(college.getText()+","+course.getText()+","+year.getText()+","+marks.getText());
                        ExperienceDetail higherEducationDetail = new ExperienceDetail();
                        if(!name.getText().equals(""))
                            higherEducationDetail.setName(name.getText().toString());
                        if(!designation.getText().equals(""))
                            higherEducationDetail.setDesignation(designation.getText().toString());
                        if(!endDate.getText().equals("") && !TextUtils.isEmpty(endDate.getText()))
                            higherEducationDetail.setStartDate(changeDateFormat(endDate.getText()+""));
                        if(!startDate.getText().equals("")&& !TextUtils.isEmpty(startDate.getText()))
                            higherEducationDetail.setEndDate(changeDateFormat(startDate.getText().toString()));
                        experienceDetailList.add(higherEducationDetail);
                    }
                }
                if(MyApplication.getInstance().prefManager.getString("section1")!=null){
                    json = MyApplication.getInstance().prefManager.getString("section1");
                    section1 = gson.fromJson(json, Section1.class);
                }
                section1.setExperienceDetails(experienceDetailList);
               // showMessage("while saving"+experienceDetailList.size()+"");
                saveSection1DataToPreferences(section1,6);
                presenter.next();
                String finalPost = gson.toJson(section1,Section1.class);
                Log.d("finalPost",finalPost);

                section1.submit(listener);
            }
        });
        if(MyApplication.getInstance().prefManager.getString("section1")!=null)
            if(MyApplication.getInstance().prefManager.getString("screen6")!=null) {
                setDataBack(6);
                // showMessage("setting back");
            }


        setProgressBarData(84);
        experienceDetailList.clear();
    }

    @Override
    public void showExamScoreScreen() {
        setContentView(R.layout.exam_score_layout);
        dateEditText = findViewById(R.id.dob);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // presenter.next();
                presenter.next();
            }
        });
        exitSection();
        examScoreViews = new ArrayList<>();
        final LinearLayout addViewHereLayout = findViewById(R.id.addViewsHere);
        RelativeLayout addViews = findViewById(R.id.add);
        addViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(AnalysisBioDataSection.this);
                View inflatedLayout= inflater.inflate(R.layout.exam_score_secondary_layout, null, false);
                addViewHereLayout.addView(inflatedLayout);
                examScoreViews.add(inflatedLayout);

                dateEditText = inflatedLayout.findViewById(R.id.dob);



            }
        });




        setProgressBarData(100);
    }
    @Override
    public void showHistoryAndGoalsSectionEndScreen(){
        setContentView(R.layout.analysis_section_end_screen);
        TextView sectionName;
        TextView sectionDuration;
        ImageView exitSection;
        final ImageView continueSection;
        sectionDuration = findViewById(R.id.duration);
        sectionName = findViewById(R.id.section_name);
        sectionImage = findViewById(R.id.section_image);
        exitSection = findViewById(R.id.exit);
        continueSection = findViewById(R.id.continue_next);

        sectionDuration.setText("40 sec");
        sectionName.setText("Me or Not Me");
        Picasso.with(this).load(R.drawable.ic_meornotme).into(sectionImage);
        MyApplication.getInstance().prefManager.saveString(HISTORYNGOALSSECTIONCOMPLETED,"true");
        continueSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AnalysisBioDataSection.this,MeOrNotMeSection.class);
                intent.putExtra("questionsSection2",meornotmeQuestions);
                intent.putExtra("questionsSection3",abilityQuestions);
                intent.putExtra("questionsSection4",interestQuestions);
                intent.putExtra("questionsSection5",egogramQuestions);
                intent.putExtra("questions",motivationalQuestions);
                startActivity(intent);
                finish();
//                startActivity(new Intent(AnalysisBioDataSection.this, MainActivity.class));
//                finish();
            }
        });
        exitSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AnalysisBioDataSection.this, AnalysisStatusActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(presenter.sequence==0){
            startActivity(new Intent(AnalysisBioDataSection.this,AnalysisStatusActivity.class));
        }
        if(presenter.sequence==6){
            ExperienceDetail experienceDetail = new ExperienceDetail();
            experienceDetail.setName(name.getText().toString());
            experienceDetail.setDesignation(designation.getText().toString());
            experienceDetail.setStartDate(changeDateFormat(dateEditText.getText().toString()));
            experienceDetail.setEndDate(changeDateFormat(toEditText.getText().toString()));
            experienceDetailList.add(experienceDetail);

            for(View myView : experienceViews){
                EditText name = myView.findViewById(R.id.college1);
                EditText designation = myView.findViewById(R.id.course1);
                EditText startDate = myView.findViewById(R.id.date);
                EditText endDate = myView.findViewById(R.id.to_edit_text);
                if(name.getText() != null && course.getText() != null && year.getText() != null && marks.getText()!=null) {
                    // showMessage(college.getText()+","+course.getText()+","+year.getText()+","+marks.getText());
                    ExperienceDetail higherEducationDetail = new ExperienceDetail();
                    if(!name.getText().equals(""))
                        higherEducationDetail.setName(name.getText().toString());
                    if(!designation.getText().equals(""))
                        higherEducationDetail.setDesignation(designation.getText().toString());
                    if(!endDate.getText().equals("") && !TextUtils.isEmpty(endDate.getText()))
                        higherEducationDetail.setStartDate(changeDateFormat(endDate.getText()+""));
                    if(!startDate.getText().equals("")&& !TextUtils.isEmpty(startDate.getText()))
                        higherEducationDetail.setEndDate(changeDateFormat(startDate.getText().toString()));
                    experienceDetailList.add(higherEducationDetail);
                }
            }
            if(MyApplication.getInstance().prefManager.getString("section1")!=null){
                json = MyApplication.getInstance().prefManager.getString("section1");
                section1 = gson.fromJson(json, Section1.class);
            }
            section1.setExperienceDetails(experienceDetailList);
            // showMessage("while saving"+experienceDetailList.size()+"");
            saveSection1DataToPreferences(section1,6);
        }
        presenter.previous();
        }
    private DatePickerDialog.OnDateSetListener toDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3){
            arg0.setMaxDate(System.currentTimeMillis()-1000);
            showToDate(arg1,arg2+1,arg3);
        }
    };
    private DatePickerDialog.OnDateSetListener secDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3){
            arg0.setMaxDate(System.currentTimeMillis()-1000);
            showFromDate(arg1,arg2+1,arg3);
        }
    };
    public void showFromDate(int year,int month, int day){
        fromEditText.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    private void showToDate(int year, int month, int day) {
        toEditText.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    arg0.setMaxDate(System.currentTimeMillis()-1000);
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateEditText.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {

        showDialog(1);
    }

    @SuppressWarnings("deprecation")
    public void setToDate(View view) {
        showDialog(0);
    }
    @SuppressWarnings("deprecation")
    public void setFromDate(View view) {

        showDialog(3);
    }



    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub

        Log.d("Calenderid",id+"");
        if (id == DATE_PICKER_FROM) {
            DatePickerDialog dialog = new DatePickerDialog(this,
                    myDateListener, 2018, 07, 29);
            dialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
            return dialog;
        }
        else if(id == DATE_PICKER_TO){
            DatePickerDialog dialog =  new DatePickerDialog(this,
                    toDateListener, 2018, 07, 29);
            dialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
            return dialog;
        }
        else if(id==3){
            DatePickerDialog dialog =  new DatePickerDialog(this,
                    secDateListener, 2018, 07, 29);
            dialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
            return dialog;
        }
        return null;
    }

}
