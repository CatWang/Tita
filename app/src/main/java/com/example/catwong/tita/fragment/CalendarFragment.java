package com.example.catwong.tita.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.catwong.tita.R;
//import com.example.catwong.tita.adapter.EventListAdapter;
import com.example.catwong.tita.activity.EventDetailActivity;
import com.example.catwong.tita.activity.HomeActivity;
import com.example.catwong.tita.activity.RegisterActivity;
import com.example.catwong.tita.adapter.EventListAdapter;
import com.example.catwong.tita.common.CommonKey;
import com.example.catwong.tita.model.Event;
import com.example.catwong.tita.util.Common;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class CalendarFragment extends Fragment implements CompactCalendarView.CompactCalendarViewListener, EventListAdapter.MyItemClickListener{
    private CompactCalendarView mCompactCalendarView;
    private TextView mYearTextView;
    private TextView mMonTextView;
    private RecyclerView mRecyclerView;
    private EventListAdapter mEventAdapter;
    private Date mDate;
    private ArrayList<Event> mAllEventList;
    private LayoutInflater mInflater;
    private HomeActivity homeActivity;

    private final static int REQUEST_CODE = 1;

    public CalendarFragment(){

    }

    @SuppressLint("ValidFragment")
    public  CalendarFragment(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        return inflater.inflate(R.layout.fragment_calendar, container, false);
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
        setListener();
    }

    /**
     * Initial all the widgets
     */
    private void init() {
        mCompactCalendarView = (CompactCalendarView) this.getView().findViewById(R.id.compactcalendar_view);
        mCompactCalendarView.setUseThreeLetterAbbreviation(true);
        mYearTextView = (TextView) this.getView().findViewById(R.id.diary_main_year_textview);
        mMonTextView = (TextView) this.getView().findViewById(R.id.diary_main_month_textview);
        mRecyclerView = (RecyclerView) this.getView().findViewById(R.id.diary_main_recyclerView);

        Calendar today = Calendar.getInstance();
        mYearTextView.setText(new SimpleDateFormat("yyyy").format(new Date(today.getTimeInMillis())));
        mMonTextView.setText(new SimpleDateFormat("MMMM").format(new Date(today.getTimeInMillis())));
    }


    private void setAdapter() {
//        mAllEventList = (ArrayList<Event>) Event.listAll(Event.class);
        mAllEventList = new ArrayList<Event> ();

        Date startDate, endDate;

        startDate = Common.dateFormat.getDate("09/24/2017 18:00");
        endDate = Common.dateFormat.getDate("09/24/2017 20:00");

        for (int i = 0; i < 10; ++i) {
            Event event = new Event(i, "Event", startDate, endDate,
                    "RMC", "123", "description", "123", "doclink", "123", "F");
            mAllEventList.add(event);
        }
        Collections.reverse(mAllEventList);
        mEventAdapter = new EventListAdapter(homeActivity.getBaseContext(), mAllEventList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(homeActivity.getBaseContext()));
        mRecyclerView.setAdapter(mEventAdapter);
    }

    /**
     * Set the listener of calendar and adapter
     */
    private void setListener() {
        mCompactCalendarView.setListener(this);
        mEventAdapter.setOnItemClickListener(this);
    }

    /**
     * Implement the click function of calendar
     *
     * @param dateClicked
     */
    @Override
    public void onDayClick(Date dateClicked) {
        mYearTextView.setText(new SimpleDateFormat("yyyy").format(new Date(dateClicked.getTime())));
        mMonTextView.setText(new SimpleDateFormat("MMMM").format(new Date(dateClicked.getTime())));
//        Toast.makeText(getBaseContext(), "Day was clicked: " + dateClicked, Toast.LENGTH_LONG).show();
        mDate = dateClicked;
//        Log.d(TAG, DAY_CLICK + dateClicked);
//        showDiaryBasedOnDate(dateClicked);
    }

    /**
     * Implement the scroll of calender to change the month and update the month TextView
     *
     * @param firstDayOfNewMonth
     */
    @Override
    public void onMonthScroll(Date firstDayOfNewMonth) {
        mYearTextView.setText(new SimpleDateFormat("yyyy").format(new Date(firstDayOfNewMonth.getTime())));
        mMonTextView.setText(new SimpleDateFormat("MMMM").format(new Date(firstDayOfNewMonth.getTime())));
        showDiaryBasedOnDate(firstDayOfNewMonth);
    }

    /**
     * Show the list of diaries which are written in the selected date
     *
     * @param date
     */
    private void showDiaryBasedOnDate(Date date) {
        ArrayList<Event> selectedEvent = new ArrayList<>();
        for (int i = 0; i < mAllEventList.size(); ++i) {
            Event event = mAllEventList.get(i);
//            if (DateProcess.getDateAsString(event.getStartTime()).equals(new SimpleDateFormat("dd-MM-yyyy").format(date))) {
//                selectedDiary.add(event);
//            }
            selectedEvent.add(event);
        }
        mEventAdapter = new EventListAdapter(homeActivity.getBaseContext(), mAllEventList);
        mEventAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mEventAdapter);
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
        Event event = mAllEventList.get(position);
        //Long id = event.getEventID();
        Intent intent = new Intent(homeActivity, EventDetailActivity.class);
        intent.putExtra(CommonKey.EVENT, event);
        startActivity(intent);
    }

}