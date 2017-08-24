package mckenzie.todo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shanemckenzie on 8/10/17.
 */

public class ToDoListAdapter extends BaseAdapter {
    private final Context mContext;
    private final ArrayList<ToDoItem> mToDoItems;

    public ToDoListAdapter(Context context, ArrayList<ToDoItem> toDoItems) {
        this.mContext = context;
        //this.mToDoItems = toDoItems;
        this.mToDoItems = new ArrayList<>(toDoItems.size());
        for (ToDoItem item : toDoItems) {
            this.mToDoItems.add(new ToDoItem(item));
        }

    }

    @Override
    public int getCount() {
        Log.d("List size", String.valueOf(mToDoItems.size()));
        return mToDoItems.size();
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public Object getItem(int position) {

        mToDoItems.get(position);

        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //mToDoItems.set(position, new ToDoItem());
        final ToDoItem toDoItem = mToDoItems.get(position);

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.to_do_item_cell, null);
        }

        final TextView titleTextView = (TextView)
                convertView.findViewById(R.id.to_do_item_title);


        titleTextView.setText(toDoItem.getTitle());

        return convertView;

    }
}
