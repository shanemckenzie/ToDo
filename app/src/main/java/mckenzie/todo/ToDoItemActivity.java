package mckenzie.todo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class ToDoItemActivity
        extends AppCompatActivity
        implements View.OnClickListener {

    EditText itemTitle;
    EditText itemDesc;
    TextView dueDateText, dueTimeText;
    Button btnDatePicker, btnTimePicker;
    int dueDateDay, dueDateMonth, dueDateYear, dueHour, dueMinute;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private boolean existingTask;

    ToDoItemManager itemManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        itemTitle = (EditText) findViewById(R.id.to_do_title);
        itemDesc = (EditText) findViewById(R.id.to_do_item_desc);

        dueDateText = (TextView) findViewById(R.id.due_date_text);
        dueTimeText = (TextView) findViewById(R.id.due_time_text);

        btnDatePicker = (Button) findViewById(R.id.btn_date);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker = (Button) findViewById(R.id.btn_time);
        btnTimePicker.setOnClickListener(this);



        Intent intent = getIntent();

        if (intent.hasExtra("TASK_ID")) {
            existingTask = true;
            int id = intent.getIntExtra("TASK_ID", 1);


            //ToDoItem currentItem = DBHelper.getData(this, id);

            itemManager = new ToDoItemManager();
            Log.d("ID selected", String.valueOf(id));
            ToDoItem currentItem = itemManager.getItem(this, id);


            //TODO: set activity info with data from task
//            if (Utils.deviceAPI >= 23) {
//                itemDueTime.setHour(currentItem.getHour());
//                itemDueTime.setMinute(currentItem.getMinute());
//            } else {
//                itemDueTime.setCurrentHour(currentItem.getHour());
//                itemDueTime.setCurrentMinute(currentItem.getMinute());
//            }
            //ToDoItem currentItem = new ToDoItem();

            itemTitle.setText(id + " " + currentItem.getTitle());
            itemDesc.setText(currentItem.getDesc());
            //dueDateText.setText(currentItem.get);
            dueTimeText.setText(currentItem.getHour() + ":" + currentItem.getMinute());

            //TODO: Set date & time in dialogue
//            if (Utils.deviceAPI >= 23) {
//                itemDueTime.setHour(4);
//                itemDueTime.setMinute(30);
//            } else {
//                itemDueTime.setCurrentHour(1);
//                itemDueTime.setCurrentMinute(23);
//            }

            //TODO: set date
//            itemDueDate.setMinDate();

        } else {
            existingTask = false;


        }



    }

    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {
            //get current date

            //TODO: wrap in API level check
            //Get current date
            final Calendar c = Calendar.getInstance();

            //TODO: save date created
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            //TODO: Check API level and wrap this
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view,
                                      int year, int monthOfYear, int dayOfMonth) {
                    //ToDo: Save date
                    dueDateYear = year;
                    dueDateMonth = monthOfYear;
                    dueDateDay = dayOfMonth;

                    dueDateText.setText(monthOfYear + "/" + dayOfMonth + "/" + year);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (v == btnTimePicker) {

            //get current time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            dueHour = hourOfDay;
                            dueMinute = minute;
                            dueTimeText.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            //save new items/edited items
            case R.id.action_save:
                //TODO: save the fields
                //TODO: If an existing item was edited,
                // overwrite it instead of creating a new item
                ToDoItem currentItem = new ToDoItem();

                if (itemTitle.getText().toString().trim().length() > 0) {
                    currentItem.setTitle(itemTitle.toString());

                    if (itemDesc != null) {
                        currentItem.setDesc(itemDesc.toString());
                    } else {
                        currentItem.setDesc("");
                    }

                    //TODO: check API level, if not 23 use getCurrentHour/Minute
//                    currentItem.setHour(itemDueTime.getHour());
//                    currentItem.setMinute(itemDueTime.getMinute());

                    if (existingTask) {
                        //TODO: Update item

                    } else {
                        //TODO: Save new item
                    }

                    finish();
                } else {
                    String warning = "A title is required.";
                    Toast.makeText(ToDoItemActivity.this,
                            warning, Toast.LENGTH_LONG).show();
                }

                return true;

            case R.id.action_delete:
                //TODO: delete item from storage
                //TODO: If item is new, dont show delete option

            default:
                //action not recognized
                return super.onOptionsItemSelected(item);
        }

    }

}
