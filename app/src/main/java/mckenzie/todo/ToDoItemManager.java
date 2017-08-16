package mckenzie.todo;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanemckenzie on 8/13/17.
 */

public class ToDoItemManager {

    private DBHelper db;

    //public static ToDoItem[] items;
    public static List<ToDoItem> items;

    void loadItems(Context context) {
        //TODO: Read items from storage
        db = new DBHelper(context);

//        ArrayList<String> array_list = db.getAllTasks();
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.to_do_item_cell, array_list);

    }

    void addNewItem(ToDoItem newItem) {
        //TODO: Append new item to end of the list
        //TODO: Save to storage

        loadItems();

    }

    void updateItem(int position, ToDoItem updatedItem) {

        //TODO: Save to storage

        items.set(position, updatedItem);

    }


}
