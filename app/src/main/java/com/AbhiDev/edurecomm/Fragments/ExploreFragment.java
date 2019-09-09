package com.AbhiDev.edurecomm.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.wireout.Activities.FiltersActivity;
import com.wireout.Activities.WishlistActivity;
import com.wireout.R;
import com.wireout.adapters.ExploreAdapter;
import com.wireout.listeners.OnEntitiesReceivedListener;
import com.wireout.models.Tag;
import com.wireout.presenters.ExplorePresenter;
import com.wireout.viewactions.ExploreViewAction;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Rishabh on 2/22/2018.
 */

public class ExploreFragment extends Fragment implements ViewPagerEx.OnPageChangeListener, ExploreViewAction {
    View view;
    ExploreAdapter adapter;
    ExplorePresenter presenter;
    RecyclerView recyclerView;
    DisplayMetrics displayMetrics;
    FloatingActionMenu menuRed;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_explore, container, false);
        this.view = view;
        recyclerView = view.findViewById(R.id.explore_recycler_view);
        menuRed=view.findViewById(R.id.menu_redExplore);

        displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        menuRed.setMenuButtonColorNormal(getResources().getColor(R.color.lightpink));
        menuRed.setMenuButtonColorPressed(getResources().getColor(R.color.lightpink));

        final FloatingActionButton programFab1 = new FloatingActionButton(getContext());
        programFab1.setButtonSize(FloatingActionButton.SIZE_MINI);
        programFab1.setLabelText("Filters");
        programFab1.setImageResource(R.drawable.ic_action_edit2);
        menuRed.addMenuButton(programFab1);
        programFab1.setColorNormal(getResources().getColor(R.color.lightpink));
        programFab1.setColorPressed(getResources().getColor(R.color.lightpink));
        programFab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                programFab1.setLabelColors(ContextCompat.getColor(getActivity(), R.color.grey),
                        ContextCompat.getColor(getActivity(), R.color.light_grey),
                        ContextCompat.getColor(getActivity(), R.color.white_transparent));
                programFab1.setLabelTextColor(ContextCompat.getColor(getActivity(), R.color.black));

                startActivity(new Intent(getActivity(), FiltersActivity.class));

                menuRed.close(true);

            }
        });
        final FloatingActionButton programFab2 = new FloatingActionButton(getContext());
        programFab2.setButtonSize(FloatingActionButton.SIZE_MINI);
        programFab2.setLabelText("Wishlist");
        programFab2.setImageResource(R.drawable.ic_action_heart2);
        menuRed.addMenuButton(programFab2);
        programFab2.setColorNormal(getResources().getColor(R.color.lightpink));
        programFab2.setColorPressed(getResources().getColor(R.color.lightpink));
        programFab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                programFab2.setLabelColors(ContextCompat.getColor(getActivity(), R.color.grey),
                        ContextCompat.getColor(getActivity(), R.color.light_grey),
                        ContextCompat.getColor(getActivity(), R.color.white_transparent));
                programFab2.setLabelTextColor(ContextCompat.getColor(getActivity(), R.color.black));

                startActivity(new Intent(getActivity(), WishlistActivity.class));

                menuRed.close(true);

            }
        });

        presenter = new ExplorePresenter(new OnEntitiesReceivedListener<Tag>(getContext()) {
            public void onReceived(List<Tag> entities){
                setExploreRecyclerView(entities);
            }
        },this);
        presenter.loadTags(new HashMap<String, String>());
        return view;
    }

    public static ExploreFragment newInstance(int index) {
        ExploreFragment fragment = new ExploreFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }
    public void setExploreRecyclerView(List<Tag> entities){

        recyclerView.setHasFixedSize(true);
        recyclerView.removeAllViews();
        recyclerView.removeAllViewsInLayout();
        LinearLayoutManager mStaggeredVerticalLayoutManager = new LinearLayoutManager(getContext()); // (int spanCount, int orientation)
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        if(getContext()!=null) {
            adapter =
                    new ExploreAdapter(getContext(), entities, displayMetrics);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

            adapter.notifyDataSetChanged();
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
    public static ExploreFragment newInstance() {
        return new ExploreFragment();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showNetworkError(String message) {

    }
}
