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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    ListView listview;
    public static FirebaseDatabase dataBase;
    public static DatabaseReference ref;
    EditText messageInput;
    String userName;
    String chatRoomName;
    ArrayList messages = new ArrayList();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        dataBase = FirebaseDatabase.getInstance();
        ref = dataBase.getReference();

        setContentView(R.layout.chat_activity);

        intent= getIntent();

        listview = (ListView) findViewById(R.id.list_view);
        MyAdapter adapter = new MyAdapter(getApplicationContext(), messages, ChatActivity.this);
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public long getCurrentTime(){

        return new java.util.Date().getTime();
    }
    public void sendMessage(View view) {

        messageInput = (EditText) findViewById(R.id.Message_text_input);
        String message = messageInput.getText().toString();
        Log.e("test1", "hi");
        chatRoomName = intent.getStringExtra("CHATROOM_NAME");
        Log.e("chatroom name", chatRoomName);
        userName = intent.getStringExtra("USER_NAME");
        Log.e("user name", userName);
        Boolean incognito = intent.getBooleanExtra("INCOGNITO_MODE", false);
        Log.e("Incognito Mode", String.valueOf(incognito));

        //Here we are instantiating our Message class by passing in variables through its constructor.

        Message myMessage = new Message(userName, getCurrentTime(), message, chatRoomName, incognito);
        JSONObject messageObj = myMessage.messageData();


        Map<String, JSONObject> jsonMap = new Gson().fromJson(messageObj.toString(), new TypeToken<HashMap<String, JSONObject>>() {}.getType());
        ref.child(chatRoomName).child(Long.toString(getCurrentTime())).setValue(jsonMap);


    }

    @Override
    protected void onStart(){
      super.onStart();
      DatabaseReference chatRef = ref.child(chatRoomName);
      if(chatRef == null) {
          return;
      }

      chatRef.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot data : dataSnapshot.getChildren()){
                try{
                  JSONObject obj = new JSONObject(data.getValue().toString());
                  String message  = obj.getString("Message");
                  String userName = obj.getString("Username");
                  Boolean incognito = Boolean.valueOf(obj.getString("Incognito"));

                  Message messageObject = new Message(userName, Long.parseLong(data.getKey()), message, chatRoomName, incognito) ;

                  messages.add(messageObject.toString());
                }catch(JSONException JE){
                    Log.e("SOMETHINGWENTWRONG", "HERE");
                }
            }



          }
          @Override
          public void onCancelled(DatabaseError databaseError) {

          }
      });


    }



}

