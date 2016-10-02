package com.example.sjors.sjors_witteveen_pset4;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String FIRST_RUN = "first run";

    private DBhelper dbhelper;

    private EditText addItem;
    private ListView toDoList;

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addItem = (EditText) findViewById(R.id.add_item);
        toDoList = (ListView) findViewById(R.id.to_do_list);

        dbhelper = new DBhelper(this);

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        if (pref.getBoolean(FIRST_RUN, true)) {
            dbhelper.create(new ToDoItem("Welcome to your To-Do List!"));
            dbhelper.create(new ToDoItem("Type a new to-do item below."));
            dbhelper.create(new ToDoItem("Long-press an item to remove it."));
            pref.edit().putBoolean(FIRST_RUN, false).apply();
        }

        adapter = new MyAdapter(this, dbhelper.read());
        toDoList.setAdapter(adapter);

        // EditText input action listener
        addItem.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    ToDoItem toDoItem = new ToDoItem(addItem.getText().toString());
                    dbhelper.create(toDoItem);
                    adapter.clear();
                    adapter.addAll(dbhelper.read());
                    addItem.getText().clear();
                    return true;
                }
                return true;
            }
        });

        // toDoList action listener
        toDoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dbhelper.update(adapter.getItem(position).switchChecked());

                adapter.notifyDataSetChanged();
            }
        });

        // toDoList long click action listener
        toDoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dbhelper.delete(adapter.getItem(position));

                adapter.remove(adapter.getItem(position));
                adapter.notifyDataSetChanged();

                return true;
            }
        });

    }
}
