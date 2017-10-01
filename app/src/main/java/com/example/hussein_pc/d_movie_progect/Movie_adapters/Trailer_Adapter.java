package com.example.hussein_pc.d_movie_progect.Movie_adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hussein_pc.d_movie_progect.Movie_Modules.Trailer_Data;
import com.example.hussein_pc.d_movie_progect.R;

import java.util.List;

/**
 * Created by Dody-PC on 22-Oct-16.
 */
public class Trailer_Adapter extends RecyclerView.Adapter<Trailer_Adapter.Trailer_ViewHolder> {

    Context context;
    List<Trailer_Data> list;

    public Trailer_Adapter(Context context, List<Trailer_Data> data) {
        this.context = context;
        this.list = data;
    }

    @Override
    public Trailer_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view =   LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_custome_layout, parent, false);
        Trailer_ViewHolder holder = new Trailer_ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Trailer_ViewHolder holder, int position) {
        holder.trailer.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Trailer_ViewHolder extends RecyclerView.ViewHolder {
        TextView trailer;

        public Trailer_ViewHolder(View itemView) {
            super(itemView);
            trailer = (TextView) itemView.findViewById(R.id.namefilm);
        }
    }
}

