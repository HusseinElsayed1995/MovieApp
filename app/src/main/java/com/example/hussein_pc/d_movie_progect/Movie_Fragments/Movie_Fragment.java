package com.example.hussein_pc.d_movie_progect.Movie_Fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.hussein_pc.d_movie_progect.Activites.Detail_Activity;
import com.example.hussein_pc.d_movie_progect.Api_connections.GetData;
import com.example.hussein_pc.d_movie_progect.Film_DataBase.DataBase_Controller;
import com.example.hussein_pc.d_movie_progect.Film_DataBase.DataBase_Helper;
import com.example.hussein_pc.d_movie_progect.Movie_Modules.Detail_Data;
import com.example.hussein_pc.d_movie_progect.Movie_adapters.Detail_Adapter;
import com.example.hussein_pc.d_movie_progect.R;

import java.util.ArrayList;


public class Movie_Fragment extends Fragment {
    RecyclerView recyclerView;
    public ArrayList<Detail_Data> list;
    GetData object;
    String URL;
    Detail_Adapter adapter;
    DataBase_Controller control;
    Detail_Data data;
    boolean fav = false, tap = false;

    public Movie_Fragment(String URL, boolean tap) {
        this.tap = tap;
        if (URL == null) {
            fav = true;
        } else {
            this.URL = URL;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_fragment, container, false);
        list = new ArrayList<Detail_Data>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        control=new DataBase_Controller(getActivity());
        adapter = new Detail_Adapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        //li is the list of gata in Detail_Data class

        if (fav == false) {
            object = new GetData(getActivity(), recyclerView, URL);
            object.execute();
        } else {
            getFromDB();
        }
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //psition is the index of selected item
                if (fav == false) {
                    list = object.li;
                }
                Detail_Data Receved_Data = list.get(position);     //Getting all Data of position from API
                //Distibution  of Data on Variables
                String Receved_id = Receved_Data.getId();
                String Receved_Poster_url = Receved_Data.getLink();
                String Receved_Film_Name = Receved_Data.getName();
                String Receved_Date = Receved_Data.getDate();
                String Receved_Vote = Receved_Data.getRate();
                String Receved_Overview = Receved_Data.getOverview();

                //Greating bundle for Transmitting Data to Detail_Fragment
                Bundle b = new Bundle();
                b.putString("Receved_id", Receved_id);
                b.putString("Receved_Poster_url", Receved_Poster_url);
                b.putString("Receved_Film_Name", Receved_Film_Name);
                b.putString("Receved_Date", Receved_Date);
                b.putString("Receved_Vote", Receved_Vote);
                b.putString("Receved_Overview", Receved_Overview);

                if (tap == false) {
                    Intent intent = new Intent(getActivity(), Detail_Activity.class);
                    intent.putExtra("bundle", b);
                    startActivity(intent);
                } else {

                    Detail_Fragment fs = new Detail_Fragment();
                    fs.setArguments(b);
                    FragmentManager manger1 = getActivity().getSupportFragmentManager();
                    FragmentTransaction transactions = manger1.beginTransaction();
                    transactions.replace(R.id.fragmentContainer2, fs, "detail");
                    transactions.commit();

                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return view;
    }

    public void getFromDB() {
        adapter.clear();
        Cursor c = control.GetAllData();
        int y = c.getColumnCount();
        list.clear();
        if (y != 0) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                String title = c.getString(c.getColumnIndex(DataBase_Helper.Movie_Title));
                String date = c.getString(c.getColumnIndex(DataBase_Helper.Movie_Date));
                String overview = c.getString(c.getColumnIndex(DataBase_Helper.Movie_OVERVIEW));
                String id = c.getString(c.getColumnIndex(DataBase_Helper.MOVIEID));
                String poster = c.getString(c.getColumnIndex(DataBase_Helper.Movie_POSTER));
                String vote = c.getString(c.getColumnIndex(DataBase_Helper.Movie_Votes));
                data = new Detail_Data(title, poster, date, vote, id, overview);
                Log.e("show>>>>>>>>>>>>>",""+id);
                list.add(data);
                c.moveToNext();
            }
            c.close();
            adapter.notifyDataSetChanged();
        }
    }


    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;
        Context c;

        public RecyclerTouchListener(final Context context, final RecyclerView recyclerView,
                                     final ClickListener clickListener) {
            this.c = context;
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    // Toast.makeText(c,"1",Toast.LENGTH_SHORT).show();
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildLayoutPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                //    Toast.makeText(c,"4",Toast.LENGTH_SHORT).show();
                clickListener.onClick(child, rv.getChildLayoutPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }


}
