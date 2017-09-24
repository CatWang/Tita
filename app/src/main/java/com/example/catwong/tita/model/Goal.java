package com.example.catwong.tita.model;

import java.security.interfaces.DSAKey;
import java.util.Date;

/**
 * Created by LENOVO on 2017/9/23.
 */

public class Goal {


    private String mDescription;

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    private String mLocation;
    private String mRepeatDay;
    private Date mStarttime;
    private long goalID;

    public Date getStarttime() {
        return mStarttime;
    }

    public void setStarttime(Date mStarttime) {
        this.mStarttime = mStarttime;
    }

    public Date getEndtime() {
        return mEndtime;
    }

    public void setEndtime(Date mEndtime) {
        this.mEndtime = mEndtime;
    }

    private Date mEndtime;
    private int mUserID;

    public Goal(){

    }

    public Goal(long id, String title, String location, String repeatDay, Date starttime, Date endtime) {
        goalID = id;
        mTitle = title;
        mLocation = location;
        mRepeatDay = repeatDay;
        mStarttime = starttime;
        mEndtime = endtime;
    }


    public long getGoalID() {
        return goalID;
    }

    public void setGoalID(long goalID) {
        this.goalID = goalID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    private String mTitle;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getRepeatDay() {
        return mRepeatDay;
    }

    public void setRepeatDay(String mRepeatDay) {
        this.mRepeatDay = mRepeatDay;
    }


    public int getUserID() {
        return mUserID;
    }

    public void setmUserID(int mUserID) {
        this.mUserID = mUserID;
    }
}
