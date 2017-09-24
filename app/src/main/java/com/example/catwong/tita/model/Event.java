package com.example.catwong.tita.model;

/**
 * Created by LENOVO on 2017/9/23.
 */

public class Event {
    private String mtitle;
    private String mStartTime;
    private String mEndTime;
    private String mLocation;
    private String mGps;
    private String mDescription;
    private String mImageUrl;
    private String mDocLink;
    private String mHomepageLink;
    private String mType;

    public Event() {

    }

    public Event(String title, String startTime, String endTime, String location, String gps, String description, String imageURL, String docLink, String homepageLink, String type) {
        mtitle = title;
        mStartTime = startTime;
        mEndTime = endTime;
        mLocation = location;
        mGps = gps;
        mDescription = description;
        mImageUrl = imageURL;
        mDocLink = docLink;
        mHomepageLink = homepageLink;
        mType = type;

    }

    public String getTitle() {
        return mtitle;
    }

    public void setTitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public void setStartTime(String mStartTime) {
        this.mStartTime = mStartTime;
    }

    public String getEndTime() {
        return mEndTime;
    }

    public void setEndTime(String mEndTime) {
        this.mEndTime = mEndTime;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getGps() {
        return mGps;
    }

    public void setGps(String mGps) {
        this.mGps = mGps;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getDocLink() {
        return mDocLink;
    }

    public void setDocLink(String mDocLink) {
        this.mDocLink = mDocLink;
    }

    public String getHomepageLink() {
        return mHomepageLink;
    }

    public void setHomepageLink(String mHomepageLink) {
        this.mHomepageLink = mHomepageLink;
    }

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }
}
