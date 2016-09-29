package com.example.sjors.sjors_witteveen_pset4;

public class ToDoItem {

    public int id;
    public String text;
    public boolean checked;

    public ToDoItem(String text) {
        this.text = text;
        checked = true;
    }
}
