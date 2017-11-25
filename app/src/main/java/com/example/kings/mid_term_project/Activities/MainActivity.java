package com.example.kings.mid_term_project.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.kings.mid_term_project.DataBase.Person;
import com.example.kings.mid_term_project.DataStorage;
import com.example.kings.mid_term_project.MyRecyclerAdapter;
import com.example.kings.mid_term_project.R;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView m_RecyclerView;
    private ImageView m_MenuBtn;
    private android.support.design.widget.FloatingActionButton m_DeleteButton;
    private SearchView m_SearchView;
    private View m_Glass;
    private FloatingActionMenu m_ActionMenu;
    private MyRecyclerAdapter m_RecyclerAdapter;
    private DataStorage m_DataStorage;
    public static Resources resourcesInstance;
    private boolean isDelete = false;
    private boolean isSearch = false;
    ArrayList<Person> relativePerson = new ArrayList<>();

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
                resetStatus();
                Person temp_data = m_DataStorage.getData().get(position);
                showDetailActivity(temp_data, "show");
            }

            @Override
            public void onLongClick(final int position) {
                resetStatus();
                final Person temp_data = m_DataStorage.getData().get(position);
                AlertDialog.Builder m_alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                m_alertDialogBuilder.setTitle("更多");

                final String[] options = {"编辑", "删除"};
                m_alertDialogBuilder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) showDetailActivity(temp_data, "edit");
                        else {
                            m_DataStorage.deletePerson(temp_data.getName());
                            m_RecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                });

                m_alertDialogBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "您选择了[取消]", Toast.LENGTH_SHORT).show();
                    }
                });
                m_alertDialogBuilder.setCancelable(true);
                AlertDialog m_alertDialog = m_alertDialogBuilder.create();
                m_alertDialog.show();
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

        m_SearchView = findViewById(R.id.search_view);
        m_Glass = findViewById(R.id.frosted_glass);
        m_DeleteButton = findViewById(R.id.delete_button);

        m_MenuBtn = new ImageView(this); // Create an icon
        m_MenuBtn.setImageResource(R.mipmap.ic_menu_white_48pt_2x);
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(m_MenuBtn)
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

        m_ActionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(addItem)
                .addSubActionView(multiSelect)
                .addSubActionView(search)
                .attachTo(actionButton)
                .build();

        addItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                resetStatus();
                showDetailActivity(null, "add");
            }
        });
        //multiselect button click listener
        multiSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFunc();
            }
        });
        //search button click listener
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchFunc();
            }
        });
        m_MenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDelete == false && !m_ActionMenu.isOpen()) m_ActionMenu.open(true);
                resetStatus();
            }
        });
    }

    private void showDetailActivity(Person data, String status) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Status", status);
        if (status.equals("show") || status.equals("edit")) {
            bundle.putParcelable("Person", data);
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void selectFunc() {
        //select button visible
        isDelete = true;
        updateSelection();
        m_ActionMenu.close(true);
        m_MenuBtn.setImageResource(R.mipmap.back);

        deleteFunc();
    }

    private void updateSelection() {
        m_RecyclerAdapter.set_isDelete(isDelete);
        int size = m_RecyclerAdapter.getItemCount();
        for (int i = 0; i < size; i++) {
            m_RecyclerAdapter.notifyItemChanged(i);
        }
    }

    private void deleteFunc() {
        //delete button visible
        if (isDelete)
            m_DeleteButton.setVisibility(View.VISIBLE);
        else
            m_DeleteButton.setVisibility(View.INVISIBLE);

        //delete button click function
        m_DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> deletePerson = m_RecyclerAdapter.deletePersonList();
                if (deletePerson != null) {
                    ArrayList<String> deleteList = new ArrayList<>();
                    for (int i = 0; i < deletePerson.size(); i++) {
                        if (deletePerson.get(i).equals(1)) {
                            deleteList.add(DataStorage.getData().get(i).getName());

                        }
                    }
                    m_DataStorage.deleteSomePerson(deleteList);
                    m_RecyclerAdapter.notifyDataSetChanged();
                    m_RecyclerAdapter.initDeletePerson();
                    resetStatus();
                }
            }
        });
    }

    private void searchFunc() {
        m_ActionMenu.close(true);
        isSearch = !isSearch;
        if (isSearch) {
            m_SearchView.setVisibility(View.VISIBLE);
            m_Glass.setVisibility(View.VISIBLE);
            m_MenuBtn.setImageResource(R.mipmap.back);
        }
        else {
            resetStatus();
        }
        //listview
        final ListView listView = (ListView)findViewById(R.id.list_view);

        //SearchView and ListView visible
        m_SearchView = (SearchView)findViewById(R.id.search_view);
        isSearch = !isSearch;
        if (isSearch) {
            m_SearchView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.VISIBLE);
        }
        else {
            m_SearchView.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }
        m_SearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println(newText);
                if (!newText.equals("")) {
                    relativePerson = m_DataStorage.searchPerson(newText);
                    listViewItemUpdate(listView, relativePerson);
                }
                return false;
            }
        });
    }

    private void listViewItemUpdate(ListView listView, final ArrayList<Person> relativePerson) {
        ArrayList<String> relativeName = new ArrayList<>();
        for (int i = 0; i < relativePerson.size(); i++) {
            relativeName.add(relativePerson.get(i).getName());
        }
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_view_item, relativeName);
        listView.setAdapter(arrayAdapter);
        //ListView item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDetailActivity(relativePerson.get(position), "show");
            }
        });
    }

    private void resetStatus() {
        isDelete = false;
        isSearch = false;
        m_MenuBtn.setImageResource(R.mipmap.ic_menu_white_48pt_2x);
        m_DeleteButton.setVisibility(View.INVISIBLE);
        m_SearchView.setVisibility(View.INVISIBLE);
        m_Glass.setVisibility(View.INVISIBLE);
        if (!isDelete) updateSelection();
        if (m_ActionMenu.isOpen()) m_ActionMenu.close(true);
    }
}
