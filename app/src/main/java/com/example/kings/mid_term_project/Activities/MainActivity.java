package com.example.kings.mid_term_project.Activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.kings.mid_term_project.DataBase.Person;
import com.example.kings.mid_term_project.DataStorage;
import com.example.kings.mid_term_project.MyRecyclerAdapter;
import com.example.kings.mid_term_project.R;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView m_RecyclerView;
    private MyRecyclerAdapter m_RecyclerAdapter;
    private DataStorage m_DataStorage;
    public static Resources resourcesInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resourcesInstance = this.getResources();
        m_DataStorage = new DataStorage(getApplicationContext());
        //m_DataStorage.deleteAllPerson();
        initRecyclerView();
        initFloatingMenu();

    }

    @Override
    protected void onResume() {
        initRecyclerView();
        super.onResume();
    }
    private void initRecyclerView() {
        m_RecyclerAdapter = new MyRecyclerAdapter(this, m_DataStorage.getData());
        m_RecyclerAdapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Person temp_data = m_DataStorage.getData().get(position);
                showDetailActivity(temp_data);
            }

            @Override
            public void onLongClick(int position) {

            }
        });
        m_RecyclerView = findViewById(R.id.characterList);
        m_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        m_RecyclerView.setHasFixedSize(true);
        m_RecyclerView.setAdapter(m_RecyclerAdapter);
        m_RecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        m_RecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initFloatingMenu() {
        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageResource(R.mipmap.ic_menu_white_48pt_2x);
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();
        actionButton.setBackgroundTintList(ColorStateList.valueOf(getApplicationContext().getColor(R.color.red)));

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        //Sub menu
        int subMenuPadding = 15;
        ImageView itemIcon = new ImageView(this);
        itemIcon.setPadding(subMenuPadding, subMenuPadding, subMenuPadding, subMenuPadding);
        itemIcon.setImageResource(R.mipmap.ic_check_box_white_2x);
        SubActionButton multiSelect = itemBuilder.setContentView(itemIcon)
                .setLayoutParams(new FloatingActionButton.LayoutParams(150, 150))
                .build();
        multiSelect.setBackgroundTintList(ColorStateList.valueOf(getApplicationContext().getColor(R.color.blue)));

        itemIcon = new ImageView(this);
        itemIcon.setImageResource(R.mipmap.ic_add_white_2x);
        SubActionButton addItem = itemBuilder.setContentView(itemIcon).build();
        addItem.setBackgroundTintList(ColorStateList.valueOf(getApplicationContext().getColor(R.color.pink)));

        itemIcon = new ImageView(this);
        itemIcon.setImageResource(R.mipmap.ic_search_white_2x);
        SubActionButton search = itemBuilder.setContentView(itemIcon).build();
        search.setBackgroundTintList(ColorStateList.valueOf(getApplicationContext().getColor(R.color.yellow)));

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(addItem)
                .addSubActionView(multiSelect)
                .addSubActionView(search)
                .attachTo(actionButton)
                .build();
    }

    private void showDetailActivity(Person data) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("Person", data);
        //bundle.putParcelable("Icon", data.getBitmap());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
