package com.example.hussein_pc.d_movie_progect.Movie_Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hussein_pc.d_movie_progect.Activites.Review_Activit;
import com.example.hussein_pc.d_movie_progect.Activites.Trailer_Activity;
import com.example.hussein_pc.d_movie_progect.Film_DataBase.DataBase_Controller;
import com.example.hussein_pc.d_movie_progect.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Dody-PC on 02-Oct-16.
 */
public class Detail_Fragment extends Fragment {
    private TextView Film_Name, Film_Rate, Film_Date, Film_OverView;
    private Button review, trailer;
    private ImageView Film_Poster;
    String film_name, film_date, film_rate, film_overview, film_poster;
    public String id;
    ImageButton favourite;
    DataBase_Controller controller;
    private String toggel = "0";
    String Title;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_, container, false);
        Film_Name = (TextView) v.findViewById(R.id.filmName);
        Film_Rate = (TextView) v.findViewById(R.id.rate);
        Film_Date = (TextView) v.findViewById(R.id.date);
        Film_OverView = (TextView) v.findViewById(R.id.OverView);
        review = (Button) v.findViewById(R.id.review);
        trailer = (Button) v.findViewById(R.id.trailer);
        Film_Poster = (ImageView) v.findViewById(R.id.poster);
        favourite= (ImageButton) v.findViewById(R.id.star);
        controller = new DataBase_Controller(getActivity());

        Bundle b = getArguments();
        if (b != null) {
            id = b.getString("Receved_id");
            film_poster = b.getString("Receved_Poster_url");
            film_name = b.getString("Receved_Film_Name");
            film_date = b.getString("Receved_Date");
            film_overview = b.getString("Receved_Overview");
            film_rate = b.getString("Receved_Vote");
            String Url = ("https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + film_poster);
            Film_Rate.setText(film_rate);
            Film_Date.setText(film_date);
            Film_OverView.setText(film_overview);
            Film_Name.setText(film_name);
            Picasso.with(getActivity()).load(Url).into(Film_Poster);
        }

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Review_Activit.class);
                Bundle b = new Bundle();
                b.putString("id", id);
                intent.putExtra("bundle", b);
                startActivity(intent);
            }
        });

        trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Trailer_Activity.class);
                Bundle b = new Bundle();
                b.putString("id", id);
                intent.putExtra("bundle", b);
                startActivity(intent);
            }
        });

        SharedPreferences prefs = getActivity().getSharedPreferences("favourit", Context.MODE_PRIVATE);
        toggel = prefs.getString(Title, "0");
        if (toggel.equals("0") || toggel.equals("")) {
            favourite.setImageResource(R.drawable.star_off);
        } else {
            favourite.setImageResource(R.drawable.star_on);
        }
        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getActivity().getSharedPreferences("favourit", Context.MODE_PRIVATE);
                toggel = prefs.getString(Title, "0");
                if (toggel.equals("1")) {
                    controller.Delete(film_name);
                    favourite.setImageResource(R.drawable.star_off);
                    SharedPreferences shared = getActivity().
                            getSharedPreferences("favourit", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = shared.edit();
                    editor.putString(Title, "0");
                    editor.commit();
                } else {

                    long get = controller.Add_Data(film_name, film_date, film_rate, film_overview, film_poster, id);
                    if (get != -1) {
                        toggel = "1";
                        favourite.setImageResource(R.drawable.star_on);
                        SharedPreferences shared = getActivity().
                                getSharedPreferences("favourit", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = shared.edit();
                        editor.putString(Title, "1");
                        editor.commit();
                    }

                }
            }
        });
        return v;


    }
}
