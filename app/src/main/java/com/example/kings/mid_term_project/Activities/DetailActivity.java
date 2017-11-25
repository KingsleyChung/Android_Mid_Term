package com.example.kings.mid_term_project.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.kings.mid_term_project.DataBase.Person;
import com.example.kings.mid_term_project.DataStorage;
import com.example.kings.mid_term_project.R;

/**
 * Created by Kings on 2017/11/22.
 */

public class DetailActivity extends Activity {

    private Person m_Data;
    private ImageView m_Icon;
    private EditText m_Name, m_Gender, m_Category, m_Time, m_Description;
    private TextInputLayout m_Category_Title;
    private Spinner m_CategorySpinner;
    private Button m_Submit, m_Cancel;

    private String category;
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_detail);

        initData();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        status = bundle.getString("Status");
        m_Data = bundle.getParcelable("Person");

        m_Icon = findViewById(R.id.icon);
        m_Name = findViewById(R.id.name);
        m_Gender = findViewById(R.id.gender);
        m_Category = findViewById(R.id.category);
        m_CategorySpinner = findViewById(R.id.category_spinner);
        m_Category_Title = findViewById(R.id.category_title);
        m_Time = findViewById(R.id.time);
        m_Description = findViewById(R.id.description);
        m_Submit = findViewById(R.id.submit);
        m_Cancel = findViewById(R.id.cancel);

        if (status.equals("show")) setShowMode();
        else if (status.equals("edit")) setEditMode();
        else setAddMode();
    }

    private void setShowMode() {
        m_Icon.setImageBitmap(DataStorage.getBitmapFromName(m_Data.getName()));
        m_Name.setText(m_Data.getName());
        m_Gender.setText(m_Data.getSex());
        m_Time.setText(m_Data.getTime());
        m_Description.setText(m_Data.getDecription());

        m_CategorySpinner.setVisibility(View.INVISIBLE);
        m_Category.setVisibility(View.VISIBLE);
        m_Category_Title.setVisibility(View.VISIBLE);
        m_Category.setText(m_Data.getCategory());

        m_Submit.setText("编辑");
        m_Cancel.setText("删除");

        m_Name.setEnabled(false);
        m_Gender.setEnabled(false);
        m_Time.setEnabled(false);
        m_Description.setEnabled(false);
        m_Category.setEnabled(false);
    }

    private void setEditMode() {
        m_Icon.setImageBitmap(DataStorage.getBitmapFromName(m_Data.getName()));
        m_Name.setText(m_Data.getName());
        m_Gender.setText(m_Data.getSex());
        m_Time.setText(m_Data.getTime());
        m_Description.setText(m_Data.getDecription());

        m_CategorySpinner.setVisibility(View.VISIBLE);
        m_Category.setVisibility(View.INVISIBLE);
        m_Category_Title.setVisibility(View.INVISIBLE);
        if (m_Data.getCategory().equals("史实人物")) m_CategorySpinner.setSelection(0);
        else m_CategorySpinner.setSelection(1);

        m_Submit.setText("确定");
        m_Cancel.setText("取消");

        m_CategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) category = "史实人物";
                else category = "虚构人物";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setAddMode() {

    }
}
