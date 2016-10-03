package com.example.congratsapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

class ToDoAdapter extends ArrayAdapter<ToDo> {
    ToDo[] toDoItems = null;
    Context context;
    public ToDoAdapter(Context context, ToDo[] resource) {
        super(context, R.layout.row, resource);
        this.context = context;
        this.toDoItems = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.checkListText);
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
        name.setText(toDoItems[position].getName() );
        if (toDoItems[position].getValue() == 1) {
            checkBox.setChecked(true);
        }
        else {
            checkBox.setChecked(false);
        }
        return convertView;
    }
}
