package com.example.hussein_pc.d_movie_progect.Activites;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.hussein_pc.d_movie_progect.Movie_Fragments.Movie_Fragment;
import com.example.hussein_pc.d_movie_progect.R;

public class MainActivity extends AppCompatActivity {
    boolean taplet_check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout frag = (FrameLayout) findViewById(R.id.fragmentContainer2);

        if (frag != null) {
            taplet_check = true;
        } else {
            taplet_check = false;
        }
        Movie_Fragment f = new Movie_Fragment("http://api.themoviedb.org/3/movie/popular?api_key=d075467a2dddab8f155d26eb0d5596ed",taplet_check);
        FragmentManager manger = getSupportFragmentManager();
        FragmentTransaction transaction = manger.beginTransaction();
        transaction.replace(R.id.fragmentContainer, f, "movie");
        transaction.commit();


    }
    private MenuItem popular, voting, favavourite;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //inflater.inflate(R.menu.main_menu, menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        popular = menu.findItem(R.id.popular);
        voting = menu.findItem(R.id.vote);
        favavourite = menu.findItem(R.id.favourite);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favourite:
                item.setChecked(true);
                Movie_Fragment  fragment = new Movie_Fragment(null,taplet_check);
                FragmentManager  manger = getSupportFragmentManager();
                FragmentTransaction  transaction = manger.beginTransaction();
                transaction.replace(R.id.fragmentContainer, fragment, "Movie");
                transaction.commit();
                break;
            case R.id.vote:
                Movie_Fragment Fvote = new Movie_Fragment("http://api.themoviedb.org/3/movie/top_rated?api_key=d075467a2dddab8f155d26eb0d5596ed",taplet_check);
                FragmentManager Vote_manger = getSupportFragmentManager();
                FragmentTransaction vote_transaction = Vote_manger.beginTransaction();
                vote_transaction.replace(R.id.fragmentContainer, Fvote, "movie");
                vote_transaction.commit();
                item.setCheckable(true);
                break;
            case R.id.popular:
                Movie_Fragment Fpopular = new Movie_Fragment("http://api.themoviedb.org/3/movie/popular?api_key=d075467a2dddab8f155d26eb0d5596ed",taplet_check);
                FragmentManager Pop_manger = getSupportFragmentManager();
                FragmentTransaction Pop_transaction = Pop_manger.beginTransaction();
                Pop_transaction.replace(R.id.fragmentContainer, Fpopular, "movie");
                Pop_transaction.commit();
                item.setCheckable(true);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}