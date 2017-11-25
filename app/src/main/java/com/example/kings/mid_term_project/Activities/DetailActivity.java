package com.example.kings.mid_term_project.Activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kings.mid_term_project.DataBase.Person;
import com.example.kings.mid_term_project.DataStorage;
import com.example.kings.mid_term_project.R;

/**
 * Created by Kings on 2017/11/22.
 */

public class DetailActivity extends Activity {

    DataStorage m_DataStorage;

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
        setButtonOnClickListener();

    }


    private void setButtonOnClickListener() {
        m_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(status) {
                    case "show":
                        setEditMode();
                        break;
                    case "edit":
                        Bitmap temp_bitmap = ((BitmapDrawable) m_Icon.getDrawable()).getBitmap();
                        Person temp = new Person(m_Name.getText().toString(), m_Gender.getText().toString(), category, m_Time.getText().toString(), m_Description.getText().toString(), BitmapFactory.decodeResource(MainActivity.resourcesInstance, R.mipmap.anyang));
                        m_DataStorage.updatePerson(m_Data.getName(), temp);
                        m_Data = temp;
                        setShowMode();
                        break;
                    case "add":
                        temp_bitmap = ((BitmapDrawable) m_Icon.getDrawable()).getBitmap();
                        temp = new Person(m_Name.getText().toString(), m_Gender.getText().toString(), category, m_Time.getText().toString(), m_Description.getText().toString(), BitmapFactory.decodeResource(MainActivity.resourcesInstance, R.mipmap.anyang));
                        if (m_DataStorage.addPerson(temp)) {
                            m_Data = temp;
                            setShowMode();
                        } else {
                            Toast.makeText(getApplicationContext(),"此人物已存在", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });

        m_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (status) {
                    case "show":
                        m_DataStorage.deletePerson(m_Data.getName());
                        finish();
                        break;
                    case "edit":
                        setShowMode();
                        break;
                    case "add":
                        finish();
                        break;
                }
            }
        });
    }

    private void initData() {
        m_DataStorage = new DataStorage(getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        status = bundle.getString("Status");
        if (status.equals("show") || status.equals("edit")) m_Data = bundle.getParcelable("Person");
        else m_Data = null;

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
        status = "show";
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
        status = "edit";
        m_Icon.setImageBitmap(DataStorage.getBitmapFromName(m_Data.getName()));
        m_Name.setText(m_Data.getName());
        m_Gender.setText(m_Data.getSex());
        m_Time.setText(m_Data.getTime());
        m_Description.setText(m_Data.getDecription());

        m_CategorySpinner.setVisibility(View.VISIBLE);
        m_Category.setVisibility(View.INVISIBLE);
        m_Category_Title.setVisibility(View.INVISIBLE);
        if (m_Data.getCategory().equals("虚构人物")) m_CategorySpinner.setSelection(1);
        else m_CategorySpinner.setSelection(0);

        m_Submit.setText("确定");
        m_Cancel.setText("取消");

        m_Name.setEnabled(true);
        m_Gender.setEnabled(true);
        m_Time.setEnabled(true);
        m_Description.setEnabled(true);
        m_Category.setEnabled(true);

        setSpinnerListener();

    }

    private void setAddMode() {
        status = "add";
        //m_Icon.setImageBitmap(DataStorage.getBitmapFromName(m_Data.getName()));
        m_Name.setText("");
        m_Gender.setText("");
        m_Time.setText("");
        m_Description.setText("");

        m_CategorySpinner.setVisibility(View.VISIBLE);
        m_Category.setVisibility(View.INVISIBLE);
        m_Category_Title.setVisibility(View.INVISIBLE);
        m_CategorySpinner.setSelection(0);

        m_Submit.setText("添加");
        m_Cancel.setText("取消");

        m_Name.setEnabled(true);
        m_Gender.setEnabled(true);
        m_Time.setEnabled(true);
        m_Description.setEnabled(true);
        m_Category.setEnabled(true);

        category = "史实人物";
        m_CategorySpinner.setSelection(0);
        setSpinnerListener();
    }

    private void setSpinnerListener() {
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
}
