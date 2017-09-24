package com.example.catwong.tita.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.catwong.tita.R;
import com.example.catwong.tita.activity.EventDetailActivity;
import com.example.catwong.tita.activity.HomeActivity;
import com.example.catwong.tita.adapter.EventListAdapter;
import com.example.catwong.tita.adapter.FindingListAdapter;
import com.example.catwong.tita.common.CommonKey;
import com.example.catwong.tita.model.Event;
import com.example.catwong.tita.util.Common;
import com.example.catwong.tita.util.HttpHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindingFragment extends Fragment implements FindingListAdapter.MyItemClickListener{
    private RecyclerView mRecyclerView;
    private FindingListAdapter mFindingAdapter;
    private ArrayList<Event> mAllEventList = new ArrayList<>();
    private LayoutInflater mInflater;
    private HomeActivity homeActivity;
    private TextView btnFriend;
    private TextView btnEmail;
    private TextView btnLocal;

    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == HttpHelper.MSG_SUCCESS) {
                mAllEventList.clear();

                JsonObject jsonObject = (JsonObject) msg.obj;

                JsonArray array = jsonObject.get("events").getAsJsonArray();
                for(int i = 0; i < array.size(); i++){
                    JsonObject subObject = array.get(i).getAsJsonObject();

                    Event event = new Event();
                    event.setEventID(subObject.get("id").getAsLong());
                    event.setTitle(subObject.get("title").getAsString());
                    event.setDescription(subObject.get("description").getAsString());
                    event.setLocation(subObject.get("location").getAsString());
                    event.setGps(subObject.get("gps").getAsString());
                    event.setLocation(subObject.get("location").getAsString());
                    event.setImageUrl(subObject.get("image_url").getAsString() == null? "" : subObject.get("image_url").getAsString());
                    event.setDocLink(subObject.get("doc_link").getAsString());
                    event.setHomepageLink(subObject.get("homepage_link").getAsString());
                    event.setStartTime(HttpHelper.getDate(subObject.get("start_time").getAsString().replace('T', ' ')));
                    event.setEndTime(HttpHelper.getDate(subObject.get("end_time").getAsString().replace('T', ' ')));

                    mAllEventList.add(event);
                }

                setAdapter();
            }

            return true;
        }
    });


    public FindingFragment() { }

    @SuppressLint("ValidFragment")
    public  FindingFragment(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_finding, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Override the onStart method and set the year and month of the calendar
     */
    @Override
    public void onStart() {
        super.onStart();
        init();
        loadList(0);
        setAdapter();
        setListener();
    }
    private void init() {
        btnFriend = (TextView) this.getView().findViewById(R.id.friend_btn);
        btnEmail = (TextView) this.getView().findViewById(R.id.email_btn);
        btnLocal = (TextView) this.getView().findViewById(R.id.local_btn);
        mRecyclerView = (RecyclerView) this.getView().findViewById(R.id.finding_main_recyclerView);

    }

    private void loadList(int type) {
        mAllEventList.clear();

        switch (type) {
            case 0:
                HttpHelper.get(handler, "event/liked", true);

                break;
            case 1:
                HttpHelper.get(handler, "event/source?s=email", true);

                break;
            case 2:
                HttpHelper.get(handler, "event/all", false);

                break;

        }
    }

    private void setAdapter() {
        Collections.reverse(mAllEventList);
        mFindingAdapter = new FindingListAdapter(homeActivity.getBaseContext(), mAllEventList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(homeActivity.getBaseContext()));
        mRecyclerView.setAdapter(mFindingAdapter);
        mFindingAdapter.setOnItemClickListener(this);
    }

    private void setListener() {
        btnFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadList(0);
                setAdapter();
            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadList(1);
                setAdapter();
            }
        });

        btnLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadList(2);
                setAdapter();
            }
        });
    }


    @Override
    public void onItemClick(View view, int position) {
        Event event = mAllEventList.get(position);
        if (event != null) {
            System.out.println("Debug-info" + event.toString());
        } else {
            System.out.println("Debug-info" + " true");
        }
        //Long id = event.getEventID();
        Intent intent = new Intent(getContext(), EventDetailActivity.class);
        intent.putExtra("id", "" + event.getEventID());
        getContext().startActivity(intent);
    }
}
