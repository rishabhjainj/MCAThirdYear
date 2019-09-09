package com.AbhiDev.edurecomm.Recommendation.AnalysisViewPager;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import com.github.clans.fab.FloatingActionButton;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.gson.Gson;
import com.wireout.Activities.AllCoursesActivity;
import com.wireout.Activities.Analysis.AnalysisStatusActivity;
import com.wireout.Activities.Analysis.CommSkillSection;
import com.wireout.Activities.Analysis.LogicalReasoningSection;
import com.wireout.Activities.AptitudeActivity;
import com.wireout.Activities.BackPressed;
import com.wireout.Activities.CareerActivity;
import com.wireout.Activities.Recommendations;
import com.wireout.Recommendation.AnalysisViewPager.presenters.TabFragment1Presenter;
import com.wireout.ViewMore.TrendingUniversitiesActivity;
import com.wireout.listeners.OnEntityReceivedListener;
import com.wireout.models.Career;
import com.wireout.models.CareerList;
import com.wireout.models.CourseList;
import com.wireout.models.Institution;
import com.wireout.models.ShortTermCourse;
import com.wireout.models.University;
import com.wireout.models.career_analysis.Report;
import com.wireout.models.career_analysis.Section1;
import com.wireout.R;
import com.wireout.Recommendation.TabFragmentViewAction;
import com.wireout.Recommendation.Top2;
import com.wireout.adapters.CareerAdapter;
import com.wireout.adapters.InstitutesAdapter;
import com.wireout.adapters.RecommendedCourseAdapter;
import com.wireout.adapters.RecommendedUniAdapter;
import com.wireout.adapters.ShortTermAdapter;
import com.wireout.adapters.SubjectsAdapter;
import com.wireout.adapters.TopCoursesItemAdapter;
import com.wireout.common.MyApplication;
import com.wireout.common.PrefManager;
import com.wireout.listeners.OnEntitiesReceivedListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Paras on 7/19/2017.
 */

public class TabFragment1 extends Fragment implements BackPressed.OnBackPressedListener, TabFragmentViewAction{

    PieChart mChart;
    TextView commCourse;
    RelativeLayout reccCourse1,reccCourse2;
    TextView logicCourse;
    TextView registerLogical,registerComm;
    HorizontalBarChart horizontalBarChart;
    OnEntityReceivedListener<Report> reportListener;
    RadarChart radarChart;
    android.support.design.widget.FloatingActionButton floatingActionButton;
    SubjectsAdapter subjectsAdapter;
    DisplayMetrics displayMetrics;
    PrefManager prefManager;

    int logicScore=0;
    int commScore=0;

    BarChart chart;

    PieChart pieChart;
    RoundCornerProgressBar Intelligence;
    RoundCornerProgressBar Emotional;
    RoundCornerProgressBar Social;
    RoundCornerProgressBar Verbal;
    RoundCornerProgressBar Spatial;
    RoundCornerProgressBar Communication;
    RoundCornerProgressBar Logical;
    RoundCornerProgressBar Creativity;
    ImageView Image;
    Bitmap bitmap;
    TextView IntelligenceScore;
    TextView EmotionalScore;
    TextView viewAllCareer,viewAllUniv,viewAllCourses;
    TextView SocialScore;

    TextView VerbalScore;
    TextView CreativityScore;
    TextView SpatialScore;
    TextView CommScore;
    TextView LogicalScore;
    PieChart mChart2;
    TextView Childname;
    TextView careerText,courseText,institutionText;
    TextView childName2;
    protected Typeface mTfRegular;
    protected Typeface mTfLight;
    ImageView i1,i2,i3,i4,i5,i6;
    CardView cardx, careerReportButton;
    Context mContext;
    RecyclerView coursesRecyclerView, shortTermCoursesRecyclerView;
    ShortTermAdapter shortTermAdapter;
    RecommendedCourseAdapter recommendedCourseAdapter;

    OnEntitiesReceivedListener<CareerList> careerListener;
    InstitutesAdapter institutesAdapter;
    TopCoursesItemAdapter topCoursesItemAdapter;
    TabFragment1Presenter presenter;


    float creative=20,intelligence=20,emotional=10,social=10,verbal=10,spatial=20,total=10;

    protected String[] mParties = new String[] {
            "Left Brain","Right Brain"
    };

    protected String[] mParties2 = new String[] {
            "Intelligence","Emotion","Social","Verbal Ability","Spatial","Creativity"
    };

    View view;
    Button report;
    CardView feedback;
    Intent intent;
    TextView AnalysisReport;
    ImageView guide,reportImage,guide2;
    //int[] images=new int[3];
//    String[] age=new String[3];
//    String[] gamename=new String[3];

    RecyclerView recyclerView,careerOptionsRecyclerView,institutesRecyclerView;
    RecommendedUniAdapter adapter;
    CareerAdapter careerAdapter;
    ArrayList<String> subjects;

