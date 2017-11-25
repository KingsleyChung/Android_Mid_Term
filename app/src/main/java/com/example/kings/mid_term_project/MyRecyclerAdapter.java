package com.example.kings.mid_term_project;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kings.mid_term_project.DataBase.Person;

import java.util.ArrayList;
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
    //for delete
    private boolean isDelete;
    private ArrayList<Integer> deletePerson;

    public MyRecyclerAdapter(Context context, List<Person> data) {
        m_Data = data;
        m_Context = context;
        m_Inflater = LayoutInflater.from(m_Context);
        initDeletePerson();
    }

    public void initDeletePerson() {
        deletePerson = new ArrayList<>();
        int size = getItemCount();
        for (int i = 0; i < size; i++) {
            deletePerson.add(0);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = m_Inflater.inflate(R.layout.recycler_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.m_TextView.setText(m_Data.get(position).getName());
        holder.m_CircleImageView.setImageBitmap(m_Data.get(position).getBitmap());

        if( mOnItemClickListener!= null) {
            holder.itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });

            holder.itemView.setOnLongClickListener( new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(position);
                    return true;
                }
            });
        }

        //select button visible
        if (isDelete)
            holder.m_SelectBox.setVisibility(View.VISIBLE);
        else
            holder.m_SelectBox.setVisibility(View.INVISIBLE);
        //select button click listener
        holder.m_SelectBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = deletePerson.get(position);
                if (tag == 1) {
                    deletePerson.set(position, 0);
                    holder.m_SelectBox.setImageResource(R.mipmap.unchecked);
                }
                else {
                    deletePerson.set(position, 1);
                    holder.m_SelectBox.setImageResource(R.mipmap.checked);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return m_Data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView m_CircleImageView;
        public TextView m_TextView;
        public ImageButton m_SelectBox;
        public MyViewHolder(View view) {
            super(view);
            m_CircleImageView = view.findViewById(R.id.character_icon);
            m_TextView = view.findViewById(R.id.character_name);
            m_SelectBox = view.findViewById(R.id.select_box);
        }
    }

    public interface OnItemClickListener{
        void onClick( int position);
        void onLongClick( int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this.mOnItemClickListener = onItemClickListener;
    }

    public void set_isDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public ArrayList<Integer> deletePersonList() {
        return deletePerson;
    }
}
