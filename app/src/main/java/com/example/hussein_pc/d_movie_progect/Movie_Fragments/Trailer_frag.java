package com.example.hussein_pc.d_movie_progect.Movie_Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.Toast;

import com.example.hussein_pc.d_movie_progect.Api_connections.Request_Jason;
import com.example.hussein_pc.d_movie_progect.Movie_Modules.Trailer_Data;
import com.example.hussein_pc.d_movie_progect.Movie_adapters.Trailer_Adapter;
import com.example.hussein_pc.d_movie_progect.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dody-PC on 22-Oct-16.
 */
public class Trailer_frag extends Fragment{
    RecyclerView recyclerView;
    public ArrayList<Trailer_Data> list=new ArrayList<>();
    Trailer_Adapter adapter;
    public String id;
   // GetData object;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trailer,container,false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView_trailer);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Trailer_Adapter(getActivity(), list);
        Bundle bundle = getArguments();
        id = bundle.getString("id");
        new Trailer_Request().execute();
        recyclerView.addOnItemTouchListener(new Movie_Fragment.RecyclerTouchListener(getContext(), recyclerView, new Movie_Fragment.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                String link = list.get(position).getKey();
                Toast.makeText(getContext(), "" + link, Toast.LENGTH_LONG).show();

                if (link != null) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + link));
                    getActivity().startActivity(i);

                } else {
                    Toast.makeText(getActivity(), "you havn't trailer try again later!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return v;


    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public class Trailer_Request extends AsyncTask<String, Void, Void> {
        ProgressDialog pDialog;
        JSONArray Trailers = null;
        Trailer_Data data;
        public static final String Results = "results";
        public static final String Tralier_Name = "name";
        public static final String Trailer_key = "key";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            Request_Jason request_jason = new Request_Jason();
            String jason = request_jason.makeServiceCall("http://api.themoviedb.org/3/movie/" + id + "/videos?api_key=d075467a2dddab8f155d26eb0d5596ed");
            Log.d("Response: ", "> " + jason);
            if (jason != null) {
                try {
                    JSONObject object = new JSONObject(jason);
                    Trailers = object.getJSONArray(Results);
                    for (int i = 0; i < Trailers.length(); i++) {
                        JSONObject getdata = Trailers.getJSONObject(i);
                        String trailer_name = getdata.getString(Tralier_Name);
                        String trailer_key = getdata.getString(Trailer_key);
                        data = new Trailer_Data(trailer_key,trailer_name);
                        list.add(data);
                        Log.e("show>>>>name", "" + trailer_name);
                        Log.e("show>>>>key",""+trailer_key);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void avoid) {
            super.onPostExecute(avoid);
            if (pDialog.isShowing())
                pDialog.dismiss();

            if (list != null) {
                adapter = new Trailer_Adapter(getContext(), list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } else {
                System.out.println("contactlist is empty");
            }

        }
    }
}
