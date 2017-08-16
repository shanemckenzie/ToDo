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
    public static final String TASKS_COLUMN_CREATED_DATE = "created_date";
    public static final String TASKS_COLUMN_DUE_DATE = "due_date";

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO: auto-generated method stub
        db.execSQL(
                "create table tasks" +
                        "(id integer primary key, title text, " +
                        "description text, due_hour integer, due_minute integer," +
                        "created_date text, due_date text)");
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

        //TODO: getters for date
        contentValues.put("created_date", "");
        contentValues.put("due_date", "");

        db.insert("tasks", null, contentValues);

        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from tasks where id="+id+"", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TASKS_TABLE_NAME);
        return numRows;
    }

    public boolean updateTask(Integer id, String title, String desc, int hour, int minute,
                              String createdDate, String dueDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("title", title);
        contentValues.put("description", desc);
        contentValues.put("due_hour", hour);
        contentValues.put("due_minute", minute);
        contentValues.put("created_date", createdDate);
        contentValues.put("due_date", dueDate);

        db.update("tasks", contentValues, "id = ? ", new String[] { Integer.toString(id) });

        return true;
    }

    public Integer deleteTask (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("tasks", "id = ? ",
                new String[] { Integer.toString(id) });
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
            //TODO: get date

            Log.d("LOADED TASK TITLE ", cursor.getString(cursor.getColumnIndex(TASKS_COLUMN_TITLE)));

            items.add(loadedItem);
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }

}


















