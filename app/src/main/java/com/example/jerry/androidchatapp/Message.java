package com.example.jerry.androidchatapp;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;
import java.lang.Object;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jerry on 12/21/2017.
 */

public class Message {
    String username;
    String message;
    String chatroomName;
    long sendTime;
    Boolean Incognito;

    public Message(String username, long sendTime, String message, String chatroomName, Boolean Incognito) {

        this.username = username;
        this.sendTime = sendTime;
        this.message = message;
        this.chatroomName = chatroomName;
        this.Incognito = Incognito;

    }
    public JSONObject messageData(){
        //declare your JSONObjects here, as well as your Map
        //Map<String, JSONObject> = new HashMap<String, JSONObject>();
        JSONObject obj = new JSONObject();
        try{
            obj.put("Username", username);
            obj.put("Message", message);
            obj.put("Incognito", Incognito);

        }
        catch (Exception e){
            e.printStackTrace();
            Log.e("SOMETHINGWENTWRONG", "HERE");

    }
        return obj;
    }

    public String getChatroomName() {return chatroomName; }
    public Long getTimestamp() {return sendTime; }
    }