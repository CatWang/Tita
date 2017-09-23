package com.example.catwong.tita.model;

/**
 * Created by LENOVO on 2017/9/23.
 */

public class SubGoal {


    private int mUserID;
    private int mGoalID;
    private String mStartTime;
    private String mEndTime;
    private String mIsShared;
    private String mSharedTime;
    private String mIsChecked;
    private String mCheckedTime;

    public SubGoal() {

    }

    public SubGoal(int userID, int goalID, String startTime, String endTime, String isShared, String sharedTime, String isChecked, String checkTime) {
        mUserID = userID;
        mGoalID = goalID;
        mStartTime = startTime;
        mEndTime = endTime;
        mIsShared = isShared;
        mSharedTime = sharedTime;
        mIsChecked = isChecked;
        mCheckedTime = checkTime;
    }

    public int getmUserID() {
        return mUserID;
    }

    public void setmUserID(int mUserID) {
        this.mUserID = mUserID;
    }

    public int getmGoalID() {
        return mGoalID;
    }

    public void setmGoalID(int mGoalID) {
        this.mGoalID = mGoalID;
    }

    public String getmStartTime() {
        return mStartTime;
    }

    public void setmStartTime(String mStartTime) {
        this.mStartTime = mStartTime;
    }

    public String getmEndTime() {
        return mEndTime;
    }

    public void setmEndTime(String mEndTime) {
        this.mEndTime = mEndTime;
    }

    public String getmIsShared() {
        return mIsShared;
    }

    public void setmIsShared(String mIsShared) {
        this.mIsShared = mIsShared;
    }

    public String getmSharedTime() {
        return mSharedTime;
    }

    public void setmSharedTime(String mSharedTime) {
        this.mSharedTime = mSharedTime;
    }

    public String getmIsChecked() {
        return mIsChecked;
    }

    public void setmIsChecked(String mIsChecked) {
        this.mIsChecked = mIsChecked;
    }

    public String getmCheckedTime() {
        return mCheckedTime;
    }

    public void setmCheckedTime(String mCheckedTime) {
        this.mCheckedTime = mCheckedTime;
    }
}
