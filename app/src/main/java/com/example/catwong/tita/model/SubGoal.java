package com.example.catwong.tita.model;

import java.util.Date;

/**
 * Created by LENOVO on 2017/9/23.
 */

public class SubGoal {


    private int mUserID;
    private int mGoalID;
    private Date mStartTime;
    private Date mEndTime;
    private String mIsShared;
    private String mSharedTime;
    private String mIsChecked;
    private String mCheckedTime;

    public SubGoal() {

    }

    public SubGoal(int userID, int goalID, Date startTime, Date endTime, String isShared, String sharedTime, String isChecked, String checkTime) {
        mUserID = userID;
        mGoalID = goalID;
        mStartTime = startTime;
        mEndTime = endTime;
        mIsShared = isShared;
        mSharedTime = sharedTime;
        mIsChecked = isChecked;
        mCheckedTime = checkTime;
    }

    public int getUserID() {
        return mUserID;
    }

    public void setUserID(int mUserID) {
        this.mUserID = mUserID;
    }

    public int getGoalID() {
        return mGoalID;
    }

    public void setGoalID(int mGoalID) {
        this.mGoalID = mGoalID;
    }

    public Date getStartTime() {
        return mStartTime;
    }

    public void setStartTime(Date mStartTime) {
        this.mStartTime = mStartTime;
    }

    public Date getEndTime() {
        return mEndTime;
    }

    public void setEndTime(Date mEndTime) {
        this.mEndTime = mEndTime;
    }

    public String getIsShared() {
        return mIsShared;
    }

    public void setIsShared(String mIsShared) {
        this.mIsShared = mIsShared;
    }

    public String getSharedTime() {
        return mSharedTime;
    }

    public void setSharedTime(String mSharedTime) {
        this.mSharedTime = mSharedTime;
    }

    public String getIsChecked() {
        return mIsChecked;
    }

    public void setIsChecked(String mIsChecked) {
        this.mIsChecked = mIsChecked;
    }

    public String getCheckedTime() {
        return mCheckedTime;
    }

    public void setCheckedTime(String mCheckedTime) {
        this.mCheckedTime = mCheckedTime;
    }
}
