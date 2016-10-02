package com.example.sjors.sjors_witteveen_pset4;

public class ToDoItem {

    public int id;
    public String text;
    public boolean checked = false;

    public ToDoItem(String text) {
        this.text = text;
    }

    public ToDoItem(int id, String text, Boolean checked) {
        this.id = id;
        this.text = text;
        this.checked = checked;
    }

    public ToDoItem switchChecked() {
        checked = !checked;
        return this;
    }
}
