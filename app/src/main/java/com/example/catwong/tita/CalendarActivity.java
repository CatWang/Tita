package com.example.catwong.tita;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity implements CompactCalendarView.CompactCalendarViewListener{
    private CompactCalendarView mCompactCalendarView;
    private TextView mYearTextView;
    private TextView mMonTextView;
    private RecyclerView mRecyclerView;
    private Date mDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initSystemBar(this);
        init();
    }

    /**
     * Override the onStart method and set the year and month of the calendar
     */
    @Override
    protected void onStart() {
        super.onStart();
        Calendar today = Calendar.getInstance();
        mYearTextView.setText(new SimpleDateFormat("yyyy").format(new Date(today.getTimeInMillis())));
        mMonTextView.setText(new SimpleDateFormat("MMMM").format(new Date(today.getTimeInMillis())));
    }

    /**
     * Initial all the widgets
     */
    private void init() {
        mCompactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        mCompactCalendarView.setUseThreeLetterAbbreviation(true);
        mYearTextView = (TextView) findViewById(R.id.diary_main_year_textview);
        mMonTextView = (TextView) findViewById(R.id.diary_main_month_textview);
        mRecyclerView = (RecyclerView) findViewById(R.id.diary_main_recyclerView);
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

    public static void initSystemBar(Activity activity) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(activity, true);
//        }
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);

        tintManager.setStatusBarTintResource(R.color.colorPrimary);

    }
}