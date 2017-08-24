package mckenzie.todo;

import java.sql.Struct;

/**
 * Created by shanemckenzie on 8/10/17.
 */

public class ToDoItem {
    private String title;
    private String desc;
    private int id;
    private int hour;
    private int minute;
    //TODO: Add due date and created date
    int createdYear;
    int createdMonth;
    int createdDay;

    int dueYear;
    int dueMonth;
    int dueDay;

    public ToDoItem() {
        this.title = "Example title";
        this.desc = "";

        //TODO: set id
        this.id = 0;
    }

    public ToDoItem(ToDoItem item) {
        this.title = item.getTitle();
        this.desc = item.getDesc();
        this.id = item.getId();
        this.hour = item.getHour();
        this.minute = item.getMinute();
        //TODO: Add due date and created date


    }


    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
