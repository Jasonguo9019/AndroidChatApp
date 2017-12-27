package com.example.jerry.androidchatapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jerry on 11/29/2017.
 */

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> myArrayList;
    Activity activity;
    public MyAdapter(Context context, ArrayList <String> array, Activity activity) {
        super();
        this.context = context;
        this.myArrayList = array;
        this.activity = activity;
    }
    @Override
    public int getCount(){
        return myArrayList.size();
    }


    @Override
    public Object getItem(int position) {
        return getItem(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String message = myArrayList.get(position);
        LayoutInflater inflater =
                (LayoutInflater)activity.getApplicationContext().getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
        View myCellLayout = inflater.inflate(R.layout.layoutinflater, null);
        TextView messageTextView = (TextView) myCellLayout.findViewById(R.id.cell);
        messageTextView.setText(message);
        convertView = myCellLayout;

        return convertView;
    }
}


