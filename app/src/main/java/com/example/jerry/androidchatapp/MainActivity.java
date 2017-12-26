package com.example.jerry.androidchatapp;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText chatroomNameInput;
    EditText Usernameinput;
    Switch IncModeinput;
    ListView listview;




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            chatroomNameInput = (EditText) findViewById(R.id.Chatroom_text_input);
            Usernameinput = (EditText) findViewById(R.id.username_text_input);
            IncModeinput = (Switch) findViewById(R.id.Incognito_text_input);


        }

        public void enterChat(View view) {

            String chatRoomName = chatroomNameInput.getText().toString();
            String username = Usernameinput.getText().toString();
            Boolean Incognito = IncModeinput.isChecked();
            Intent intent = new Intent(MainActivity.this, ChatActivity.class);


            intent.putExtra("CHATROOM_NAME", chatRoomName);
            intent.putExtra("USER_NAME", username);
            intent.putExtra("INCOGNITO_MODE", Incognito);
            startActivity(intent);


        }
    }
