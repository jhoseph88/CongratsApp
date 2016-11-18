package edu.umich.engin.congrats;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

class ToDoAdapter extends ArrayAdapter<ToDo> {
    ToDo[] toDoItems = null;
    String[] stepNumberHeadings = null;
    Context context;
    public ToDoAdapter(Context context, ToDo[] resource, String[] headings) {
        super(context, edu.umich.engin.congrats.R.layout.row, resource);
        this.context = context;
        this.toDoItems = resource;
        this.stepNumberHeadings = headings;
    }

    public ToDoAdapter(Context context, int resource, ToDo[] toDoItems) {
        super(context, resource);
        this.toDoItems = toDoItems;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(edu.umich.engin.congrats.R.layout.row, parent, false);
        TextView name = (TextView) convertView.findViewById(edu.umich.engin.congrats.R.id.checkListText);
        TextView heading = (TextView) convertView.findViewById(edu.umich.engin.congrats.R.id.stepNumberHeading);
        TextView depositReminders = (TextView) convertView.findViewById(edu.umich.engin.congrats.R.id.depositReminder);
        CheckBox checkBox = (CheckBox) convertView.findViewById(edu.umich.engin.congrats.R.id.checkBox);
        final int pos = position;

        // Update boolCheckBoxes when user clicks/unclicks a checkbox
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toDoItems[pos].setValue(1);
                    ( (CheckListActivity)context).updateCheckboxString(position, true);
                }
                else {
                    toDoItems[pos].setValue(0);
                    ( (CheckListActivity)context).updateCheckboxString(position, false);
                }
            }
        });
        name.setText(Html.fromHtml(toDoItems[position].getName() ) );
        name.setMovementMethod(LinkMovementMethod.getInstance() );
        heading.setText(stepNumberHeadings[position]);
        // Add deposit reminder to final two checklist items.
        if (position == 5 || position == 6) {
            depositReminders.setText(context.getString(edu.umich.engin.congrats.R.string.deposit_reminder) );
        }
        /* Set checkBox to true state according to value of boolCheckBoxes (this is for the case
           where the user exited the app and the state of the checkboxes need to be restored */
        if ( ( (CheckListActivity)context).getCheckboxStringValue(position) == '1') {
            checkBox.setChecked(true);
        }
        else {
            checkBox.setChecked(false);
        }
        return convertView;
    }
}
