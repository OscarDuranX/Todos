package com.example.todos.iesebre.oscar.todos;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String SHARED_PREFERENCES_TODOS = "SP_TODOS";
    private static final String TODO_LIST= "todo_list";



    private Gson gson;
    public TodoArrayList tasks;
    private CustomListAdapter adapter;
    private SharedPreferences sharedPreferenceDades;

    private String taskTitle;


    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

   /* protected void onStop(){
        super.onStop;


        SharedPreferences.Editor editor = todos.edit();
        editor.putString(TODO_LIST, initial_json);
        editor.commit();
        todoList = todos.getString(TODO_LIST, null);
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)
                findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // findViewById(R.id.fab_add).setOnClickListener(this);
       // findViewById(R.id.fab_del).setOnClickListener(this);


        SharedPreferences todos = getSharedPreferences(SHARED_PREFERENCES_TODOS, 0);
        String todoList = todos.getString(TODO_LIST, null);

        if (todoList == null){
            String initial_json="[\n" +
                    "        {\"name\": \"Comprar llet\", \"done\": true, \"priority\": 2},\n" +
                    "        {\"name\": \"Comprar pa\", \"done\": true, \"priority\": 1},\n" +
                    "        {\"name\": \"Realitzar exercici\", \"done\": false, \"priority\": 3}\n" +
                    "        {\"name\": \"Estudiar\", \"done\": true, \"priority\": 4}\n" +
                    "        ]";
            SharedPreferences.Editor editor = todos.edit();
            editor.putString(TODO_LIST, initial_json);
            editor.commit();
            todoList = todos.getString(TODO_LIST, null);
        }

//        Log.d("prova",todoList);

//        Snackbar.make(view, todoList, Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

        //Toast.makeText(this, todoList, Toast.LENGTH_LONG).show();


        gson = new Gson();
        /*

        [
        {"name": "Comprar llet", "done": true, "priority": 2},
        {"name": "Comprar pa", "done": true, "priority": 1},
        {"name": "Realitzar exercici", "done": false, "priority": 3}
        {"name": "Estudiar", "done": true, "priority": 4}
        ]

        */


        Type arrayTodoList = new TypeToken<TodoArrayList>() {}.getType();
        TodoArrayList temp = gson.fromJson(todoList, arrayTodoList);

        if(temp != null){
            tasks = temp;
        }else{
            //Error TODO
        }

        ListView todoslv = (ListView) findViewById(R.id.todolistview);

        adapter = new CustomListAdapter(this, tasks);
        todoslv.setAdapter(adapter);








        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
//                startActivity(intent);

//                Snackbar.make(view, "Replace with your own action Prova!", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    View positiveAction;

    public void showAddTodoForm(View view) {

        EditText taskTitleText;

        taskTitle = "";

        MaterialDialog dialog
                = new MaterialDialog.Builder(this).
                title("Afegir tasca").
                customView(R.layout.form_add_task, true).
                negativeText("Cancel·ler").
                positiveText("Afegir").
                negativeColor(Color.parseColor("#2195F3")).
                positiveColor(Color.parseColor("#2195F3")).
                onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        final TodoItem todoItem = new TodoItem();
                        todoItem.setDone(false);
                        todoItem.setName(taskTitle);


                        RadioGroup taskPriority = (RadioGroup) dialog.findViewById(R.id.Priority);

                        switch (taskPriority.getCheckedRadioButtonId()) {
                            case R.id.task_priority_urgent:
                                todoItem.setPriority(1);
                                break;
                            case R.id.task_priority_important_not_urgent:
                                todoItem.setPriority(2);
                                break;
                            case R.id.task_priority_not_urgent:
                                todoItem.setPriority(3);
                                break;
                        }

                        tasks.add(todoItem);
                        adapter.notifyDataSetChanged();
                    }
                }).
                build();

        dialog.show();

        taskTitleText = (EditText) dialog.getCustomView().findViewById(R.id.task_title);
        positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
        positiveAction.setEnabled(false);

        taskTitleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                taskTitle = s.toString();
                positiveAction.setEnabled(taskTitle.trim().length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void showDeleteTodoForm(View view) {

        for (int i = tasks.size() - 1; i >= 0; i--) {
            if (tasks.get(i).isDone()) {
                tasks.remove(i);
            }
        }

        adapter.notifyDataSetChanged();
    }

    public void editTask(final int position) {

        final EditText taskNameText;
        RadioGroup checkPriority;

        MaterialDialog dialog = new MaterialDialog.Builder(this).
                title("Update Task").
                customView(R.layout.form_add_task, true).
                negativeText("Cancel").
                positiveText("Update").
                negativeColor(Color.parseColor("#ff3333")).
                positiveColor(Color.parseColor("#2196F3")).
                onPositive(new MaterialDialog.SingleButtonCallback() {

                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {

                        tasks.get(position).setName(taskTitle);
                        if (tasks.get(position).isDone() == true) {
                            tasks.get(position).setDone(true);
                        } else {
                            tasks.get(position).setDone(false);
                        }

                        RadioGroup taskPriority = (RadioGroup) dialog.findViewById(R.id.Priority);

                        switch (taskPriority.getCheckedRadioButtonId()) {
                            case R.id.task_priority_urgent:
                                tasks.get(position).setPriority(1);
                                break;
                            case R.id.task_priority_important_not_urgent:
                                tasks.get(position).setPriority(2);
                                break;
                            case R.id.task_priority_not_urgent:
                                tasks.get(position).setPriority(3);
                                break;
                        }

                        adapter.notifyDataSetChanged();
                    }
                }).


                build();

        dialog.show();

        taskNameText = (EditText) dialog.getCustomView().findViewById(R.id.task_title);
        taskNameText.append(tasks.get(position).getName());
        taskTitle = taskNameText.getText().toString();

        positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
        positiveAction.setEnabled(false);

        taskNameText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                taskTitle = s.toString();
                positiveAction.setEnabled(taskTitle.trim().length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        checkPriority = (RadioGroup) dialog.getCustomView().findViewById(R.id.Priority);
        if (tasks.get(position).getPriority() == 1) {
            checkPriority.check(R.id.task_priority_urgent);
        }
        if (tasks.get(position).getPriority() == 2) {
            checkPriority.check(R.id.task_priority_important_not_urgent);
        }
        if (tasks.get(position).getPriority() == 3) {
            checkPriority.check(R.id.task_priority_not_urgent);
        }

        checkPriority.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup taskPriority, int checkedId) {
                positiveAction.setEnabled(true);
            }
        });







    }
}
