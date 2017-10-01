package com.example.hussein_pc.d_movie_progect.Api_connections;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.hussein_pc.d_movie_progect.Movie_Modules.Detail_Data;
import com.example.hussein_pc.d_movie_progect.Movie_adapters.Detail_Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Hussien_pc on 10/1/2016.
 */
public class GetData extends AsyncTask<String, Void, Void> {
    ProgressDialog progress;
    Detail_Adapter adapter11;
    Context context;
    JSONArray jsonArray = null;
    private static String URL;
    Detail_Data data2;
    public ArrayList<Detail_Data> li = new ArrayList<Detail_Data>();

    public static final String name = "results";
    public static final String Poster = "poster_path";
    public static final String Title = "title";
    public static final String Date = "release_date";
    public static final String Id = "id";
    public static final String Overview = "overview";
    public static final String Rate = "popularity";


    RecyclerView recyclerView;

    public GetData(Context context, RecyclerView recyclerView,String URL) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.URL=URL;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = new ProgressDialog(context);
        progress.setMessage("loading....");
        progress.setCancelable(false);
        progress.show();
    }

    @Override
    protected Void doInBackground(String... params) {
        Request_Jason request_jason = new Request_Jason();
        String jason = request_jason.makeServiceCall(URL);
        Log.d("Respone: >", jason);

        if (jason != null) {
            try {
                JSONObject jsonObject = new JSONObject(jason);
                jsonArray = jsonObject.getJSONArray(name);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String title2 = jsonObject1.getString(Title);
                    String poster = jsonObject1.getString(Poster);
                    String date = jsonObject1.getString(Date);
                    String id = jsonObject1.getString(Id);
                    String overview = jsonObject1.getString(Overview);
                    String rate = jsonObject1.getString(Rate);
                    ////////////////////////////////////////////
                    data2 = new Detail_Data(title2, poster, date, rate, id, overview);
                    Log.e("Show>>>>>>>>>>",""+data2);
                    li.add(data2);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {

            Log.e("serviceHandeler", "couldent get any Detail_Data from URL");
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (progress.isShowing()) {
            progress.dismiss();
        }
        if (li != null) {
            adapter11 = new Detail_Adapter(context, li);
            recyclerView.setAdapter(adapter11);
            adapter11.notifyDataSetChanged();
        } else {
            System.out.println("array is empty");
        }
    }
}

