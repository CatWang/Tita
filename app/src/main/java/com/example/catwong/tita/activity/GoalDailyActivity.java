package com.example.catwong.tita.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.catwong.tita.R;
import com.example.catwong.tita.adapter.SubGoalListAdapter;
import com.example.catwong.tita.common.CommonKey;
import com.example.catwong.tita.model.SubGoal;
import com.example.catwong.tita.util.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by CatWong on 9/24/17.
 */

public class GoalDailyActivity extends AppCompatActivity implements SubGoalListAdapter.MyItemClickListener{
    private RecyclerView mRecyclerView;
    private SubGoalListAdapter mSubGoalAdapter;
    private ArrayList<SubGoal> mAllSubGoalList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_daily_list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
        setAdapter();
        setListener();
    }

    private void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.subGoal_daily_recyclerView);
    }

    private void setAdapter() {
        mAllSubGoalList = new ArrayList<SubGoal> ();

        Date startTime = Common.dateFormat.getTimeFromString("16:00");
        Date endTime = Common.dateFormat.getTimeFromString("18:00");
        for (int i = 0; i < 10; ++i) {
            SubGoal subGoal = new SubGoal(i, 2 * i, startTime, endTime, "F", "123", "1100011", "123");
            mAllSubGoalList.add(subGoal);
        }
        Collections.reverse(mAllSubGoalList);
        mSubGoalAdapter = new SubGoalListAdapter(this.getBaseContext(), mAllSubGoalList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getBaseContext()));
        mRecyclerView.setAdapter(mSubGoalAdapter);
    }


    /**
     * Set the listener of calendar and adapter
     */
    private void setListener() {
        mSubGoalAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        SubGoal goal = mAllSubGoalList.get(position);
        Intent intent = new Intent(this, RegisterActivity.class);
//        intent.putExtra(CommonKey.GOAL, goal);
        startActivity(intent);
    }
}
