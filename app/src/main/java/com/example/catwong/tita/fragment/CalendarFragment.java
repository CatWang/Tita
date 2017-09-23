package com.example.catwong.tita.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.catwong.tita.R;
import com.example.catwong.tita.adapter.EventListAdapter;
import com.example.catwong.tita.model.Event;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarFragment extends Fragment implements CompactCalendarView.CompactCalendarViewListener{
    private CompactCalendarView mCompactCalendarView;
    private TextView mYearTextView;
    private TextView mMonTextView;
    private RecyclerView mRecyclerView;
    private EventListAdapter mDairyAdapter;
    private Date mDate;
    private ArrayList<Event> mAllEventList;

    private final static int REQUEST_CODE = 1;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
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

    /**
     * Implement the click function of calendar
     *
     * @param dateClicked
     */
    @Override
    public void onDayClick(Date dateClicked) {
        mYearTextView.setText(new SimpleDateFormat("yyyy").format(new Date(dateClicked.getTime())));
        mMonTextView.setText(new SimpleDateFormat("MMMM").format(new Date(dateClicked.getTime())));
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
//        showDiaryBasedOnDate(firstDayOfNewMonth);
    }

}