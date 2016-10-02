package com.example.sjors.sjors_witteveen_pset4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<ToDoItem> {

    public MyAdapter(Context context, ArrayList<ToDoItem> toDoList) {
        super(context, R.layout.list_item, toDoList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);

        ToDoItem toDoItem = getItem(position);

        TextView toDoText = (TextView) view.findViewById(R.id.to_do_text);
        CheckBox toDoCheckBox = (CheckBox) view.findViewById(R.id.to_do_checkbox);

        toDoText.setText(toDoItem.text);
        toDoCheckBox.setChecked(toDoItem.checked);

        return view;
    }
}
