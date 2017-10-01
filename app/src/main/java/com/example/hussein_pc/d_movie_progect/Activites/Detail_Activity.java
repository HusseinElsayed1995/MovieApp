package com.example.hussein_pc.d_movie_progect.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.hussein_pc.d_movie_progect.Movie_Fragments.Detail_Fragment;
import com.example.hussein_pc.d_movie_progect.R;

public class Detail_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);
        Detail_Fragment f = new Detail_Fragment();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        f.setArguments(bundle);
        FragmentManager manger = getSupportFragmentManager();
        FragmentTransaction transaction = manger.beginTransaction();
        transaction.replace(R.id.frag, f, "movie");
        transaction.commit();
    }

}
