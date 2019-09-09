package com.AbhiDev.edurecomm.Fragments;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.wireout.Activities.AllCoursesActivity;
import com.wireout.Activities.AptitudeActivity;
import com.wireout.Activities.CareerActivity;
import com.wireout.Activities.CategoryActivity;
import com.wireout.Activities.CategoryLandingPage;
import com.wireout.Activities.RandomizedFeedsActivity;
import com.wireout.Activities.TedTalksActivity;
import com.wireout.Activities.TrendingArticlesActivity;
import com.wireout.Activities.YantraClubActivity;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.listeners.OnUserReceivedListener;
import com.wireout.models.CareerList;
import com.wireout.videos.Config;
import com.wireout.R;
import com.wireout.ViewMore.AmbassdorsActivity;
import com.wireout.ViewMore.TrendingUniversitiesActivity;
import com.wireout.adapters.AmbassdorsRecyclerViewAdapter;
import com.wireout.adapters.CareerAdapter;
import com.wireout.adapters.RecommendedUniAdapter;
import com.wireout.adapters.TedTalksAdapter;
import com.wireout.adapters.TopCoursesItemAdapter;
import com.wireout.adapters.WikipediaAdapter;
import com.wireout.apiservices.Repository;
import com.wireout.common.ChildAnimationExample;
import com.wireout.common.MyApplication;
import com.wireout.models.Articles;
import com.wireout.models.BannerImages;
import com.wireout.models.Course;
import com.wireout.models.CourseList;
import com.wireout.models.DailyGoals;
import com.wireout.models.Mentor;
import com.wireout.models.ShortTermCourse;
import com.wireout.models.TedYouTube;
import com.wireout.models.University;
import com.wireout.models.User;
import com.wireout.presenters.CurrentUserPresenter;
import com.wireout.presenters.HomePagePresenter;
import com.wireout.presenters.TrendingArticlesPresenter;
import com.wireout.repositories.HomePageRepository;
import com.wireout.viewactions.UniversityViewAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends android.support.v4.app.Fragment implements OnUserReceivedListener,UniversityViewAction ,ViewPagerEx.OnPageChangeListener,BaseSliderView.OnSliderClickListener{

    private HomeFragment.OnFragmentInteractionListener mListener;

    RecommendedUniAdapter adapter;
    ScrollView scrollView;
    ImageView micSearch;
    SearchView searchKeyboard;
    CareerAdapter careerAdapter;
    HashMap<String,String> urlMap;
    FloatingActionButton floatingActionButton;
    OnEntitiesReceivedListener<BannerImages> imagesListener;
    SliderLayout exploreSlider;
    OnEntitiesReceivedListener<CourseList> courseListener;
    LinearLayout buttonsLayout;
    CardView randomizer;
    OnEntitiesReceivedListener<CareerList> careerListener;
    private RecyclerView universityRecyclerView,trendingRecyclerView,courseRecyclerView,tedRecyclerView,careerOptionsRecyclerView;
    HomePagePresenter presenter;
    TedTalksAdapter tedTalksAdapter;
    WikipediaAdapter wikipediaAdapter;
    TopCoursesItemAdapter topCoursesItemAdapter;
    OnEntitiesReceivedListener<Mentor> listener;
    View view;
    ImageView news,events,recommender;
    PieChart mChart;
    protected Typeface mTfLight;
    ImageView rewards;
    protected Typeface mTfRegular;
    SliderLayout mDemoSlider;
    DisplayMetrics displayMetrics;
    ArrayList<String> links,stories;
    YouTubeThumbnailView youTubeThumbnailView;
    private RecyclerView ambassdorsRecyclerView;
    AmbassdorsRecyclerViewAdapter ambassdorsRecyclerViewAdapter;
    private TextView viewAllTeds;
    private TextView viewAllTrending;
    private TextView viewAllCareer;
    private TextView seeMoreCategory;
    private TextView viewAllCourses;
    private TextView viewAllUniv;
    private TextView viewAllAmbass;
    Intent i;
    ImageView yantraClub;
    private TextView goalOne,goalTwo,goalThree;
    private TextView userName;
    CardView cardOne,cardTwo,cardThree,cardFour;

    //api working
    private OnEntitiesReceivedListener<Articles> articlesListener;
    private TrendingArticlesPresenter trendingArticlesPresenter;
    private OnEntitiesReceivedListener<DailyGoals> goalsListener;


    public HomeFragment(){

    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter = new HomePagePresenter(getContext(),this,new HomePageRepository());
        view =  inflater.inflate(R.layout.activity_new_homepage, container, false);
        yantraClub = view.findViewById(R.id.yantra_club);
        buttonsLayout = view.findViewById(R.id.appbar);
        cardOne = view.findViewById(R.id.card1);
        youTubeThumbnailView =view.findViewById(R.id.why_image);
        youTubeThumbnailView.initialize(Config.DEVELOPER_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

                //             if(urls.get(position)!=null) {
                // youTubeThumbnailLoader.setVideo("bA4-FRJ5afc");
                //           }
                //youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //Toast.makeText(ctx,"failed",Toast.LENGTH_SHORT).show();
            }
        });
        youTubeThumbnailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= YouTubeStandalonePlayer.createVideoIntent(getActivity(),Config.DEVELOPER_KEY,"Huv4IwbVvf8");
                startActivity(intent);
            }
        });
        scrollView = view.findViewById(R.id.scrollview);
        cardTwo = view.findViewById(R.id.card2);
        cardThree = view.findViewById(R.id.card3);
        cardFour = view.findViewById(R.id.card4);
        presenter.getUniversities();

        imagesListener = new OnEntitiesReceivedListener<BannerImages>(getContext()) {
            @Override
            public void onReceived(List<BannerImages> entities) {
                setExploreBannerImages(entities);
            }
        };
        presenter.getBannerImages(imagesListener);
        courseListener = new OnEntitiesReceivedListener<CourseList>(getContext()) {
            @Override
            public void onReceived(List<CourseList> entities) {
                setTopCourses(entities);
            }
        };
        careerListener = new OnEntitiesReceivedListener<CareerList>(getContext()) {
            @Override
            public void onReceived(List<CareerList> entities) {
                setCareerOptionsRecyclerView(entities);
            }
        };
        listener = new OnEntitiesReceivedListener<Mentor>(getContext()) {
            @Override
            public void onReceived(List<Mentor> entities) {
                setCampaignersRecyclerView(entities);
            }
        };
        presenter.getCampaigners(listener);
        presenter.getCareer(careerListener);
        cardOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(getContext(), CategoryLandingPage.class);
                i.putExtra("id","2");
                startActivity(i);
            }
        });
        cardTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(getContext(), CategoryLandingPage.class);
                i.putExtra("id","8");
                startActivity(i);
            }
        });
        cardThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(getContext(), CategoryLandingPage.class);
                i.putExtra("id","12");
                startActivity(i);
            }
        });
        cardFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(getContext(), CategoryLandingPage.class);
                i.putExtra("id","5");
                startActivity(i);
            }
        });
        yantraClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), YantraClubActivity.class));
            }
        });
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        universityRecyclerView = view.findViewById(R.id.universty_recyclerview);
        rewards = view.findViewById(R.id.rewards);

        tedRecyclerView = view.findViewById(R.id.ted_recyclerview);
        randomizer = view.findViewById(R.id.randomizer_layout);
        userName = view.findViewById(R.id.username);
        searchKeyboard = view.findViewById(R.id.searchView);
        trendingRecyclerView = view.findViewById(R.id.trending_recyclerview);
        micSearch = view.findViewById(R.id.mic_search);
        ambassdorsRecyclerView = view.findViewById(R.id.am_recycler_view);
        news = view.findViewById(R.id.news_events);
        floatingActionButton = view.findViewById(R.id.fab);
        recommender = view.findViewById(R.id.recommender);
        events = view.findViewById(R.id.events_image);
        exploreSlider = view.findViewById(R.id.explore_slider);
        courseRecyclerView = view.findViewById(R.id.subjects_recyclerview);
        mDemoSlider = (SliderLayout)view.findViewById(R.id.slider);
        careerOptionsRecyclerView = view.findViewById(R.id.career_recyclerview);
        displayMetrics=new DisplayMetrics();
        mChart = view.findViewById(R.id.chart);
        seeMoreCategory = view.findViewById(R.id.see_more_category);
        viewAllCourses = view.findViewById(R.id.view_all_courses);
        viewAllUniv = view.findViewById(R.id.view_all_univ);
        viewAllCareer = view.findViewById(R.id.view_all_career);
        viewAllTeds = view.findViewById(R.id.view_all_teds);
        viewAllAmbass = view.findViewById(R.id.view_all_ambass);
        viewAllTrending = view.findViewById(R.id.view_all_trending);

        goalOne = view.findViewById(R.id.goal1);
        goalTwo = view.findViewById(R.id.goal2);
        goalThree = view.findViewById(R.id.goal3);

        goalsListener = new OnEntitiesReceivedListener<DailyGoals>(getContext()) {
            @Override
            public void onReceived(List<DailyGoals> goals) {
                goalOne.setText(goals.get(0).getTitle());
                goalTwo.setText(goals.get(1).getTitle());
                goalThree.setText(goals.get(2).getTitle());
                Log.d("coursesize",goals.get(0).getTitle());
            }
        };
        presenter.getDailyGoals(goalsListener);

        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        ViewGroup.LayoutParams params = buttonsLayout.getLayoutParams();
