package com.placement.pesu.pesuplacement;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NotifTextList {
    List<NotifText> nList=new ArrayList<NotifText>();
    NotifTextListAdapter notifTextListAdapter;
    ListView lv;
    Context ctx;

    public NotifTextList(ListView lv, Context ctx){
        this.lv=lv;
        this.ctx=ctx;
    }
    public void addAdapter(NotifTextListAdapter notifTextListAdapter){
        this.notifTextListAdapter=notifTextListAdapter;
    }






    public void ResetAdapter(){

        this.notifTextListAdapter.notifTextList=this;
        this.lv.setAdapter(this.notifTextListAdapter);

    }
}
