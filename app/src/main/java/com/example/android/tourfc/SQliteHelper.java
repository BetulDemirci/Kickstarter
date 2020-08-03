package com.example.android.tourfc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.android.tourfc.model.AttractionItem;

import java.util.ArrayList;
import java.util.List;

public class SQliteHelper extends SQLiteOpenHelper {
    private static final String DataBase = "DBAttraction";
    private static final String table_Attraction = "Attractions";
    private static final String attraction_NO = "no";
    private static final String attractionPLEDGED = "pledged";
    private static final String attractionBLURB = "blurb";
    private static final String attractionBY = "by";
    private static final String attractionCOUNTRY = "country";
    private static final String attractionCURRENCY = "currency";
    private static final String attractionENDTIME = "endTime";
    private static final String attractionLOCATION = "location";
    private static final String attractionFUNDED = "funded";
    private static final String attractionBACKERS = "backers";
    private static final String attractionSTATE = "state";
    private static final String attractionTITLE = "title";
    private static final String attractionTYPE = "type";
    private static final String attractionURL = "url";

    private static final String CREATE_ATTRACTION_TABLE = "CREATE TABLE IF NOT EXISTS "
            + table_Attraction+" ("
            + attraction_NO + " INTEGER PRIMARY KEY, "
            + attractionPLEDGED+ " INTEGER, "
            + attractionBLURB+" TEXT, "
            + attractionBY+" TEXT, "
            + attractionCOUNTRY+" TEXT, "
            + attractionCURRENCY+" TEXT, "
            + attractionENDTIME+" TEXT, "
            + attractionLOCATION+" TEXT, "
            + attractionFUNDED+" INTEGER, "
            + attractionBACKERS+" TEXT, "
            + attractionSTATE+" TEXT, "
            + attractionTITLE+" TEXT, "
            + attractionTYPE+" TEXT, "
            + attractionURL+" TEXT )";

    public SQliteHelper(Context context) {
        super(context, DataBase, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_ATTRACTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ table_Attraction);
        onCreate(db);

    }
    public void addAttraction(AttractionItem attraction){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv=new ContentValues();
            cv.put(attraction_NO,attraction.no);
        cv.put(attractionPLEDGED,attraction.pledged);
        cv.put(attractionBLURB,attraction.blurb);
        cv.put(attractionBY,attraction.by);
        cv.put(attractionCOUNTRY,attraction.country);
        cv.put(attractionCURRENCY,attraction.currency);
        cv.put(attractionENDTIME,attraction.endTime);
        cv.put(attractionLOCATION,attraction.location);
        cv.put(attractionFUNDED,attraction.funded);
        cv.put(attractionBACKERS,attraction.backers);
        cv.put(attractionSTATE,attraction.state);
        cv.put(attractionTITLE,attraction.title);
        cv.put(attractionTYPE,attraction.type);
        cv.put(attractionURL,attraction.url);
            db.insertWithOnConflict(table_Attraction,null,cv, SQLiteDatabase.CONFLICT_IGNORE);
            db.close();

    }

    public List<AttractionItem> getAttraction(){
        List<AttractionItem> attractions = new ArrayList<>();
        String query = "SELECT * FROM "+table_Attraction;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        AttractionItem attraction = null;
        if(cursor.moveToFirst()){
            do{
                attraction = new AttractionItem();
                attraction.setNo(Integer.parseInt(cursor.getString(0)));
                attraction.setPledged(Integer.parseInt(cursor.getString(1)));
                attraction.setBlurb(cursor.getString(2));
                attraction.setBy(cursor.getString(3));
                attraction.setCountry(cursor.getString(4));
                attraction.setCurrency(cursor.getString(5));
                attraction.setEndTime(cursor.getString(6));
                attraction.setLocation(cursor.getString(7));
                attraction.setFunded(Integer.parseInt(cursor.getString(8)));
                attraction.setBackers(cursor.getString(9));
                attraction.setState(cursor.getString(10));
                attraction.setTitle(cursor.getString(11));
                attraction.setType(cursor.getString(12));
                attraction.setUrl(cursor.getString(13));
                attractions.add(attraction);
            }while(cursor.moveToNext());
        }
        return attractions;
    }
}
