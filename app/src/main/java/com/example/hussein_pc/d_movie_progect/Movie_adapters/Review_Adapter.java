package com.example.hussein_pc.d_movie_progect.Movie_adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hussein_pc.d_movie_progect.Movie_Modules.Review_Data;
import com.example.hussein_pc.d_movie_progect.R;

import java.util.List;

/**
 * Created by Dody-PC on 22-Oct-16.
 */
public class Review_Adapter extends RecyclerView.Adapter<Review_Adapter.Rev_ViewHolder> {

    List<Review_Data> list;
    Context context;


    public Review_Adapter(Context context,List<Review_Data> l ){
        this.list = l;
        this.context=context;
    }

    @Override
    public Rev_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =   LayoutInflater.from(parent.getContext()).inflate(R.layout.review_custom_layout,parent,false);
        Rev_ViewHolder rev_viewHolder =    new Rev_ViewHolder(view);
        return rev_viewHolder;
    }

    @Override
    public void onBindViewHolder(Rev_ViewHolder holder, int position) {
        holder.author_name.setText(list.get(position).getAuthor_name());
        holder.content.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Rev_ViewHolder extends RecyclerView.ViewHolder{
        TextView author_name,content;
        public Rev_ViewHolder(View itemView) {
            super(itemView);
            author_name = (TextView) itemView.findViewById(R.id.author_name);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }
}
