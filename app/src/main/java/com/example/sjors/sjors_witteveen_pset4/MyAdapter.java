package com.example.sjors.sjors_witteveen_pset4;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<String> {

    public MyAdapter(Context context, ArrayList<String> toDoList) {
        super(context, R.layout.list_item, toDoList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);

    }
}
