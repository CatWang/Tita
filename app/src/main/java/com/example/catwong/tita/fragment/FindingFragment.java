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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindingFragment extends Fragment implements FindingListAdapter.MyItemClickListener{
    private RecyclerView mRecyclerView;
    private FindingListAdapter mFindingAdapter;
    private ArrayList<Event> mAllEventList;
    private LayoutInflater mInflater;
    private HomeActivity homeActivity;
    private TextView btnFriend;
    private TextView btnEmail;
    private TextView btnLocal;


    public FindingFragment() {
        // Required empty public constructor
    }

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
//        setContentView(R.layout.activity_calendar);

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
        mAllEventList = new ArrayList<Event> ();
        Date startDate, endDate;

        startDate = Common.dateFormat.getDate("09/24/2017 18:00");
        endDate = Common.dateFormat.getDate("09/24/2017 20:00");
        String title;
        ArrayList<String> keywords = new ArrayList<>();
        keywords.add("meeting");
        keywords.add("machine learning");
        switch (type) {
            case 0:
                title = "Friend";
                mAllEventList.clear();
                for (int i = 0; i < 10; ++i) {
                    Event event = new Event(i, title, startDate, endDate,
                            "1070 RMC", CommonKey.TYPE_PUBLIC);
                    event.setAdded(false);
                    event.setKeywords(keywords);
                    event.setDescription("Machine Learning Meeting");
                    event.setHomepageLink("www.catwangmenma.com");

                    mAllEventList.add(event);
                }
                break;
            case 1:
                title = "Email";
                mAllEventList.clear();
                for (int i = 0; i < 10; ++i) {
                    Event event = new Event(i, title, startDate, endDate,
                            "1070 RMC", CommonKey.TYPE_PUBLIC);
                    event.setAdded(false);
                    event.setKeywords(keywords);
                    event.setDescription("Machine Learning Meeting");
                    event.setHomepageLink("www.catwangmenma.com");

                    mAllEventList.add(event);
                }
                break;
            case 2:
                title = "Local";
                mAllEventList.clear();
                for (int i = 0; i < 10; ++i) {
                    Event event = new Event(i, title, startDate, endDate,
                            "1070 RMC", CommonKey.TYPE_PUBLIC);
                    event.setAdded(false);
                    event.setKeywords(keywords);
                    event.setDescription("Machine Learning Meeting");
                    event.setHomepageLink("www.catwangmenma.com");

                    mAllEventList.add(event);
                }
                break;
            default:
                title = "Haha";
        }
        Collections.reverse(mAllEventList);
    }

    private void setAdapter() {

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
        //mFindingAdapter.setOnItemClickListener(this);
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
        intent.putExtra(CommonKey.EVENT, event);
        getContext().startActivity(intent);
    }
}
