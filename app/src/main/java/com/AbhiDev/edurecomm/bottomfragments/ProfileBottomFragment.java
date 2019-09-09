package com.AbhiDev.edurecomm.bottomfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wireout.Activities.Analysis.AnalysisActivity;
import com.wireout.R;

public class ProfileBottomFragment extends Fragment {

    View view;
    ImageView startButton;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_profile_layout, container, false);
        this.view = view;
        startButton = (ImageView) view.findViewById(R.id.start_p);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(),AnalysisActivity.class);
                i.putExtra("state","3");
                startActivity(i);
            }
        });
        return view;
    }
    public static ProfileBottomFragment newInstance(int index) {
        ProfileBottomFragment fragment = new ProfileBottomFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }
}