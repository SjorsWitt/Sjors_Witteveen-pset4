package com.example.sjors.sjors_witteveen_pset4;

public class ToDoItem {

    private int id;
    private String text;
    private boolean checked;

    public ToDoItem(String text) {
        this.text = text;
        checked = false;
    }
}
