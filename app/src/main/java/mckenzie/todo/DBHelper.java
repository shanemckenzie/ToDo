package mckenzie.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by shanemckenzie on 8/16/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "ToDo.db";
    public static final String TASKS_TABLE_NAME = "tasks";
    public static final String TASKS_COLUMN_ID = "id";
    public static final String TASKS_COLUMN_TITLE = "title";
    public static final String TASKS_COLUMN_DESC = "description";
    public static final String TASKS_COLUMN_DUE_HOUR = "due_hour";
    public static final String TASKS_COLUMN_DUE_MINUTE = "due_minute";

    //TODO: create more fields for date if necessary for data type
    public static final String TASKS_COLUMN_CREATED_DAY = "created_day";
    public static final String TASKS_COLUMN_CREATED_MONTH = "created_month";
    public static final String TASKS_COLUMN_CREATED_YEAR = "created_year";

    public static final String TASKS_COLUMN_DUE_DAY = "due_day";
    public static final String TASKS_COLUMN_DUE_MONTH = "due_month";
    public static final String TASKS_COLUMN_DUE_YEAR = "due_year";

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO: auto-generated method stub
        db.execSQL(
                "CREATE TABLE tasks" +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1, title TEXT, " +
                        "description TEXT, due_hour INTEGER, due_minute INTEGER," +
                        "created_day INTEGER, created_month INTEGER, created_year INTEGER, " +
                        "due_day INTEGER, due_month INTEGER, due_year INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tasks");
        onCreate(db);
    }


    public boolean insertTask(ToDoItem newItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("title", newItem.getTitle());
        contentValues.put("description", newItem.getDesc());

        contentValues.put("due_hour", newItem.getHour());
        contentValues.put("due_minute", newItem.getMinute());

        contentValues.put("created_day", newItem.getCreatedDay());
        contentValues.put("created_month", newItem.getCreatedMonth());
        contentValues.put("created_year", newItem.getCreatedYear());

        contentValues.put("due_day", newItem.getDueDay());
        contentValues.put("due_month", newItem.getDueMonth());
        contentValues.put("due_year", newItem.getDueYear());

        db.insert("tasks", null, contentValues);
        return true;
    }

    public ToDoItem getData(int id) {
        //DBHelper dbhelper = new DBHelper(context);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tasks WHERE id="+id+";", null);
        cursor.moveToFirst();

        ToDoItem retrievedItem = new ToDoItem();

        retrievedItem.setTitle(cursor.getString(cursor.getColumnIndex(TASKS_COLUMN_TITLE)));
        retrievedItem.setDesc(cursor.getString(cursor.getColumnIndex(TASKS_COLUMN_DESC)));

        retrievedItem.setHour(cursor.getInt(cursor.getColumnIndex(TASKS_COLUMN_DUE_HOUR)));
        retrievedItem.setMinute(cursor.getInt(cursor.getColumnIndex(TASKS_COLUMN_DUE_MINUTE)));

        retrievedItem.setId(cursor.getInt(cursor.getColumnIndex(TASKS_COLUMN_ID)));

        retrievedItem.setCreatedDay(cursor.getColumnIndex(TASKS_COLUMN_CREATED_DAY));
        retrievedItem.setCreatedMonth(cursor.getColumnIndex(TASKS_COLUMN_CREATED_MONTH));
        retrievedItem.setCreatedYear(cursor.getColumnIndex(TASKS_COLUMN_CREATED_YEAR));

        retrievedItem.setDueDay(cursor.getColumnIndex(TASKS_COLUMN_DUE_DAY));
        retrievedItem.setDueMonth(cursor.getColumnIndex(TASKS_COLUMN_DUE_MONTH));
        retrievedItem.setDueYear(cursor.getColumnIndex(TASKS_COLUMN_DUE_YEAR));

        return retrievedItem;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TASKS_TABLE_NAME);
        return numRows;
    }

    public boolean updateTask(Integer id, ToDoItem itemToUpdate) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("title", itemToUpdate.getTitle());
        contentValues.put("description", itemToUpdate.getDesc());

        contentValues.put("due_hour", itemToUpdate.getHour());
        contentValues.put("due_minute", itemToUpdate.getMinute());

        contentValues.put("created_day", itemToUpdate.getCreatedDay());
        contentValues.put("created_month", itemToUpdate.getCreatedMonth());
        contentValues.put("created_year", itemToUpdate.getCreatedYear());

        contentValues.put("due_day", itemToUpdate.getDueDay());
        contentValues.put("due_month", itemToUpdate.getDueMonth());
        contentValues.put("due_year", itemToUpdate.getDueYear());

        db.update("tasks", contentValues, "id = ? ", new String[] { Integer.toString(id) });

        return true;
    }

    public Integer deleteTask (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TASKS_TABLE_NAME,
                "id = ? ", new String[] { Integer.toString(id) });

    }

    public ArrayList<ToDoItem> getAllTasks() {
        ArrayList<ToDoItem> items = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tasks", null);
        cursor.moveToNext();

        while (!cursor.isAfterLast()) {
            ToDoItem loadedItem = new ToDoItem();

            loadedItem.setTitle(cursor.getString(cursor.getColumnIndex(TASKS_COLUMN_TITLE)));
            loadedItem.setDesc(cursor.getString(cursor.getColumnIndex(TASKS_COLUMN_DESC)));
            loadedItem.setHour(cursor.getInt(cursor.getColumnIndex(TASKS_COLUMN_DUE_HOUR)));
            loadedItem.setMinute(cursor.getInt(cursor.getColumnIndex(TASKS_COLUMN_DUE_MINUTE)));
            loadedItem.setId(cursor.getInt(cursor.getColumnIndex(TASKS_COLUMN_ID)));
            //TODO: get date

            Log.d("LOADED TASK ID ", cursor.getString(cursor.getColumnIndex(TASKS_COLUMN_ID)));

            items.add(loadedItem);
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }

}


















