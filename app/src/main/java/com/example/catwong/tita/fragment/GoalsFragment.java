package com.example.catwong.tita.fragment;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.catwong.tita.R;
import com.example.catwong.tita.activity.GoalDailyActivity;
import com.example.catwong.tita.activity.HomeActivity;
import com.example.catwong.tita.activity.RegisterActivity;
import com.example.catwong.tita.adapter.EventListAdapter;
import com.example.catwong.tita.adapter.GoalListAdapter;
import com.example.catwong.tita.common.CommonKey;
import com.example.catwong.tita.model.Event;
import com.example.catwong.tita.model.Goal;
import com.example.catwong.tita.util.Common;
import com.example.catwong.tita.util.HttpHelper;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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
    private ArrayList<Goal> mAllGoalList = new ArrayList<>();
    private LayoutInflater mInflater;
    private HomeActivity homeActivity;
    private ImageView imgAddGaol;

    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == HttpHelper.MSG_SUCCESS) {
                mAllGoalList.clear();

                JsonObject jsonObject = (JsonObject) msg.obj;

                JsonArray array = jsonObject.get("goals").getAsJsonArray();
                for(int i = 0; i < array.size(); i++){
                    JsonObject subObject = array.get(i).getAsJsonObject();

                    Goal goal = new Goal();
                    goal.setGoalID(subObject.get("id").getAsInt());
                    goal.setTitle(subObject.get("title").getAsString());
                    goal.setDescription(subObject.get("description").getAsString());
                    goal.setLocation(subObject.get("location").getAsString());
                    goal.setRepeatDay(subObject.get("repeat_day").getAsString());
                    goal.setStartTime(HttpHelper.getTime(subObject.get("start_time").getAsString().replace('T', ' ')));
                    goal.setEndtime(HttpHelper.getTime(subObject.get("end_time").getAsString().replace('T', ' ')));

                    mAllGoalList.add(goal);
                }

                refreshAdapter();
            }

            return true;
        }
    });

    private final Handler createHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == HttpHelper.MSG_SUCCESS) {
                setAdapter();
            }

            return true;
        }
    });


    public GoalsFragment() {}

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
    }

    /**
     * Initial all the widgets
     */
    private void init() {

        mRecyclerView = (RecyclerView) this.getView().findViewById(R.id.goal_main_recyclerView);
        imgAddGaol = (ImageView) getView().findViewById(R.id.add_goal);

        imgAddGaol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alter = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();

                View v_iew=inflater.inflate(R.layout.layout_add_goal, null);
                alter.setView(v_iew);

                alter.setTitle("Add Goal");
                final TextView txtTile = (TextView) v_iew.findViewById(R.id.add_goal_title);
                final TextView txtLocation = (TextView) v_iew.findViewById(R.id.add_goal_location);
                final TextView txtStartTime = (TextView) v_iew.findViewById(R.id.add_goal_start_time);
                final TextView txtEndTime = (TextView) v_iew.findViewById(R.id.add_goal_end_time);
                final CheckBox chkMon = (CheckBox) v_iew.findViewById(R.id.goal_repeat_mon);
                final CheckBox chkTue = (CheckBox) v_iew.findViewById(R.id.goal_repeat_tue);
                final CheckBox chkWed = (CheckBox) v_iew.findViewById(R.id.goal_repeat_wed);
                final CheckBox chkTur = (CheckBox) v_iew.findViewById(R.id.goal_repeat_thu);
                final CheckBox chkFir = (CheckBox) v_iew.findViewById(R.id.goal_repeat_fir);
                final CheckBox chkSat = (CheckBox) v_iew.findViewById(R.id.goal_repeat_sat);
                final CheckBox chkSun = (CheckBox) v_iew.findViewById(R.id.goal_repeat_sun);

                txtStartTime.setText("14:20:00");
                txtEndTime.setText("20:20:00");

                alter.setPositiveButton("Create",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String body = "description=no&title=" + txtTile.getText() + "&location=" + txtLocation.getText()
                                        + "&start_time=" + txtStartTime.getText() + "&end_time=" + txtEndTime.getText() + "&repeat_day=";
                                if (chkMon.isChecked()) body += "M";
                                if (chkTue.isChecked()) body += "T";
                                if (chkWed.isChecked()) body += "W";
                                if (chkTur.isChecked()) body += "U";
                                if (chkFir.isChecked()) body += "F";
                                if (chkSat.isChecked()) body += "A";
                                if (chkSun.isChecked()) body += "N";

                                HttpHelper.post(createHandler, "goal/create", body, true);
                            }
                        })
                        .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("Person Dialog", "cancel");
                            }
                        });
                alter.show();
            }
        });

    }
    private void setAdapter() {
        Calendar c = Calendar.getInstance();
        String today = HttpHelper.getDateString(c);

        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);
        String tomorrow = HttpHelper.getDateString(c);

        HttpHelper.get(handler, "goal/all", true);

        refreshAdapter();
    }

    private void refreshAdapter() {
        mGoalAdapter = new GoalListAdapter(homeActivity.getBaseContext(), mAllGoalList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(homeActivity.getBaseContext()));
        mRecyclerView.setAdapter(mGoalAdapter);

        mGoalAdapter.setOnItemClickListener(this);
    }

    /**
     * Set the listener of calendar and adapter
     */

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
        Intent intent = new Intent(homeActivity, GoalDailyActivity.class);
        intent.putExtra("id", "" + goal.getGoalID());
        startActivity(intent);
    }
}
