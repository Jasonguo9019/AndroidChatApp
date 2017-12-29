package com.example.jerry.androidchatapp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

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
    public Map<String, JSONObject> messageData(){
        //declare your JSONObjects here, as well as your Map
        Map<String, JSONObject> messageMap= new HashMap<String, JSONObject>();

        try{
            JSONObject obj = new JSONObject();
            obj.put("username", username);
            obj.put("message", message);
            obj.put("Incognito", Incognito);

            JSONObject sendTimeObj = new JSONObject();
            sendTimeObj.put(Long.toString(sendTime), ojb);

            JSONObject chartObj = new JSONObject();
            chartObj.put(chartroomName, sendTimeObj);

            //All your JSON handling goes here.
            messageMap.put("myMessage", chartObj);
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e("SOMETHINGWENTWRONG", "HERE");
    }

        return messageMap;
    }}
