package com.example.android.tourfc.model;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.example.android.tourfc.R;
import com.example.android.tourfc.SQliteHelper;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AttractionRepository {

    @SuppressWarnings({"FieldCanBeLocal", "unused"}) private Context context;


    private List<AttractionCollection> collections;
    @SuppressLint("StaticFieldLeak") private static AttractionRepository attractionRepository;

    public static AttractionRepository getInstance(Context packageContext) {
        if (attractionRepository == null) {
            attractionRepository = new AttractionRepository(packageContext);
        }
        return attractionRepository;
    }

    public AttractionCollection getCollection(int sectionTitle) {
        for (int i = 0; i < collections.size(); i++) {
            if (sectionTitle == collections.get(i).getHeaderTitle()) {
                return collections.get(i);
            }
        }

        return null;
    }

    private AttractionRepository(Context context) {
        this.context = context.getApplicationContext();
        SQliteHelper db = new SQliteHelper(context);
        List<AttractionItem> list = db.getAttraction();

        collections = new ArrayList<>();

        // Build collection
        AttractionCollection activity = buildActivityCollection(list);
        collections.add(activity);

        AttractionCollection middle = buildMiddleCollection(list);
        collections.add(middle);

        AttractionCollection quarter = buildQuarterCollection(list);
        collections.add(quarter);

        AttractionCollection theLast = buildtheLastCollection(list);
        collections.add(theLast);
    }

    public List<AttractionCollection> getCollections() {
        return collections;
    }

    @VisibleForTesting
    static AttractionCollection buildActivityCollection(List<AttractionItem> list) {
        List<Attraction> attractions = new ArrayList<>();
        int[] myImageList = new int[]{R.drawable.horsetooth_mountain_park,R.drawable.whitewater_rafting,R.drawable.flower_trail_garden,R.drawable.horsetooth_reservoir,R.drawable.city_park,R.drawable.rio_grande,R.drawable.new_belgium, R.drawable.odell_brewing,R.drawable.odell_brewing, R.drawable.anheuser_busch, R.drawable.social};
        Random rand = new Random();
        for(int i = 0; i<25; i++) {
            int randomNum = rand.nextInt((10 - 0) + 1) + 0;
                attractions.add(new Attraction(
                        myImageList[randomNum],
                        list.get(i).title,
                        list.get(i).by,
                        list.get(i).blurb,
                        list.get(i).location,
                        (int)list.get(i).pledged,
                        list.get(i).backers,
                        list.get(i).endTime,
                        list.get(i).url
                        )
                );
        }

        return new AttractionCollection(R.string.top_activities, attractions);
    }

    @VisibleForTesting
    static AttractionCollection buildMiddleCollection(List<AttractionItem> list) {
        List<Attraction> attractions = new ArrayList<>();
        int[] myImageList = new int[]{R.drawable.horsetooth_mountain_park,R.drawable.whitewater_rafting,R.drawable.flower_trail_garden,R.drawable.horsetooth_reservoir,R.drawable.city_park,R.drawable.rio_grande,R.drawable.new_belgium, R.drawable.odell_brewing,R.drawable.odell_brewing, R.drawable.anheuser_busch, R.drawable.social};
        Random rand = new Random();
        for(int i = 25; i<50; i++) {
            int randomNum = rand.nextInt((10 - 0) + 1) + 0;
            attractions.add(new Attraction(
                            myImageList[randomNum],
                            list.get(i).title,
                            list.get(i).by,
                            list.get(i).blurb,
                            list.get(i).location,
                            (int)list.get(i).pledged,
                            list.get(i).backers,
                            list.get(i).endTime,
                            list.get(i).url
                    )
            );
        }

        return new AttractionCollection(R.string.top_restaurants, attractions);
    }

    @VisibleForTesting
    static AttractionCollection buildQuarterCollection(List<AttractionItem> list) {
        List<Attraction> attractions = new ArrayList<>();
        int[] myImageList = new int[]{R.drawable.horsetooth_mountain_park,R.drawable.whitewater_rafting,R.drawable.flower_trail_garden,R.drawable.horsetooth_reservoir,R.drawable.city_park,R.drawable.rio_grande,R.drawable.new_belgium, R.drawable.odell_brewing,R.drawable.odell_brewing, R.drawable.anheuser_busch, R.drawable.social};
        Random rand = new Random();
        for(int i = 50; i<75; i++) {
            int randomNum = rand.nextInt((10 - 0) + 1) + 0;
            attractions.add(new Attraction(
                            myImageList[randomNum],
                            list.get(i).title,
                            list.get(i).by,
                            list.get(i).blurb,
                            list.get(i).location,
                            (int)list.get(i).pledged,
                            list.get(i).backers,
                            list.get(i).endTime,
                            list.get(i).url
                    )
            );
        }

        return new AttractionCollection(R.string.top_breweries, attractions);
    }

    @VisibleForTesting
    static AttractionCollection buildtheLastCollection(List<AttractionItem> list) {
        List<Attraction> attractions = new ArrayList<>();
        int[] myImageList = new int[]{R.drawable.horsetooth_mountain_park,R.drawable.whitewater_rafting,R.drawable.flower_trail_garden,R.drawable.horsetooth_reservoir,R.drawable.city_park,R.drawable.rio_grande,R.drawable.new_belgium, R.drawable.odell_brewing,R.drawable.odell_brewing, R.drawable.anheuser_busch, R.drawable.social};
        Random rand = new Random();
        for(int i = 75; i<101; i++) {
            int randomNum = rand.nextInt((10 - 0) + 1) + 0;
            attractions.add(new Attraction(
                            myImageList[randomNum],
                            list.get(i).title,
                            list.get(i).by,
                            list.get(i).blurb,
                            list.get(i).location,
                            (int)list.get(i).pledged,
                            list.get(i).backers,
                            list.get(i).endTime,
                            list.get(i).url
                    )
            );
        }

        return new AttractionCollection(R.string.top_bars_nightlife, attractions);
    }
}