    OnEntitiesReceivedListener<CourseList> courseListener;


//    @Override
//    public int getLayoutResId() {
//        return R.layout.tab_fragment_3;
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.tab_fragment_3, container, false);
        reportListener= new OnEntityReceivedListener<Report>(getContext()) {
            @Override
            public void onReceived(Report entity) {

                setReportData(entity);
            }
        };
        commCourse = view.findViewById(R.id.courseNamee);
        logicCourse = view.findViewById(R.id.course_namee);
        reccCourse1 = view.findViewById(R.id.recommendedCourse1);
        reccCourse2 = view.findViewById(R.id.recommendedCourse2);
        registerComm = view.findViewById(R.id.registerButtonn);
        registerLogical = view.findViewById(R.id.registerButton);
        registerComm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+91 88262 68922"));
                startActivity(intent);
            }
        });
        registerLogical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+91 87955 50655"));
                startActivity(intent);
            }
        });
        if(MyApplication.getInstance().prefManager.getString(LogicalReasoningSection.LOGICALSCORE)!=null) {
            logicScore = Integer.parseInt(MyApplication.getInstance().prefManager.getString(LogicalReasoningSection.LOGICALSCORE));
            if(logicScore>=0&&logicScore<=5){
                logicCourse.setText("Numerical Aptitude- Basic ");
            }
            else if(logicScore>=6&&logicScore<=8){
                logicCourse.setText("IBPS/SSC");
            }
            else if( logicScore==9||logicScore==10){
                logicCourse.setText("CAT");
            }
        }
        else{
            reccCourse2.setVisibility(View.GONE);
        }
        if(MyApplication.getInstance().prefManager.getString(CommSkillSection.COMMSCORE)!=null){
            commScore = Integer.parseInt(MyApplication.getInstance().prefManager.getString(CommSkillSection.COMMSCORE));
            if(commScore>=0&&commScore<=5){
                commCourse.setText("IELTS - Basic Level");
            }
            else if(commScore>=6&&commScore<=8){
                commCourse.setText("IELTS - Advanced Level");
            }
            else if( commScore==9||commScore==10){
                commCourse.setText("GRE/GMAT");
            }
        }
        else{
            reccCourse1.setVisibility(View.GONE);
        }



        floatingActionButton = view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AptitudeActivity.class));
            }
        });
        radarChart = view.findViewById(R.id.radarChart);
        pieChart = view.findViewById(R.id.half_piechart);
        view.findViewById(R.id.egogram_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipp(3);
            }
        });
        horizontalBarChart  = view.findViewById(R.id.horizontalchart);

        viewAllCareer = view.findViewById(R.id.view_all_career);
        viewAllUniv = view.findViewById(R.id.view_all_inst);
        viewAllCourses = view.findViewById(R.id.view_all_courses);
        AnalysisReport = (TextView)view.findViewById(R.id.anylsisreport);
        Childname = (TextView)view.findViewById(R.id.childName);
        childName2 = (TextView)view.findViewById(R.id.name_child);
        careerText = view.findViewById(R.id.career_text);
        chart =  view.findViewById(R.id.bar_chart);
        courseText = view.findViewById(R.id.course_text);
        institutionText  = view.findViewById(R.id.institution_text);
        prefManager = new PrefManager(getActivity());

        Social = (RoundCornerProgressBar)view.findViewById(R.id.socials);
        Spatial =(RoundCornerProgressBar)view.findViewById(R.id.spatia);
        Creativity =(RoundCornerProgressBar)view.findViewById(R.id.creativity);
        IntelligenceScore = (TextView)view.findViewById(R.id.intelligenceScore);
        Verbal = (RoundCornerProgressBar)view.findViewById(R.id.verba);
        Logical = (RoundCornerProgressBar)view.findViewById(R.id.logica);
        Communication = (RoundCornerProgressBar)view.findViewById(R.id.comma);

        Emotional =(RoundCornerProgressBar)view.findViewById(R.id.emo);
        EmotionalScore =(TextView)view.findViewById(R.id.emotionalScore);
        CommScore = (TextView)view.findViewById(R.id.commScore);
        LogicalScore=(TextView)view.findViewById(R.id.logicalScore);
        SpatialScore =(TextView)view.findViewById(R.id.spatialScore);
        VerbalScore = (TextView)view.findViewById(R.id.verbalScore);
        SocialScore = (TextView)view.findViewById(R.id.socialScore);
        CreativityScore =(TextView)view.findViewById(R.id.creativeScore);
        Intelligence = (RoundCornerProgressBar)view.findViewById(R.id.intelli);
        coursesRecyclerView = view.findViewById(R.id.subjects_recyclerview);
        institutesRecyclerView = view.findViewById(R.id.institutions_recyclerview);
        careerOptionsRecyclerView = view.findViewById(R.id.career_recyclerview);
        careerReportButton = view.findViewById(R.id.careerReportButton);

        presenter = new TabFragment1Presenter(getContext(),this);
        feedback=view.findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Top2 top2 = (Top2) getParentFragment();
                if(top2 != null)
                    top2.viewPager.setCurrentItem(2, true);
            }
        });
        reportImage=(ImageView)view.findViewById(R.id.report_image);
        reportImage.setColorFilter(getResources().getColor(R.color.dark_purple));

        careerText.setText("My Career Options");
        courseText.setText("My courses");
        institutionText.setText("Institutions with Courses");
        careerListener = new OnEntitiesReceivedListener<CareerList>(getContext()) {
            @Override
            public void onReceived(List<CareerList> entities) {
                setCareerOptionsRecycler(entities);
            }
        };
       // setCareerOptionsRecycler(subjects);

        courseListener = new OnEntitiesReceivedListener<CourseList>(getContext()) {
            @Override
            public void onReceived(List<CourseList> entities) {
                setTopCourses(entities);
            }
        };
        presenter.getUniversities();
        mChart = (PieChart) view.findViewById(R.id.chart1);


        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);
        mChart.setDragDecelerationFrictionCoef(0.95f);
        mChart.setCenterTextTypeface(mTfLight);
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);
        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);
        mChart.setHoleRadius(48f);
        mChart.setTransparentCircleRadius(50f);
        mChart.setDrawCenterText(true);
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);


        i1=(ImageView)view.findViewById(R.id.imag1);
        i2=(ImageView)view.findViewById(R.id.imag2);
        i3=(ImageView)view.findViewById(R.id.imag3);
        i4=(ImageView)view.findViewById(R.id.imag4);
        i5=(ImageView)view.findViewById(R.id.imag5);
        i6=(ImageView)view.findViewById(R.id.imag6);

        i1.setColorFilter(getResources().getColor(R.color.fabColor));
        i2.setColorFilter(getResources().getColor(R.color.accent));
        i3.setColorFilter(getResources().getColor(R.color.green));
        i4.setColorFilter(getResources().getColor(R.color.colorAccent));
        i5.setColorFilter(getResources().getColor(R.color.red));
        i6.setColorFilter(getResources().getColor(R.color.gold));

        guide=(ImageView)view.findViewById(R.id.guide);
        guide.setColorFilter(getResources().getColor(R.color.black));
        guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                skipp(1);
            }
        });
        guide2=(ImageView)view.findViewById(R.id.guide2);
        guide2.setColorFilter(getResources().getColor(R.color.dark_purple));
        guide2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                skipp(2);
            }
        });

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = mChart.getLegend();
        l.setEnabled(false);
        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);
        mChart.setEntryLabelTypeface(mTfRegular);
        mChart.setEntryLabelTextSize(10f);

        mChart2 = (PieChart) view.findViewById(R.id.chart2);
        mChart2.setUsePercentValues(true);
        mChart2.getDescription().setEnabled(false);
        mChart2.setExtraOffsets(5, 10, 5, 5);
        mChart2.setDragDecelerationFrictionCoef(0.95f);
        mChart2.setCenterTextTypeface(mTfLight);
        mChart2.setDrawHoleEnabled(true);
        mChart2.setHoleColor(Color.WHITE);
        mChart2.setTransparentCircleColor(Color.WHITE);
        mChart2.setTransparentCircleAlpha(110);
        mChart2.setHoleRadius(45f);
        mChart2.setTransparentCircleRadius(47f);
        mChart2.setDrawCenterText(true);
        mChart2.setRotationAngle(0);
        mChart2.setRotationEnabled(true);
        mChart2.setHighlightPerTapEnabled(true);
        setData2(6, 100,mChart2,mParties2);
        mChart2.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l2 = mChart2.getLegend();
        l2.setEnabled(false);

        // entry label styling
        mChart2.setEntryLabelColor(Color.WHITE);
        mChart2.setEntryLabelTypeface(mTfRegular);
        mChart2.setEntryLabelTextSize(12f);

        displayMetrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

