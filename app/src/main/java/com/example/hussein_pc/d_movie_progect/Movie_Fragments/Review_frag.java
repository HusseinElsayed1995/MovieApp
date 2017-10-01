package com.example.hussein_pc.d_movie_progect.Movie_Fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hussein_pc.d_movie_progect.Api_connections.Request_Jason;
import com.example.hussein_pc.d_movie_progect.Movie_Modules.Review_Data;
import com.example.hussein_pc.d_movie_progect.Movie_adapters.Review_Adapter;
import com.example.hussein_pc.d_movie_progect.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dody-PC on 22-Oct-16.
 */
public class Review_frag extends Fragment {
    RecyclerView recyclerView;
    public ArrayList<Review_Data> list = new ArrayList<>();
    public String id;
    Review_Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_review, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView_review);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Review_Adapter(getActivity(), list);
        Bundle bundle = getArguments();
        id = bundle.getString("id");
        new GetData().execute();
        return v;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public class GetData extends AsyncTask<String, Void, Void> {
        ProgressDialog progress;
        JSONArray jsonArray = null;
        Review_Data data2;
        public static final String name = "results";
        public static final String Review_Author = "author";
        public static final String Review_Content = "content";

        // Context context;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(getContext());
            progress.setMessage("loading....");
            progress.setCancelable(false);
            progress.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            Request_Jason request_jason = new Request_Jason();
            String jason = request_jason.makeServiceCall("http://api.themoviedb.org/3/movie/" + id + "/reviews?api_key=d075467a2dddab8f155d26eb0d5596ed");
            Log.d("Respone: >", jason);

            if (jason != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jason);
                    jsonArray = jsonObject.getJSONArray(name);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String author = jsonObject1.getString(Review_Author);
                        String content = jsonObject1.getString(Review_Content);
                        data2 = new Review_Data(author, content);
                        list.add(data2);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {

                Log.e("serviceHandeler", "couldent get any context_data from URL");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (progress.isShowing()) {
                progress.dismiss();
            }
            if (list != null) {
                //context??
                adapter = new Review_Adapter(getContext(), list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } else {
                System.out.println("array is empty");
            }
        }
    }

}