// Changes the height and width to the specified *pixels*
        Double height=displayMetrics.heightPixels*(0.7);
        params.height = height.intValue();
        params.width = displayMetrics.widthPixels;
        buttonsLayout.setLayoutParams(params);

        if(MyApplication.getInstance().prefManager.isLoggedIn()){
            new CurrentUserPresenter(this,this).getCurrentUser();
        }
        else{
            userName.setText("Aspirant,");
        }
//        subjects = new ArrayList<>();
//        subjects.add("Become a Data Scientist");
//        subjects.add("Become a Financial Analyst");
//        subjects.add("Become a Lawyer");
//        subjects.add("Become a Web Developer");
//        subjects.add("Become an Android Developer");

        links = new ArrayList<>();
        links.add("_tewedUBhAo");
        links.add("Fw1Fc_y_2Ek");
        links.add("9Q7Zl3OI4us");
        links.add("kcW4ABcY3zI");
        links.add("LTnI7cmpDZI");

        stories = new ArrayList<>();
        stories.add("What it's like to be the child of immigrants");
        stories.add("The interesting story of our educational system");
        stories.add("The 3 Myths of the Indian Education System");
        stories.add("A well educated mind vs a well formed mind");
        stories.add("What makes life complete?");
        articlesListener = new OnEntitiesReceivedListener<Articles>(getContext()) {
            @Override
            public void onReceived(List<Articles> articles) {
                setTrendingRecyclerView(articles.subList(0,4));
            }
        };
        trendingArticlesPresenter = new TrendingArticlesPresenter(new Repository());
        trendingArticlesPresenter.getArticles(articlesListener);





        setTedTalksRecyclerView(links,stories);
        presenter.getCourses(courseListener);
        //setTopCourses();
        mTfRegular = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");
        mTfLight = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");
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
        mChart.setTransparentCircleRadius(60f);
        mChart.setDrawCenterText(true);
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);
        setData(4, 100);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        Legend l=mChart.getLegend();
        l.setEnabled(false);
        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);
        mChart.setEntryLabelTypeface(mTfRegular);
        mChart.setEntryLabelTextSize(12f);
        mChart.setEntryLabelColor(R.color.black);

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Kanjur Wangdi",R.drawable.test_1);
        file_maps.put("Zainaab Manzoor" , R.drawable.test_2);
        file_maps.put("Shikha Gaur",R.drawable.test_3);
        file_maps.put("Ekta Siha",R.drawable.test_4);
        file_maps.put("Raishree Oinam",R.drawable.test_5);
        file_maps.put("Sapana Karki",R.drawable.test_6);
        file_maps.put("Benjamin Varghese",R.drawable.test_7);
        file_maps.put("Komal Sharma",R.drawable.test_8);
        for(String name : file_maps.keySet()){
            DefaultSliderView textSliderView = new DefaultSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterInside)
                    .setOnSliderClickListener(this);

            //add your extra information