//        presenter = new TabFragment1Presenter(
//                new OnEntitiesReceivedListener<ShortTermCourse>(getContext()) {
//                    public void onReceived(List<ShortTermCourse> entities){
//                        setShortTermRecyclerView(entities);
//                    }
//                },
//                new OnEntitiesReceivedListener<Course>(getContext()) {
//                    public void onReceived(List<Course> entities) {
//                        setCoursesRecyclerView(entities);
//                    }
//                }, this);
//        presenter.loadCourses(new HashMap<String, String>());
//        presenter.loadShortTermCourses(new HashMap<String, String>());

        if(MyApplication.getInstance().prefManager.getString(AnalysisStatusActivity.ANALYSIS_COMPLETED)!=null){
            if(MyApplication.getInstance().prefManager.getString("report")!=null) {
                //showMessage("getting details");
                Gson gson = new Gson();
                String json = MyApplication.getInstance().prefManager.getString("report");
                final Report report = gson.fromJson(json,Report.class);
                setupRadarChart(report.getRealistic(),report.getInvestigative(),report.getArtistic(),report.getSocial(),report.getEnterprising(),report.getConventional());
                //showMessage("setting data");
                String myjson = MyApplication.getInstance().prefManager.getString("section1");
                gson = new Gson();
                Section1 section1 = gson.fromJson(myjson,Section1.class);
                if(section1!=null) {
                    Childname.setText(section1.getName());
                    childName2.setText(section1.getName());
                }

                viewAllUniv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =  new Intent(getContext(), TrendingUniversitiesActivity.class);
                        intent.putExtra("landing","no");
                        startActivity(intent);
                    }
                });
                viewAllCareer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), CareerActivity.class);
                        intent.putExtra("landing","ex");
                        intent.putExtra("LIST", (Serializable) report.getCareerOptions());
                        startActivity(intent);
                    }
                });
                viewAllCourses.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), AllCoursesActivity.class);
                        intent.putExtra("LIST", (Serializable) report.getCourses());
                        intent.putExtra("landing","exc");
                        startActivity(intent);
                    }
                });
                if(report.getCareerOptions().size()>10)
                setCareerOptionsRecycler(report.getCareerOptions().subList(0,10));
                else
                    setCareerOptionsRecycler(report.getCareerOptions());
                if(report.getCourses().size()>10)
                setTopCourses(report.getCourses().subList(0,10));
                else
                    setTopCourses(report.getCourses());
                Intelligence.setProgress(report.getIntelligence());
                Spatial.setProgress(report.getSpatial());
                Verbal.setProgress(report.getVerbal());
                Creativity.setProgress(report.getCreativity());
                Social.setProgress(report.getSocial2());
                Emotional.setProgress(report.getEmotional());
                Logical.setProgress(logicScore*40);
                Communication.setProgress(commScore*40);

                IntelligenceScore.setText(report.getIntelligence()+"");
                SpatialScore.setText(report.getSpatial()+"");
                VerbalScore.setText(report.getVerbal()+"");
                CreativityScore.setText(report.getCreativity()+"");
                SocialScore.setText(report.getSocial2()+"");
                EmotionalScore.setText(report.getEmotional()+"");
                LogicalScore.setText(logicScore*40+"");
                CommScore.setText(commScore*40+"");
                AnalysisReport.setText("Analysis Report");
                if(report.getEgogramSum() == 1) //can infer all false responses from egogram in this case : show default data
                    setHalfPieChartData();
                else {
                    Log.d("Report", "Setting egogram..." + report.getParentPercentage() + " " + report.getChildPercentage() + " " + report.getAdultPercentage());
                    setHalfPieChartData(report.getParentPercentage(), report.getChildPercentage(), report.getAdultPercentage());
                }
                setBarChartData(report.getAnalytical(),report.getInteractive(),report.getIntrospective());
               // showMessage("radardata"+","+report.getRealistic()+","+report.getInvestigative()+","+report.getArtistic()+","+report.getSocial()+","+report.getEnterprising()+","+report.getConventional());
                //setupRadarChart(report.getRealistic(),report.getInvestigative(),report.getArtistic(),report.getSocial(),report.getEnterprising(),report.getConventional());
                setData(2, 100,mChart,mParties,report.getLeftBrain(),report.getRightBrain());


            }
            //presenter.getAnalysisReport(reportListener);
        }

        else{
            //showMessage("details not found");
            viewAllUniv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =  new Intent(getContext(), TrendingUniversitiesActivity.class);
                    intent.putExtra("landing","no");
                    startActivity(intent);
                }
            });
            viewAllCareer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), CareerActivity.class);
                    intent.putExtra("landing","no");
                    startActivity(intent);
                }
            });
            viewAllCourses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), AllCoursesActivity.class);
                    intent.putExtra("landing","no");
                    startActivity(intent);
                }
            });
            //showMessage("setting static data");
            presenter.getCourses(courseListener);
            presenter.getCareer(careerListener);
            setHorizontalChartData(12,50);
            setHalfPieChartData();
            setBarChartData();
            setupRadarChart();
            setData(2, 100,mChart,mParties);
        }
        return view;
    }
