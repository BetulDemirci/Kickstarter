package com.example.android.tourfc.model;

public class AttractionItem {
    public int no;
    public long pledged;
    public String blurb;
    public String by;
    public String country;
    public String currency;
    public String endTime;
    public String location;
    public int funded;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public long getPledged() {
        return pledged;
    }

    public void setPledged(long pledged) {
        this.pledged = pledged;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getFunded() {
        return funded;
    }

    public void setFunded(int funded) {
        this.funded = funded;
    }

    public String getBackers() {
        return backers;
    }

    public void setBackers(String backers) {
        this.backers = backers;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String backers;
    public String state;
    public String title;
    public String type;
    public String url;


    public AttractionItem(int no, long pledged, String blurb, String by, String country, String currency, String endTime, String location, int funded, String backers, String state, String title, String type, String url) {
        this.no = no;
        this.pledged = pledged;
        this.blurb = blurb;
        this.by = by;
        this.country = country;
        this.currency = currency;
        this.endTime = endTime;
        this.location = location;
        this.funded = funded;
        this.backers = backers;
        this.state = state;
        this.title = title;
        this.type = type;
        this.url = url;
    }

    public AttractionItem() {

    }
}
