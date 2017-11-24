package com.example.kings.mid_term_project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kings.mid_term_project.DataBase.Person;

import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Kings on 2017/11/17.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private List<Person> m_Data;
    private Context m_Context;
    private LayoutInflater m_Inflater;
    private OnItemClickListener mOnItemClickListener;

    public MyRecyclerAdapter(Context context, List<Person> data) {
        m_Data = data;
        m_Context = context;
        m_Inflater = LayoutInflater.from(m_Context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = m_Inflater.inflate(R.layout.recycler_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.m_TextView.setText(m_Data.get(position).getName());
        holder.m_CircleImageView.setImageBitmap(m_Data.get(position).getBitmap());

        if( mOnItemClickListener!= null){
            holder.itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });

            holder. itemView.setOnLongClickListener( new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(position);
                    return false;
                }
            });
        }
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

    public interface OnItemClickListener{
        void onClick( int position);
        void onLongClick( int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this.mOnItemClickListener = onItemClickListener;
    }
}
