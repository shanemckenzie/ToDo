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
    public static ArrayList<ToDoItem> items;

    ArrayList<ToDoItem> loadItems(Context context) {
        //TODO: Read items from storage
        db = new DBHelper(context);
        items = db.getAllTasks();

        return items;
    }

    void addNewItem(ToDoItem newTask) {
        //TODO: Append new item to end of the list
        //TODO: Save to storage
        db.insertTask(newTask);

        //loadItems();

    }

    void updateItem(int position, ToDoItem updatedItem) {

        //TODO: Save to storage

        items.set(position, updatedItem);

    }


}
