package com.placement.pesu.pesuplacement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class random extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        Intent Extra = getIntent();
        String textView = Extra.getStringExtra("submitButton");

        TextView UserInput = (TextView) findViewById(R.id.randomTextview);
        UserInput.setText(textView);
    }

}
