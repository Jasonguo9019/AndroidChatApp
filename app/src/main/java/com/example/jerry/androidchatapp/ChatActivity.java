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
    EditText messageInput;
    String userName;
    String chatRoomName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        dataBase = FirebaseDatabase.getInstance();
        ref = dataBase.getReference();

        setContentView(R.layout.chat_activity);
        messageInput = (EditText) findViewById(R.id.Message_text_input);
        Intent getIntent = getIntent();
        Log.e("test1", "hi");
        chatRoomName = getIntent.getStringExtra("CHATROOM_NAME");
        Log.e("chatroom name", chatRoomName);
        username = getIntent.getStringExtra("USER_NAME");
        Log.e("user name", username);
        Boolean Incognito = getIntent.getBooleanExtra("INCOGNITO_MODE", false);
        Log.e("Incognito Mode", String.valueOf(Incognito));




        listview = (ListView) findViewById(R.id.list_view);
        MyAdapter adapter = new MyAdapter(getApplicationContext(), listview, ChatActivity.this);
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public long getCurrentTime(){
      return new java.util.Date().getTime();
    }
    public void sendMessage(View view) {
        String message = messageInput.getText().toString();
        //Here we are instantiating our Message class by passing in variables through its constructor.
        Intent getIntent = getIntent();
        Message myMessage = new Message(userName, getCurrentTime(), message, chatRoomName, getIntent.getBooleanExtra("INCOGNITO_MODE",false));
        Map<String, JSONObject> jsonMap = myMessage.messageData();
        ref.child(chatRoomName).child(getCurrenTime()).setValue(jsonMap);


    }

    @Override
    protected void onStart(){
      super.onStart();
      DatabaseReference chatRef = ref.child(chatRoomName);

      chatRef.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnaptshot data : dataSnapshot.getChildren()){
                try{
                  JSONObject obj = new JSONObject(data.getValue().toString());
                  String message  = obj.getString(“Message”);
                  String userName = obj.getString("Username");
                  Boolean incognito = Boolean.valueOf(obj.getString(“Incognito”));

                  Message messageObject = new Message(username, data.getKey(), message, chatRoomName, incognito) ;

                  listview.add(messageObject);
                }catch(JSONException JE){
                  //Your Log error message goes here
                }
            }



          }
          @Override
          public void onCancelled(DatabaseError databaseError) {

          }
      });


    }



}
//user id 760190032490
