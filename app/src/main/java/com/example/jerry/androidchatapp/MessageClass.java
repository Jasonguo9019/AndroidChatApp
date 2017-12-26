package com.example.jerry.androidchatapp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jerry on 12/21/2017.
 */

public class MessageClass {
    String username;
    String message;
    String chatroomName;
    long sendTime;
    Boolean Incognito;

    public MessageClass(String username, long sendTime, String message, String chatroomName, Boolean Incognito) {

        this.username = username;
        this.sendTime = sendTime;
        this.message = message;
        this.chatroomName = chatroomName;
        this.Incognito = Incognito;

    }
    public Map<String, JSONObject> messageData(){
        //declare your JSONObjects here, as well as your Map
        Map<String, JSONObject> userMap= new HashMap<String, JSONObject>();
       JSONObject obj = new JSONObject();
        try{
            obj.put("username", username);
            obj.put("sendTime",sendTime);
            obj.put("message", message);
            obj.put("chatroomName",chatroomName);
            obj.put("Incognito", Incognito);

            //All your JSON handling goes here.
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e("SOMETHINGWENTWRONG", "HERE");
    }

        return userMap;
    }}
