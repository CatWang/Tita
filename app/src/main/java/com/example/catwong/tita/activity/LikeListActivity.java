package com.example.catwong.tita.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;

import com.example.catwong.tita.R;
import com.example.catwong.tita.adapter.EventListAdapter;
import com.example.catwong.tita.common.CommonKey;
import com.example.catwong.tita.model.Event;
import com.example.catwong.tita.util.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by CatWong on 9/23/17.
 */

public class LikeListActivity extends AppCompatActivity implements EventListAdapter.MyItemClickListener{
    private RecyclerView mRecyclerView;
    private EventListAdapter mEventAdapter;
    private ArrayList<Event> mAllEventList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);
    }
    @Override
    protected void onStart() {
        super.onStart();
        init();
        setAdapter();
        setListener();
    }

    private void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.like_main_recyclerView);
    }

    private void setAdapter() {
//        mAllEventList = (ArrayList<Event>) Event.listAll(Event.class);
        mAllEventList = new ArrayList<Event> ();

        Date startDate, endDate;

        startDate = Common.dateFormat.getDate("09/24/2017 18:00");
        endDate = Common.dateFormat.getDate("09/24/2017 20:00");

        ArrayList<String> keywords = new ArrayList<>();
        keywords.add("meeting");
        keywords.add("machine learning");

        for (int i = 0; i < 10; ++i) {
            Event event = new Event(i, "Meeting", startDate, endDate,
                    "1070 RMC", CommonKey.TYPE_PUBLIC);
            event.setAdded(true);
            event.setKeywords(keywords);
            event.setDescription("Machine Learning Meeting");
            event.setHomepageLink("www.catwangmenma.com");

            mAllEventList.add(event);
        }
        Collections.reverse(mAllEventList);
        mEventAdapter = new EventListAdapter(this.getBaseContext(), mAllEventList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getBaseContext()));
        mRecyclerView.setAdapter(mEventAdapter);
    }

    /**
     * Set the listener of calendar and adapter
     */
    private void setListener() {
        mEventAdapter.setOnItemClickListener(this);
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
        Intent intent = new Intent(this, EventDetailActivity.class);
        intent.putExtra(CommonKey.EVENT, event);
        startActivity(intent);
    }
}