//            textSliderView.bundle(new Bundle());
//            textSliderView
//                    .getBundle()
//                    .putString("extra",name);
            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
        mDemoSlider.setPresetTransformer("ZoomOut");
        mDemoSlider.setCustomAnimation(new ChildAnimationExample());


        rewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getContext(), InternshipListActivity.class));
            }
        });

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), OptionsActivity.class);
//                intent.putExtra("option","1");
//                startActivity(intent);

            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), EventsActivity.class);
////                intent.putExtra("option","2");
//                startActivity(intent);

            }
        });

        recommender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AptitudeActivity.class);
                //i.putExtra("state","2");
                startActivity(i);
            }
        });

        searchKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(getContext(), SearchActivity.class);
//                i.putExtra("searchQuery",searchKeyboard.getQuery());
//                startActivity(i);
                // showMessage("cli");
            }
        });
        micSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(getContext(), MessageListActivity.class));
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AptitudeActivity.class));
            }
        });
        randomizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), RandomizedFeedsActivity.class));
            }
        });
        // presenter.getCampaigners();
        seeMoreCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CategoryActivity.class));
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
        viewAllCareer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CareerActivity.class);
                intent.putExtra("landing","no");
                startActivity(intent);
            }
        });
        viewAllTeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), TedTalksActivity.class));
            }
        });
        viewAllTrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(), TrendingArticlesActivity.class);
                intent.putExtra("landing","no");
                startActivity(intent);
            };
        });
        viewAllAmbass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AmbassdorsActivity.class));
            }
        });
        viewAllUniv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(getContext(), TrendingUniversitiesActivity.class);
                intent.putExtra("landing","no");
                startActivity(intent);
            }
        });

        //adding static campaigners


        return view;

    }
    public static void watchYoutubeVideo(Context context, String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }
    public void setExploreBannerImages(List<BannerImages> bannerImages){

        Log.d("imageBanner",bannerImages.size()+"");
        urlMap = new HashMap<>();
        for(BannerImages image : bannerImages){
            urlMap.put(image.getUrl()+"",image.getImage());
        }
        for(String name : urlMap.keySet()){
            DefaultSliderView sliderView = new DefaultSliderView(getContext());
            // initialize a SliderLayout
            Log.d("imageBanner",urlMap.get(name));
            sliderView
                    .description(name)
                    .image(urlMap.get(name))
                    .setOnSliderClickListener(this);
            exploreSlider.addSlider(sliderView);
        }

        exploreSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        exploreSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        exploreSlider.setCustomAnimation(new DescriptionAnimation());
        exploreSlider.setDuration(4000);
        exploreSlider.addOnPageChangeListener(this);
        //exploreSlider.setPresetTransformer("ZoomOut");
        exploreSlider.setCustomAnimation(new ChildAnimationExample());
    }
    @Override
    public void setCampaignersRecyclerView(List<Mentor> campaigners){
        ambassdorsRecyclerView.setHasFixedSize(true);
        ambassdorsRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        ambassdorsRecyclerView.setLayoutManager(linearLayoutManager);
        if(getContext()!=null) {
            ambassdorsRecyclerViewAdapter = new AmbassdorsRecyclerViewAdapter(getActivity(), campaigners, displayMetrics) {
                @Override
                public void like(int id) {
                    presenter.likeCampaigners(id);
                }
            };
            ambassdorsRecyclerView.setAdapter(ambassdorsRecyclerViewAdapter);
            ambassdorsRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    YouTubeThumbnailLoader.OnThumbnailLoadedListener onThumbnailLoadedListener =  new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
        @Override
        public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {

        }

        @Override
        public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
            youTubeThumbnailView.setVisibility(View.VISIBLE);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //showMessage("resumed");
    }

    private void setData(int count, float range) {

        float mult = range;
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();


        entries.add(new PieEntry((float) (26),"",
                getResources().getDrawable(R.drawable.ic_star_selected)));

        entries.add(new PieEntry((float) (35),"",
                getResources().getDrawable(R.drawable.ic_star_selected)));

        entries.add(new PieEntry((float) (10),"",
                getResources().getDrawable(R.drawable.ic_star_selected)));

        entries.add(new PieEntry((float) (29),"",
                getResources().getDrawable(R.drawable.ic_star_selected)));


        PieDataSet dataSet = new PieDataSet(entries, " ");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(5f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);
        // dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueTextColor(R.color.black);
        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setValueTextSize(20f);
        dataSet.setValueTextColor(R.color.black);
        dataSet.setColors(colors);
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
    public void scrollToTop(){
        scrollView.smoothScrollTo(0,0);
    }
    public void setTopCourses(List<CourseList> courses)
    {
//
        courseRecyclerView.hasFixedSize();
        courseRecyclerView.removeAllViews();
        courseRecyclerView.setNestedScrollingEnabled(false);
        courseRecyclerView.removeAllViewsInLayout();


        topCoursesItemAdapter=new TopCoursesItemAdapter(getContext(),courses){
            @Override
            public void like(int id) {
                presenter.likeCourse(id);
            }
        };

        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        courseRecyclerView.setLayoutManager(layoutManager);
        courseRecyclerView.setAdapter(topCoursesItemAdapter);
        topCoursesItemAdapter.notifyDataSetChanged();

    }
    public void setCareerOptionsRecyclerView(List<CareerList> career) {
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
    public void setTedTalksRecyclerView(List<String> links,List<String> text){

        TedYouTube video1 = new TedYouTube();
        video1.setTitle(text.get(0));
        video1.setYoutubeId(links.get(0));

        TedYouTube video2 = new TedYouTube();
        video2.setTitle(text.get(1));
        video2.setYoutubeId(links.get(1));

        TedYouTube video3 = new TedYouTube();
        video3.setTitle(text.get(2));
        video3.setYoutubeId(links.get(2));

        TedYouTube video4 = new TedYouTube();
        video4.setTitle(text.get(3));
        video4.setYoutubeId(links.get(3));

        TedYouTube video5 = new TedYouTube();
        video5.setTitle(text.get(4));
        video5.setYoutubeId(links.get(4));

        List<TedYouTube> videos = new ArrayList<>();
        videos.add(video1);
        videos.add(video2);
        videos.add(video3);
        videos.add(video4);
        videos.add(video5);

        tedRecyclerView.setHasFixedSize(true);
        tedRecyclerView.removeAllViews();
        tedRecyclerView.setNestedScrollingEnabled(false);
        tedRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
        tedRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        tedTalksAdapter =
                new TedTalksAdapter(getContext(),videos);
        tedRecyclerView.setAdapter(tedTalksAdapter);
        tedRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        tedTalksAdapter.notifyDataSetChanged();
    }
    @Override
    public void setUniversitiesRecyclerView(List<University> institutions) {
        universityRecyclerView.setHasFixedSize(true);
        universityRecyclerView.removeAllViews();
        universityRecyclerView.setNestedScrollingEnabled(false);
        universityRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
        universityRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        adapter =
                new RecommendedUniAdapter(getContext(),institutions,displayMetrics){
                    @Override
                    public void like(int id) {
                        presenter.likeUniversity(id);
                    }
                };
        universityRecyclerView.setAdapter(adapter);
        universityRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        adapter.notifyDataSetChanged();

    }
    public void setTrendingRecyclerView(List<Articles> articles) {
        trendingRecyclerView.setHasFixedSize(true);
        trendingRecyclerView.removeAllViews();
        trendingRecyclerView.setNestedScrollingEnabled(false);
        trendingRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
        trendingRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        if(getContext()!=null) {
            wikipediaAdapter =
                    new WikipediaAdapter(getContext(), articles);
            trendingRecyclerView.setAdapter(wikipediaAdapter);
            trendingRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
            wikipediaAdapter.notifyDataSetChanged();
        }

    }
    public static HomeFragment newInstance(int index) {
        HomeFragment fragment = new HomeFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void hideToolbar() ;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.view = null;
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.view = null;
        mListener = null;
    }

    @Override
    public void setCoursesRecyclerView(List<Course> courses) {

    }

    @Override
    public void setShortTermCoursesRecyclerView(List<ShortTermCourse> shortTermCourses) {

    }

    @Override
    public void setLikedUniversity(int id) {
        presenter.likeUniversity(id);
    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showNetworkError(String message) {

    }

    public void openBrowser(String url)
    {
        if(url!=null&&url.charAt(0)=='h') {
            Log.d("slider description",url.length()+"");
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
    @Override
    public void onSliderClick(BaseSliderView slider) {

        if(slider.getDescription()!=null&&!slider.getDescription().equals("null")){
            Log.d("slider description",slider.getDescription());
            if(slider.getDescription().length()>0)
                openBrowser(slider.getDescription());
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    @Override
    public void onUserReceived(User user) {
        //showMessage(user.getId() + ": " + user.getEmail());
        userName.setVisibility(View.VISIBLE);
        userName.setText(user.getFirstName()+",");
        Log.d("username",user.getFirstName()+",");
    }
}
