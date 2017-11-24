package com.example.kings.mid_term_project.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.kings.mid_term_project.DataBase.Person;
import com.example.kings.mid_term_project.R;

/**
 * Created by Kings on 2017/11/22.
 */

public class DetailActivity extends Activity {

    private Person m_Data;
    private TextView m_Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_detail);

        initData();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        m_Data = bundle.getParcelable("Person");

        m_Name = findViewById(R.id.detail_name);

        m_Name.setText(m_Data.getName());
    }
}
