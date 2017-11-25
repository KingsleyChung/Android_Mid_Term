package com.example.kings.mid_term_project.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kings.mid_term_project.DataBase.Person;
import com.example.kings.mid_term_project.DataStorage;
import com.example.kings.mid_term_project.R;

/**
 * Created by Kings on 2017/11/22.
 */

public class DetailActivity extends Activity {

    private Person m_Data;
    private ImageView m_Icon;
    private TextView m_Name, m_Gender, m_Category, m_Time, m_Description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_detail);

        initData();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        m_Data = bundle.getParcelable("Person");

        m_Icon = findViewById(R.id.detail_icon);
        m_Name = findViewById(R.id.detail_name);
        m_Gender = findViewById(R.id.detail_gender);
        m_Category = findViewById(R.id.detail_category);
        m_Time = findViewById(R.id.detail_time);
        m_Description = findViewById(R.id.detail_descripion);

        m_Icon.setImageBitmap(m_Data.getBitmap());
        m_Icon.setImageBitmap(DataStorage.getBitmapFromName(m_Data.getName()));
        m_Name.setText(m_Data.getName());
        m_Gender.setText("性别：" + m_Data.getSex());
        m_Category.setText("人物类别：" + m_Data.getCategory());
        m_Time.setText("生卒年份：" + m_Data.getTime());
        m_Description.setText(m_Data.getDecription());
    }
}
