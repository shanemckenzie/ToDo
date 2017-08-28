package mckenzie.todo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ToDoListActivity extends AppCompatActivity {

    public ArrayList<ToDoItem> items;
    //public ToDoItem[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        //items = new ToDoItem[4];
//        ToDoItemManager itemManager = new ToDoItemManager();
//
//        //TODO: Make async w/ loading progress bar
//        DBHelper dbHelper = new DBHelper(this);

//        ToDoItem example = new ToDoItem();
//        example.setTitle("Example id 2");
//        example.setDesc("Example id");
//        example.setHour(2);
//        example.setMinute(10);

        //dbHelper.insertTask(example);

        AsyncLoadItems loadItems = new AsyncLoadItems(this);
        loadItems.execute();


    }

    private class AsyncLoadItems extends AsyncTask<Void, Void, Void> {
        private Context mContext;

        public AsyncLoadItems (Context context) {
            mContext = context;
        }

        @Override
        protected Void doInBackground(Void... params) {

            ToDoItemManager itemManager = new ToDoItemManager();

            //DBHelper dbHelper = new DBHelper(mContext);

            itemManager.loadItems(mContext);
            items = ToDoItemManager.items;
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            ListView toDoList = (ListView) findViewById(R.id.to_do_list);
            toDoList.setAdapter(new ToDoListAdapter(mContext, items));
            toDoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Log.d("ID of cell selected: ", String.valueOf(items.get(position).getId()));

                    Intent intent = new Intent(ToDoListActivity.this, ToDoItemActivity.class);
                    //TODO: put extra item id
                    intent.putExtra("TASK_ID", items.get(position).getId());

                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_new_task:
                //create a new item
                Intent intent = new Intent(ToDoListActivity.this, ToDoItemActivity.class);
                //startActivity(intent);

                startActivityForResult(intent, 1);

                return true;

            default:
                //action not recognized
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            Intent refresh = new Intent(this, ToDoListActivity.class);
            startActivity(refresh);
            this.finish();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        AsyncLoadItems loadItems = new AsyncLoadItems(this);
        loadItems.execute();

    }

    //TODO: Delete item on check box selection



}

