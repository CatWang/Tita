package com.example.catwong.tita.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.catwong.tita.R;
import com.example.catwong.tita.adapter.SubGoalListAdapter;
import com.example.catwong.tita.model.SubGoal;
import com.example.catwong.tita.util.HttpHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;


/**
 * Created by CatWong on 9/24/17.
 */

public class GoalDailyActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SubGoalListAdapter mSubGoalAdapter;
    private ArrayList<SubGoal> mAllSubGoalList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_daily_list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        init();

        HttpHelper.get(handler, "goal/sub?g=" + getIntent().getStringExtra("id"), true);

        refreshAdapter();
    }

    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == HttpHelper.MSG_SUCCESS) {
                mAllSubGoalList.clear();

                JsonObject jsonObject = (JsonObject) msg.obj;

                JsonArray array = jsonObject.get("goals").getAsJsonArray();
                for(int i = 0; i < array.size(); i++){
                    JsonObject subObject = array.get(i).getAsJsonObject();

                    SubGoal subGoal = new SubGoal();
                    subGoal.setGoalID(subObject.get("id").getAsInt());
                    subGoal.setStartTime(HttpHelper.getDate(subObject.get("start_time").getAsString().replace("T", " ")));
                    subGoal.setEndTime(HttpHelper.getDate(subObject.get("end_time").getAsString().replace("T", " ")));
                    subGoal.setIsChecked(subObject.get("is_checked").getAsString());
                    subGoal.setIsShared(subObject.get("is_shared").getAsString());

                    mAllSubGoalList.add(subGoal);
                }

                refreshAdapter();
            }

            return true;
        }
    });


    private void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.subGoal_daily_recyclerView);
    }

    private void refreshAdapter() {
        mSubGoalAdapter = new SubGoalListAdapter(this.getBaseContext(), mAllSubGoalList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getBaseContext()));
        mRecyclerView.setAdapter(mSubGoalAdapter);
    }

}