//

    public class LabelFormatter implements IAxisValueFormatter {
        private final String[] mLabels;

        public LabelFormatter(String[] labels) {
            mLabels = labels;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mLabels[(int) value];
        }
    }
    public static TabFragment1 create() {

        return new TabFragment1();
    }
    public Bitmap getThumbnail(String filename) {

        Bitmap thumbnail = null;

// If no file on external storage, look in internal storage
        try {
            File filePath = getActivity().getFileStreamPath(filename);
            FileInputStream fi = new FileInputStream(filePath);
            thumbnail = BitmapFactory.decodeStream(fi);
            return thumbnail;
        } catch (Exception ex) {
            Log.e("getThumbnail() internal", ex.getMessage());
            return null;
        }


    }



    private void setHorizontalChartData(int count, int range){

        ArrayList<BarEntry> yVals = new ArrayList<>();
        float barwidth = 5f;
        float spaceForBar = 10f;
        yVals.add(new BarEntry(1*spaceForBar,90));
        yVals.add(new BarEntry(2*spaceForBar,85));
        yVals.add(new BarEntry(3*spaceForBar,100));

        BarDataSet set1;
        set1 = new BarDataSet(yVals,"data set 1");
        String[] labels = {"Analytical","Interactive","Instrospective","dd"};
        horizontalBarChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        set1.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data = new BarData(set1);
        horizontalBarChart.setFitBars(true);
        XAxis xAxis = horizontalBarChart.getXAxis();
        xAxis.setSpaceMin(data.getBarWidth() / 2f);
        xAxis.setSpaceMax(data.getBarWidth() / 2f);
        data.setBarWidth(barwidth);
        horizontalBarChart.setData(data);

    }

    @Override
    public void confirmVisible() {

    }

    private void setBarChartData(){
        ArrayList<BarEntry> BarEntry = new ArrayList<>();
        float barwidth = 20f;
        float spaceForBar = 15f;
        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(44f, 50); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(88f, 80); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(66f, 90); // Mar
        valueSet1.add(v1e3);


        String[] labels = {"Analytical","Interactive","Instrospective","dd"};
        //chart.getXAxis().setValueFormatter(new LabelFormatter(labels));
        BarDataSet dataSet = new BarDataSet(valueSet1, "");
        BarData data = new BarData(dataSet);
        data.setBarWidth(barwidth);
        chart.getXAxis().setSpaceMax(5f);
        chart.getDescription().setEnabled(false);
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setValueTextSize(20f);
        dataSet.setValueTextColor(R.color.black);
        dataSet.setColors(colors);

        XAxis xAxis = chart.getXAxis();
        xAxis.setSpaceMin(data.getBarWidth() / 2f);
        xAxis.setSpaceMax(data.getBarWidth() / 2f);
        YAxis y = chart.getAxisLeft();
        y.setLabelCount(5);
        y.setAxisMaxValue(100f);
        y.setAxisMinValue(0f);

        YAxis yr = chart.getAxisRight();
        yr.setLabelCount(5);
        yr.setAxisMinValue(0f);
        yr.setAxisMaxValue(100f);
        chart.setData(data);
        chart.invalidate();
    }
    private void setBarChartData(float a,float b, float d){
        ArrayList<BarEntry> BarEntry = new ArrayList<>();
        float barwidth = 20f;
        float spaceForBar = 15f;
        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(44f, a); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(88f, b); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(66f, d); // Mar
        valueSet1.add(v1e3);


        String[] labels = {"Analytical","Interactive","Instrospective","dd"};
        //chart.getXAxis().setValueFormatter(new LabelFormatter(labels));
        BarDataSet dataSet = new BarDataSet(valueSet1, "");
        BarData data = new BarData(dataSet);
        data.setBarWidth(barwidth);
        chart.getXAxis().setSpaceMax(5f);
        chart.getDescription().setEnabled(false);
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setValueTextSize(20f);
        dataSet.setValueTextColor(R.color.black);
        dataSet.setColors(colors);

        XAxis xAxis = chart.getXAxis();
        xAxis.setSpaceMin(data.getBarWidth() / 2f);
        xAxis.setSpaceMax(data.getBarWidth() / 2f);
        YAxis y = chart.getAxisLeft();
        y.setLabelCount(5);
        y.setAxisMaxValue(100f);
        y.setAxisMinValue(0f);

        YAxis yr = chart.getAxisRight();
        yr.setLabelCount(5);
        yr.setAxisMinValue(0f);
        yr.setAxisMaxValue(100f);
        chart.setData(data);
        chart.invalidate();
    }



    private void setHalfPieChartData(){
        pieChart.setBackgroundColor(Color.WHITE);

        //moveOffScreen();
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setCenterTextTypeface(mTfLight);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);
        pieChart.setHoleRadius(48f);
        pieChart.setTransparentCircleRadius(50f);
        pieChart.setDrawCenterText(true);
        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);
        setEgoGramData(3, 100,pieChart,mParties);
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        Legend l = pieChart.getLegend();
        l.setEnabled(false);
        // entry label styling
        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTypeface(mTfRegular);
        pieChart.setEntryLabelTextSize(10f);



    }
    private void setHalfPieChartData(float parent,float child,float adult){
        pieChart.setBackgroundColor(Color.WHITE);

        //moveOffScreen();
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setCenterTextTypeface(mTfLight);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);
        pieChart.setHoleRadius(48f);
        pieChart.setTransparentCircleRadius(50f);
        pieChart.setDrawCenterText(true);
        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);
        setEgoGramData(3, 100,pieChart,mParties,parent,child,adult);
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        Legend l = pieChart.getLegend();
        l.setEnabled(false);
        // entry label styling
        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTypeface(mTfRegular);
        pieChart.setEntryLabelTextSize(10f);



    }
    private void setData(int count ,int range){
        ArrayList<PieEntry> values = new ArrayList<>();
        String[] countries = new String []{"Parent ","Adult","Child"};
//        for(int i = 0;i<count;i++){
//            float val = (float)((Math.random()*range)+range/5);
//            values.add(new PieEntry(val,countries[i]));
//
//        }
        values.add(new PieEntry(33,countries[0]));
        values.add(new PieEntry(25,countries[1]));
        values.add(new PieEntry(42,countries[2]));

        PieDataSet dataset  = new PieDataSet(values,"");
        dataset.setSelectionShift(5f);
        dataset.setSliceSpace(3f);
        dataset.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData data = new PieData(dataset);
        data.setValueTextSize(0f);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextColor(Color.WHITE);
        pieChart.setData(data);
        pieChart.invalidate();

    }

    private void moveOffScreen(){
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height =  metrics.heightPixels;

        int offset = (int)(height*0.5);
        RelativeLayout.LayoutParams params =(RelativeLayout.LayoutParams) pieChart.getLayoutParams();
        params.setMargins(0,0,0,-offset);
        pieChart.setLayoutParams(params);
    }
    private void setEgoGramData(int count, float range, PieChart mChart, String[] mParties) {

        //float l;
//        total=creative+intelligence+social+spatial+emotional+verbal;
//        Log.d("totalScore",total+"");
//
//
//        float left=intelligence+spatial+verbal;
//        Log.d("totalscore",left+"");
//        l=(float)(left/total)*100;
//        Log.d("totalscoreleft",l+"");
//
//        float right=social+emotional+creative;
//        float r=(right/total)*100;
//        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry((float)40,"Parent"));
        entries.add(new PieEntry((float)40,"Adult"));
        entries.add(new PieEntry((float)20,"Child"));

        PieDataSet dataSet = new PieDataSet(entries, " ");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(9f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(mTfLight);
        mChart.setData(data);
        mChart.highlightValues(null);
        mChart.invalidate();
    }
    private void setEgoGramData(int count, float range, PieChart mChart, String[] mParties,float parent,float child,float adult) {

        //float l;
//        total=creative+intelligence+social+spatial+emotional+verbal;
//        Log.d("totalScore",total+"");
//
//
//        float left=intelligence+spatial+verbal;
//        Log.d("totalscore",left+"");
//        l=(float)(left/total)*100;
//        Log.d("totalscoreleft",l+"");
//
//        float right=social+emotional+creative;
//        float r=(right/total)*100;
//        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(parent,"Parent"));
        entries.add(new PieEntry(child,"Adult"));
        entries.add(new PieEntry(adult,"Child"));

        PieDataSet dataSet = new PieDataSet(entries, " ");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(9f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(mTfLight);
        mChart.setData(data);
        mChart.highlightValues(null);
        mChart.invalidate();
    }
    private void setData(int count, float range, PieChart mChart, String[] mParties) {

        float l;
        total=creative+intelligence+social+spatial+emotional+verbal;
        Log.d("totalScore",total+"");


        float left=intelligence+spatial+verbal;
        Log.d("totalscore",left+"");
        l=(float)(left/total)*100;
        Log.d("totalscoreleft",l+"");

        float right=social+emotional+creative;
        float r=(right/total)*100;
        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry((float)l,"Left Brain"));
        entries.add(new PieEntry((float)r,"Right Brain"));

        PieDataSet dataSet = new PieDataSet(entries, " ");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(9f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(mTfLight);
        mChart.setData(data);
        mChart.highlightValues(null);
        mChart.invalidate();
    }

    private void setData(int count, float range, PieChart mChart, String[] mParties,float leftbrain,float rightbrain) {

        float l;
        total=creative+intelligence+social+spatial+emotional+verbal;
        Log.d("totalScore",total+"");


        float left=intelligence+spatial+verbal;
        Log.d("totalscore",left+"");
        l=(float)(left/total)*100;
        Log.d("totalscoreleft",l+"");

        float right=social+emotional+creative;
        float r=(right/total)*100;
        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry((float)leftbrain,"Left Brain"));
        entries.add(new PieEntry((float)rightbrain,"Right Brain"));

        PieDataSet dataSet = new PieDataSet(entries, " ");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(9f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(mTfLight);
        mChart.setData(data);
        mChart.highlightValues(null);
        mChart.invalidate();
    }
    private void setData2(int count, float range, PieChart mChart, String[] mParties) {

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        if(intelligence!=0)
            entries.add(new PieEntry((float) ((intelligence/total)*100),"Intelligence"));

        if(creative!=0)
            entries.add(new PieEntry((float) ((creative/total)*100),"Creativity"));

        if(social!=0)
            entries.add(new PieEntry((float) ((social/total)*100),"Social"));

        if(spatial!=0)
            entries.add(new PieEntry((float) ((spatial/total)*100),"Spatial"));

        if(emotional!=0)
            entries.add(new PieEntry((float) ((emotional/total)*100),"Emotional"));

        if(verbal!=0)
            entries.add(new PieEntry((float) ((verbal/total)*100),"Verbal Ability"));


        PieDataSet dataSet = new PieDataSet(entries, " ");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);
        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(mTfLight);
        mChart.setData(data);
        mChart.highlightValues(null);
        mChart.invalidate();
    }

    void skipp(int x)
    {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        if(x == 1) {
            alertDialogBuilder.setMessage("It shows comparative study of various Holland Codes based on your Abilities & Interests.");
            alertDialogBuilder.setTitle("Smart Branding Quotient");
        } else if(x == 2) {
            alertDialogBuilder.setMessage("This report would give you glimpse of your skills & abilities and corresponding career options & courses. For detailed 7-Dimensional assessment & refined options, we recommend you to opt for “Career Report”.");
            alertDialogBuilder.setTitle("Brief Analysis Report");
        } else if(x == 3){
            alertDialogBuilder.setMessage("Egogram is system of feelings accompanied by related set of behavior patterns. Please note that it resets to default state if you disagree with every existing set of feelings.");
            alertDialogBuilder.setTitle("Your Egogram");
        }

        alertDialogBuilder.setPositiveButton("Got It",
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    //Toast.makeText(getActivity(),"Resume",Toast.LENGTH_LONG).show();
                }
            });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void setCareerOptionsRecycler(List<CareerList> career) {


        careerOptionsRecyclerView.setHasFixedSize(true);
        careerOptionsRecyclerView.removeAllViews();
        careerOptionsRecyclerView.setNestedScrollingEnabled(false);
        careerOptionsRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
        careerOptionsRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        if(getContext()!=null) {
            careerAdapter =
                    new CareerAdapter(getContext(), career, displayMetrics);
            careerOptionsRecyclerView.setAdapter(careerAdapter);
            careerOptionsRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

            careerAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void doBack()
    {
        Intent i= new Intent(getActivity(), Recommendations.class);
        startActivity(i);
    }

    @Override
    public void startAnalysisNotDone() {

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkError(String message) {

    }

    void setupRadarChart(){
        List<RadarEntry> entries = new ArrayList<>();
        entries.add(new RadarEntry(4f,0));
        entries.add(new RadarEntry(5f,1));
        entries.add(new RadarEntry(10f,2));
        entries.add(new RadarEntry(7f,3));
        entries.add(new RadarEntry(6f,4));
        entries.add(new RadarEntry(4f,5));
        RadarDataSet radarDataSet = new RadarDataSet(entries, "Paras");
        radarDataSet.setDrawFilled(true);
        radarDataSet.setColor(Color.CYAN);
//        radarDataSet.setFillAlpha(0);
        List<IRadarDataSet> radarDataSets = new ArrayList<>();
        radarDataSets.add(radarDataSet);
        RadarData radarData = new RadarData(radarDataSets);
        radarData.setHighlightEnabled(true);
        radarData.setValueTextColor(Color.BLACK);
        radarChart.animate();
        radarChart.setData(radarData);
        radarChart.getXAxis().setTextColor(R.color.dark_purple);
        radarChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            private String[] mActivities = new String[]{"Realistic", "Investigative", "Artistic", "Social", "Enterprising","Conventional"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        radarChart.getDescription().setEnabled(false);
        radarChart.getSkipWebLineCount();
        radarChart.setWebLineWidthInner(1.5f);
        radarChart.setWebColor(Color.RED);
        radarChart.setWebLineWidth(2.5f);
        radarChart.setWebColorInner(Color.BLACK);
        radarChart.getLegend().setEnabled(false);
        radarChart.getViewPortHandler().setChartDimens(50,50);
//        radarChart.setWebLineWidthInner(4f);

    }
    void setupRadarChart(float a,float b,float c,float d,float e,float f){
       // showMessage("radar");
        List<RadarEntry> entries = new ArrayList<>();
        entries.add(new RadarEntry(Float.parseFloat(a+"f"),0));
        entries.add(new RadarEntry(Float.parseFloat(b+"f"),1));
        entries.add(new RadarEntry(Float.parseFloat(c+"f"),2));
        entries.add(new RadarEntry(Float.parseFloat(d+"f"),3));
        entries.add(new RadarEntry(Float.parseFloat(e+"f"),4));
        entries.add(new RadarEntry(Float.parseFloat(f+"f"),5));
        RadarDataSet radarDataSet = new RadarDataSet(entries, "Paras");
        radarDataSet.setDrawFilled(true);
        radarDataSet.setColor(Color.CYAN);
//        radarDataSet.setFillAlpha(0);
        List<IRadarDataSet> radarDataSets = new ArrayList<>();
        radarDataSets.add(radarDataSet);
        RadarData radarData = new RadarData(radarDataSets);
        radarData.setHighlightEnabled(true);
        radarData.setValueTextColor(Color.BLACK);
        radarChart.animate();
        radarChart.setData(radarData);
        radarChart.getXAxis().setTextColor(R.color.dark_purple);
        radarChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            private String[] mActivities = new String[]{"Realistic", "Investigative", "Artistic", "Social", "Enterprising","Conventional"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        radarChart.getDescription().setEnabled(false);
        radarChart.getSkipWebLineCount();
        radarChart.setWebLineWidthInner(1.5f);
        radarChart.setWebColor(Color.RED);
        radarChart.setWebLineWidth(2.5f);
        radarChart.setWebColorInner(Color.BLACK);
        radarChart.getLegend().setEnabled(false);
        radarChart.getViewPortHandler().setChartDimens(50,50);
        radarChart.invalidate();
//        radarChart.setWebLineWidthInner(4f);

    }




    @Override
    public void setUniversitiesRecyclerView(List<University> institutions) {
        institutesRecyclerView.setHasFixedSize(true);
        institutesRecyclerView.removeAllViews();
        institutesRecyclerView.setNestedScrollingEnabled(false);
        institutesRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
        institutesRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        if(getContext()!=null) {
            adapter =
                    new RecommendedUniAdapter(getContext(), institutions, displayMetrics) {

                    };
            institutesRecyclerView.setAdapter(adapter);
            institutesRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

            adapter.notifyDataSetChanged();
        }

    }
    private void setInstitutesRecyclerView() {

        ArrayList<Institution> institutions=new ArrayList<>();
        Institution institution1 = new Institution();
        Institution institution2 = new Institution();
        institution1.setName("IIT DELHI");
        institution2.setName("UDACITY");

        institutions.add(institution1);
        institutions.add(institution2);

//        ArrayList<Integer> images=new ArrayList<>();
//        institution1.add(R.drawable.institution1);
//        institution2.add(R.drawable.institution2);


        institutesRecyclerView.hasFixedSize();
        institutesRecyclerView.removeAllViews();
        institutesRecyclerView.setNestedScrollingEnabled(false);
        institutesRecyclerView.removeAllViewsInLayout();

        institutesAdapter=new InstitutesAdapter(getContext(),institutions);
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2);
        institutesRecyclerView.setLayoutManager(layoutManager);
        institutesRecyclerView.setHasFixedSize(true);
        institutesRecyclerView.setAdapter(institutesAdapter);

    }
    public void setTopCourses(List<CourseList> courses)
    {
        topCoursesItemAdapter=new TopCoursesItemAdapter(getContext(),courses);

        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        coursesRecyclerView.setLayoutManager(layoutManager);
        coursesRecyclerView.setAdapter(topCoursesItemAdapter);
        topCoursesItemAdapter.notifyDataSetChanged();

    }
    public void setCoursesRecyclerView(List<CourseList> courses){
        coursesRecyclerView.setHasFixedSize(true);
        coursesRecyclerView.removeAllViews();
        coursesRecyclerView.setNestedScrollingEnabled(false);
        coursesRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
        coursesRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        RecommendedCourseAdapter courseAdapter =
                new RecommendedCourseAdapter(MyApplication.getInstance().prefManager,getActivity(), courses){
                    @Override
                    public void like(int id) {
                        presenter.likeCourse(id);
                    }
                };

        coursesRecyclerView.setAdapter(courseAdapter);
        coursesRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        courseAdapter.notifyDataSetChanged();
    }
    public void setReportData(Report report){
//        showMessage("setting data");
//        String json = MyApplication.getInstance().prefManager.getString("section1");
//        Gson gson = new Gson();
//        Section1 section1 = gson.fromJson(json,Section1.class);
//        Childname.setText(section1.getName());
//        childName2.setText(section1.getName());
//        setHalfPieChartData();
//        setBarChartData(report.getInfluence(),report.getAchievement(),report.getAffiliation());
//        showMessage("radardata"+","+report.getRealistic()+","+report.getInvestigative()+","+report.getArtistic()+","+report.getSocial()+","+report.getEnterprising()+","+report.getConventional());
//        //setupRadarChart(report.getRealistic(),report.getInvestigative(),report.getArtistic(),report.getSocial(),report.getEnterprising(),report.getConventional());
//        setData(2, 100,mChart,mParties,report.getLeftBrain(),report.getRightBrain());
    }

}