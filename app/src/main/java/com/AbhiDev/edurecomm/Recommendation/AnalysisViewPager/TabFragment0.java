package com.AbhiDev.edurecomm.Recommendation.AnalysisViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wireout.Activities.BackPressed;
import com.wireout.Activities.LoginActivity;
import com.wireout.Activities.Recommendations;
import com.wireout.R;
import com.wireout.Recommendation.TabFragmentViewAction;
import com.wireout.Recommendation.Top2;
import com.wireout.common.MyApplication;
import com.wireout.common.PrefManager;
import com.wireout.listeners.OnUserReceivedListener;
import com.wireout.models.University;
import com.wireout.models.User;
import com.wireout.presenters.CurrentUserPresenter;
import com.wireout.viewactions.BaseViewAction;
import com.yarolegovich.lovelydialog.LovelyCustomDialog;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.util.List;

public class TabFragment0 extends Fragment implements BackPressed.OnBackPressedListener,OnUserReceivedListener, TabFragmentViewAction,BaseViewAction {

    TextView viewInsights;
    TextView profileName;
    ImageView profileImage;
    ImageView help;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view =  inflater.inflate(R.layout.profile_layout, container, false);
        viewInsights = view.findViewById(R.id.viewinsights);
        profileImage = view.findViewById(R.id.profile_Image);
        help = view.findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LovelyStandardDialog(getContext(), LovelyStandardDialog.ButtonLayout.VERTICAL)
                        .setTopColorRes(R.color.filter_blue)
                        .setButtonsColorRes(R.color.orange)
                        .setIcon(R.drawable.ic_action_info)
                        .setTitle("Improve Profile Accuracy")
                        .setMessage("Improve profile accuracy by taking more quizzes and inviting friends to answer questions about you.")
                        .setPositiveButton("Take more quizzes", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMessage("Take quizzes.");
                            }
                        })
                        .setNegativeButton("Ask Friends", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMessage("Ask Friends.");
                            }
                        })
                        .show();
            }
        });

        if(MyApplication.getInstance().prefManager.isLoggedIn()){
            new CurrentUserPresenter(this, this).getCurrentUser();
            if(MyApplication.getInstance().prefManager.getString(LoginActivity.FB_LOGIN)!=null&&
                    MyApplication.getInstance().prefManager.getString(LoginActivity.FB_PROFILE)!=null){
                // showMessage("true");
                if(MyApplication.getInstance().prefManager.getString(LoginActivity.FB_LOGIN).equals("true")){
                    // showMessage("profile"+MyApplication.getInstance().prefManager.getString(LoginActivity.FB_PROFILE) +"");
                    Picasso.with(getContext()).load("https://graph.facebook.com/" + MyApplication.getInstance().prefManager.getString(LoginActivity.FB_PROFILE) + "/picture?type=large").
                            error(R.drawable.ic_profile_default).into(profileImage);

                }

            }
            else if(MyApplication.getInstance().prefManager.getString(LoginActivity.GOOGLE_LOGIN)!=null){
                Picasso.with(getContext()).load(MyApplication.getInstance().prefManager.getString(LoginActivity.GOOGLE_LOGIN)).error(R.drawable.ic_profile_default).into(profileImage);
            }

            else{
                //handle here for google login and default login

                Picasso.with(getContext()).load(R.drawable.ic_profile_default).into(profileImage);

            }
        }
        else{
            profileName.setText("User");

            Picasso.with(getContext()).load(R.drawable.ic_profile_default).into(profileImage);
        }

        profileName = view.findViewById(R.id.name);

        viewInsights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Top2 top2 = (Top2) getParentFragment();
                if(top2 != null)
                    top2.viewPager.setCurrentItem(1, true);
            }
        });
        return view;
    }
    public static TabFragment0 create() {

        return new TabFragment0();
    }


    @Override
    public void onUserReceived(User user) {
       profileName.setText(user.getFirstName()+" "+user.getLastName());
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showNetworkError(String message) {

    }

    @Override
    public void setUniversitiesRecyclerView(List<University> universities) {

    }

    @Override
    public void doBack() {
        Intent i= new Intent(getActivity(), Recommendations.class);
        startActivity(i);
    }

    @Override
    public void confirmVisible() {

    }

    @Override
    public void startAnalysisNotDone() {

    }
}
