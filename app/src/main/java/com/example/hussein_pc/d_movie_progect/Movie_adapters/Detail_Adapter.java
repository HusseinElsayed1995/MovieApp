package com.example.hussein_pc.d_movie_progect.Movie_adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hussein_pc.d_movie_progect.Movie_Modules.Detail_Data;
import com.example.hussein_pc.d_movie_progect.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Hussien_pc on 10/1/2016.
 */
public class Detail_Adapter extends RecyclerView.Adapter<Detail_Adapter.RecHolder>{

    Context c;
    ArrayList<Detail_Data> li;
    RecAction listner;
    LayoutInflater inflater;

    public Detail_Adapter(Context con, ArrayList<Detail_Data> list){

        this.c = con;
        this.li = list;
        inflater=LayoutInflater.from(con);
    }

    @Override
    public RecHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = inflater.inflate(R.layout.custom_layout,parent,false);
        RecHolder h = new RecHolder(item);
        return h;
    }

    @Override
    public void onBindViewHolder(RecHolder holder, final int position) {


        Detail_Data ob = li.get(position);
        //  String n = ob.getName();
        //  String l = D.getLink();
        // holder.film_name.setText(n);
        // Picasso.with(c).load(l).into(holder.film_photo);


        String link = ob.getLink();
        Picasso.with(c).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + link).into(holder.film_photo);
        holder.film_name.setText(li.get(position).getName());

    /*    holder.film_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onImageClick(v, position);
            }
        });*/

    }
    public void listnerInterface(Detail_Adapter.RecAction c){
        listner = c;


    }
    @Override
    public int getItemCount() {
        return li.size();
    }

    class RecHolder extends RecyclerView.ViewHolder{
        ImageView film_photo;
        TextView film_name;
        public RecHolder(View itemView) {
            super(itemView);
            film_photo = (ImageView) itemView.findViewById(R.id.image);
            film_name =(TextView) itemView.findViewById(R.id.text);
        }
    }
    public interface RecAction{
        public void onImageClick(View v,int position);

    }
    public void clear() {
        if (li != null) {
            int size = li.size();
            li.clear();
            notifyItemRangeRemoved(0, size);
        }
    }
}
