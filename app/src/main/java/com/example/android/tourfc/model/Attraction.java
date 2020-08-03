package com.example.android.tourfc.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Attraction implements Parcelable {

    /** Image resource ID for the image file associated with the attraction */
    private int imageResourceId;

    /** Title of the attraction */
    private String titleTextResId;

    /** Brief description of the attraction */
    private String shortDescTextResId;

    /** Long description of the attraction */
    private String longDescTextResId;

    /** Map Query String of attraction */
    private String mapQueryStrId;
    private int pledge;
    private String backer;
    private String endtime;
    private String url;

    /**
     * Create data object that holds all the details of an attraction including an image resource
     * for the attraction
     *
     * @param imageResourceId    an integer value for the image resource ID
     * @param titleTextResId     a String value for the name of the attraction
     * @param shortDescTextResId a String value for a brief description of the attraction
     */
    public Attraction(int imageResourceId, String titleTextResId, String shortDescTextResId,
                      String longDescTextResId, String mapQueryStrId, int pledge, String backer, String endtime, String url) {
        this.imageResourceId = imageResourceId;
        this.titleTextResId = titleTextResId;
        this.shortDescTextResId = shortDescTextResId;
        this.longDescTextResId = longDescTextResId;
        this.mapQueryStrId = mapQueryStrId;
        this.pledge = pledge;
        this.backer = backer;
        this.endtime = endtime;
        this.url = url;
    }

    protected Attraction(Parcel in) {
        imageResourceId = in.readInt();
        titleTextResId = in.readString();
        shortDescTextResId = in.readString();
        longDescTextResId = in.readString();
        mapQueryStrId = in.readString();
        pledge = in.readInt();
        backer = in.readString();
        endtime = in.readString();
        url = in.readString();
    }

    public static final Creator<Attraction> CREATOR = new Creator<Attraction>() {
        @Override
        public Attraction createFromParcel(Parcel in) {
            return new Attraction(in);
        }

        @Override
        public Attraction[] newArray(int size) {
            return new Attraction[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.imageResourceId);
        dest.writeString(this.titleTextResId);
        dest.writeString(this.shortDescTextResId);
        dest.writeString(this.longDescTextResId);
        dest.writeString(this.mapQueryStrId);
        dest.writeInt(this.pledge);
        dest.writeString(this.backer);
        dest.writeString(this.endtime);
        dest.writeString(this.url);

    }


    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getTitle() {
        return titleTextResId;
    }

    public String getShortDesc() {
        return shortDescTextResId;
    }

    public String getLongDesc() {
        return longDescTextResId;
    }

    public String getMapQueryStrId() {
        return mapQueryStrId;
    }

    public int getPledge() {
        return pledge;
    }

    public String getBacker() {
        return backer;
    }

    public String getEndtime() {
        return endtime;
    }

    public String getUrl() {
        return url;
    }


}
