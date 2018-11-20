package com.placement.pesu.pesuplacement;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {

    SharedPreferences sp;
    String res;
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        sp = getSharedPreferences("login", MODE_PRIVATE);
        jsonArray = null;

        if(sp.getBoolean("logged", false)){
            res = getIntent().getStringExtra("student");
            try {
                jsonArray = new JSONArray(res);
                for(int i=0; i<jsonArray.length();i++){
                    Log.d("hello", jsonArray.getString(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
