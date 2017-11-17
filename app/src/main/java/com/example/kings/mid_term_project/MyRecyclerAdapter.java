package com.example.kings.mid_term_project;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Kings on 2017/11/17.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private List<Map<String, Object>> m_Data;

    MyRecyclerAdapter(List<Map<String, Object>> data) {
        m_Data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.m_TextView.setText(m_Data.get(position).get("name").toString());
        holder.m_CircleImageView.setImageResource(Integer.parseInt(m_Data.get(position).get("icon").toString()));
    }

    @Override
    public int getItemCount() {
        return m_Data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView m_CircleImageView;
        public TextView m_TextView;
        public MyViewHolder(View view) {
            super(view);
            m_CircleImageView = view.findViewById(R.id.character_icon);
            m_TextView = view.findViewById(R.id.character_name);
        }
    }
}
