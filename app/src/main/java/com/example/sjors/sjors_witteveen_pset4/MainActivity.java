package com.example.sjors.sjors_witteveen_pset4;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DBhelper dbhelper;

    private EditText addItem;
    private ListView toDoList;

    private ArrayAdapter adapter;
    private ArrayList<String> toDoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbhelper = new DBhelper(this);
        toDoItems = dbhelper.read();

        addItem = (EditText) findViewById(R.id.add_item);
        toDoList = (ListView) findViewById(R.id.to_do_list);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, toDoItems);
        toDoList.setAdapter(adapter);

        // EditText input action listener
        addItem.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    ToDoItem toDoItem = new ToDoItem(addItem.getText().toString());
                    dbhelper.create(toDoItem);
                    toDoItems.add(addItem.getText().toString());
                    adapter.notifyDataSetChanged();
                    addItem.getText().clear();
                    return true;
                }
                return false;
            }
        });

        // toDoList action listener
        toDoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                if (text1.getPaintFlags() == Paint.STRIKE_THRU_TEXT_FLAG) {
                    text1.setPaintFlags(0);
                } else {
                    text1.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }
            }
        });

        toDoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                dbhelper.delete(text1.getText().toString());
                toDoItems.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });

    }
}
