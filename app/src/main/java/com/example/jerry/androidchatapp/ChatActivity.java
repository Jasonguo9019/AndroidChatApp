package com.example.jerry.androidchatapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ChatActivity extends AppCompatActivity {

    ListView listview;
    public static FirebaseDatabase dataBase;
    public static DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        dataBase = FirebaseDatabase.getInstance();
        ref = dataBase.getReference();

        setContentView(R.layout.chat_activity);
        Intent getIntent = getIntent();
        Log.e("test1", "hi");
        String chatRoomName = getIntent.getStringExtra("CHATROOM_NAME");
        Log.e("chatroom name", chatRoomName);
        String username = getIntent.getStringExtra("USER_NAME");
        Log.e("user name", username);
        Boolean Incognito = getIntent.getBooleanExtra("INCOGNITO_MODE", false);
        Log.e("Incognito Mode", String.valueOf(Incognito));
        ArrayList foodList = new ArrayList();

        foodList.add("Chips");
        foodList.add("Pizza");
        foodList.add("Hamburger");
        foodList.add("Bananas");
        foodList.add("Apples");

        listview = (ListView)findViewById(R.id.list_view);
        MyAdapter adapter = new MyAdapter(getApplicationContext(),foodList, ChatActivity.this);
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }
}



