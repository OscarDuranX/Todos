package com.example.todos.iesebre.oscar.todos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by oscar on 20/11/15.
 */
public class CustomListAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<TodoItem> list;
    private final LayoutInflater layputInflater;

    public CustomListAdapter(Context context, ArrayList listData){
        this.context = context;
        this.list = listData;
        layputInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView ==null) {
            convertView = layputInflater.inflate(R.layout.todolistitem, null);
        }else{

        }

        TextView tv =(TextView) convertView.findViewById(R.id.todolistitemtext);

        //tv.setText("Prova");
        tv.setText(list.get(position).getName()+
                " p: "+list.get(position).getPriority()+
                " done: "+ list.get(position).isDone());

        return convertView;
    }
}
