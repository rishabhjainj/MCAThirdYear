package com.AbhiDev.edurecomm.Recommendation.AnalysisViewPager;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import com.roughike.swipeselector.SwipeItem;
import com.roughike.swipeselector.SwipeSelector;
import com.wireout.Activities.LoginActivity;
import com.wireout.Activities.PaymentsActivity;
import com.wireout.apiservices.Repository;
import com.wireout.common.MultiSelectSpinner;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.Slot;
import com.wireout.models.University;
import com.wireout.presenters.TabFragment2Presenter;
import com.wireout.R;
import com.wireout.Recommendation.BaseFragment;
import com.wireout.Recommendation.TabFragmentViewAction;
import com.wireout.common.PrefManager;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class TabFragment3 extends BaseFragment implements ViewPagerEx.OnPageChangeListener,AdapterView.OnItemSelectedListener,TabFragmentViewAction,MultiSelectSpinner.OnMultipleItemsSelectedListener {

    PrefManager prefManager;
    private SliderLayout mDemoSlider;
     SwipeSelector sizeSelector;
    ImageView research,happy;
    LinearLayout bookingComplete;
    String contact;
    String selectedSlot;
    int id=1;
    ArrayList<Slot> slotArrayList;
    TabFragment2Presenter presenter;
    CardView pay;
    Bitmap bitmap;
    OnEntitiesReceivedListener<Slot> listener;
    ImageView startLiveChatButton;
    MultiSelectSpinner spinner;
    TextView payMoney;
    EditText idText;
    Button confirmBooking;
    ImageView call,duo,skype;
    @Override
    public int getLayoutResId() {
        return R.layout.tab_fragment_1;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void selectedIndices(List<Integer> indices) {
       // bookingComplete.setVisibility(View.VISIBLE);
    }

    @Override
    public void confirmVisible() {
        bookingComplete.setVisibility(View.VISIBLE);
    }

    @Override
    public void selectedStrings(List<String> strings) {
       // bookingComplete.setVisibility(View.VISIBLE);
    }

    @Override
    public void setUniversitiesRecyclerView(List<University> universities) {

    }

    @Override
    public void inOnCreateView(View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        prefManager = new PrefManager(getActivity());
        bookingComplete = view.findViewById(R.id.slot_complete);
        pay = view.findViewById(R.id.payButton);
        slotArrayList = new ArrayList<>();
        bookingComplete = view.findViewById(R.id.slot_complete);
        confirmBooking= view.findViewById(R.id.confirm);
        idText = view.findViewById(R.id.idEditText);
        call = view.findViewById(R.id.call);
        duo = view.findViewById(R.id.duo);
        skype = view.findViewById(R.id.skype);

        presenter = new TabFragment2Presenter(this,new Repository());
        listener = new OnEntitiesReceivedListener<Slot>(getContext()) {
            @Override
            public void onReceived(List<Slot> entities) {
                slotArrayList.addAll( entities);
                setSlots(entities);
            }
        };
        presenter.getSlots(listener);
        confirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (idText.getText().toString().length() > 3) {

                    contact = idText.getText().toString();
                    selectedSlot = spinner.getSelectedItemsAsString();
                    ArrayList<String> slotString = new ArrayList<>();
                    for (Slot slot : slotArrayList) {
                        slotString.add(slot.getTitle());
                    }


                   // int slotid = 0;
                   // slotid = slotString.indexOf(selectedSlot);
                   // int slotIdInList = slotArrayList.get(slotid).getId();
                    String mode = "skype";
                    switch (id) {
                        case 1:
                            mode = "skype";
                            break;
                        case 2:
                            mode = "duo";
                            break;
                        case 3:
                            mode = "whatsapp";
                            break;
                    }
                    Log.d("slotbooking",selectedSlot+","+mode+","+contact);
                    presenter.bookSlot(selectedSlot, mode, contact);
                }
                else{
                    showMessage("Enter valid contact details!");
                }
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id= 3;
                idText.setHint("Your WhatsApp number");
            }
        });
        duo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=2;
                idText.setHint("Your Duo number");
            }
        });
        skype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=1;
                idText.setHint("Your Skype Id");
            }
        });
        spinner= view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        String[] slots = {"09 Aug , 9:00 PM","10 Aug , 2:00 PM"};
        spinner.setItems(slots);
        spinner.hasNoneOption(true);
        spinner.setListener(this);

        sizeSelector = (SwipeSelector) view.findViewById(R.id.sizeSelector);

        research=(ImageView)view.findViewById(R.id.expertise);
        research.setColorFilter(getResources().getColor(R.color.dark_purple));

        happy=(ImageView)view.findViewById(R.id.happy);
        happy.setColorFilter(getResources().getColor(R.color.white));

        payMoney = view.findViewById(R.id.pay_money);
        payMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PaymentsActivity.class);
                intent.putExtra("price","3600");
                startActivity(intent);
            }
        });

        sizeSelector.setItems(
                new SwipeItem(0,"Launch Career Journey","Pay premium and Start Career Journey in right direction."),
                new SwipeItem(1,"Start Live Sessions with Expert","You are entitled to book any 3 time slots at the time of payment with our experts to have 1:1 mentorship."),
                new SwipeItem(2,"Primary Session","Our expert would analyze your current skill levels and generated career assessment report to explain meaning of each term used in report, to discuss areas of improvement and to discuss potential career recommendations & courses." ),
                new SwipeItem(3,"Focused Session","Our expert would suggest various career path which you can take to direct your career direction. He will recommend various short-term or long-term courses, brain exercises, development techniques to help you to enroll to right career direction."),
                new SwipeItem(4,"Closing Session","Joint discussion in terms of improvements, final closure on career path to be taken, decisioning of college or course selection, detailing of career building techniques and steps of next interaction (if required).")
        );

//        startLiveChatButton = view.findViewById(R.id.startLiveChatButton);
//        startLiveChatButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startWhatsapp();
//            }
//        });


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setSlots(List<Slot> slots){
        ArrayList<String> slotString = new ArrayList<>();
        for(Slot slot:slots){
            slotString.add(slot.getTitle());
        }
        spinner.setItems(slotString);
        spinner.hasNoneOption(true);
        spinner.setListener(this);

    }
    public static TabFragment3 create() {

        return new TabFragment3();
    }


    public void startLoginActivity(){
        Toast.makeText(getActivity(),"Login To Add Products To Cart",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(),LoginActivity.class);
        startActivity(i);
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


    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkError(String message) {

    }

    @Override
    public void startAnalysisNotDone() {

    }

    public void startWhatsapp(){
        PackageManager packageManager = getContext().getPackageManager();
        String phone = "919818787752";
        String message = "Hi! I want to start a Live Chat session that you provide under T4U. I've paid for the session.";
        Intent i = new Intent(Intent.ACTION_VIEW);

        try {
            String url = "https://api.whatsapp.com/send?phone="+ phone +"&text=" + URLEncoder.encode(message, "UTF-8");
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            if (i.resolveActivity(packageManager) != null) {
                getContext().startActivity(i);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
