package com.example.android.tourfc;

import android.content.Context;
import android.util.Log;

import com.example.android.tourfc.model.AttractionItem;
import com.example.android.tourfc.model.AttractionResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.EntityUtils;

public final class Utils {
    static Gson gson = new Gson();
    static String surl = "http://starlord.hackerearth.com/kickstarter";

    public static int resolveStringToResId(Context context, CharSequence text) {
        if (context.getString(R.string.top_activities).contentEquals(text)) return R.string.top_activities;
        else if (context.getString(R.string.top_bars_nightlife).contentEquals(text)) return R.string.top_bars_nightlife;
        else if (context.getString(R.string.top_breweries).contentEquals(text)) return R.string.top_breweries;
        else if (context.getString(R.string.top_restaurants).contentEquals(text)) return R.string.top_restaurants;
        else return 0;
    }

    public static List<AttractionItem> getItems(Object... params) throws JSONException {
       // Context context = (Context) params[1];
        //DataBaseUser db = new DataBaseUser(context);
        //DBUserInfo user = db.getUsers();
        String url = surl ;
        String response = getServiceResponse(url);
        JSONArray jArray = null;
        jArray = new JSONArray(response);
        List<AttractionItem> list = new ArrayList<AttractionItem>();
        for(int i = 0; i<101; i++) {
            JSONObject jsObjc = (JSONObject) jArray.get(i);
            AttractionItem attraction = new AttractionItem(jsObjc.getInt("s.no"),jsObjc.getLong("amt.pledged"),jsObjc.getString("blurb"),jsObjc.getString("by"),jsObjc.getString("country"),jsObjc.getString("currency"),jsObjc.getString("end.time"),jsObjc.getString("location"),jsObjc.getInt("percentage.funded"),jsObjc.getString("num.backers"),jsObjc.getString("state"),jsObjc.getString("title"),jsObjc.getString("type"),jsObjc.getString("url"));
            list.add(attraction);
        }

        return  list;
    }

    public static String getServiceResponse(String... params) {
        String serviceResponse = null;
        try {
            HttpGet httpGet = new HttpGet(params[0]);

            httpGet.setHeader("Accept", "application/json");
            httpGet.setHeader("Content-type", "application/json");
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            serviceResponse = EntityUtils.toString(httpEntity);
            Log.d("Response", serviceResponse);
        } catch (Exception e) {
            serviceResponse = e.getMessage();
            e.printStackTrace();
            Log.d("Service Error: ", e.getMessage());
        }

        return serviceResponse;
    }
}
