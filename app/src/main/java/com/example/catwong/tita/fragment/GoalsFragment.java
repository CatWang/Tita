package com.example.catwong.tita.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.catwong.tita.R;
import com.example.catwong.tita.activity.HomeActivity;
import com.example.catwong.tita.activity.RegisterActivity;
import com.example.catwong.tita.adapter.EventListAdapter;
import com.example.catwong.tita.adapter.GoalListAdapter;
import com.example.catwong.tita.model.Event;
import com.example.catwong.tita.model.Goal;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoalsFragment extends Fragment implements GoalListAdapter.MyItemClickListener{
    private RecyclerView mRecyclerView;
    private GoalListAdapter mGoalAdapter;
    private ArrayList<Goal> mAllGoalList;
    private LayoutInflater mInflater;
    private HomeActivity homeActivity;

    public GoalsFragment(){

    }

    @SuppressLint("ValidFragment")
    public  GoalsFragment(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }


    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        return inflater.inflate(R.layout.fragment_goals, container, false);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_calendar);

    }

    /**
     * Override the onStart method and set the year and month of the calendar
     */
    @Override
    public void onStart() {
        super.onStart();
        init();
        setAdapter();
//        setListener();
    }

    /**
     * Initial all the widgets
     */
    private void init() {

        mRecyclerView = (RecyclerView) this.getView().findViewById(R.id.goal_main_recyclerView);

    }
    private void setAdapter() {
        mAllGoalList = new ArrayList<Goal> ();
        Goal event = new Goal("GOAL", "Description", "123", "1345", "5:00", "6:00", 123);
        for (int i = 0; i < 10; ++i) {
            mAllGoalList.add(event);
        }
        Collections.reverse(mAllGoalList);
        mGoalAdapter = new GoalListAdapter(homeActivity.getBaseContext(), mAllGoalList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(homeActivity.getBaseContext()));
        mRecyclerView.setAdapter(mGoalAdapter);
    }

    /**
     * Set the listener of calendar and adapter
     */
    private void setListener() {
        mGoalAdapter.setOnItemClickListener(this);
    }

    /**
     * Implement the onItemClick. When the users click the item of diary, it will go to another
     * activity to show the details of this diary
     *
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {
        Goal goal = mAllGoalList.get(position);
//        Long id = event.getId();
        Intent intent = new Intent(homeActivity, RegisterActivity.class);
//        intent.putExtra(Constants.EXTRA_DIARY, id);
//        startActivityForResult(intent, REQUEST_CODE);
        startActivity(intent);
    }
}
