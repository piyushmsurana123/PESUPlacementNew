package com.placement.pesu.pesuplacement;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.text.Layout;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;

public class LearningChooseCollegeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_choose_college);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayout PESLayout=(LinearLayout) findViewById(R.id.learningPES);
        PESLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // ADD your action here
                Intent intent = new Intent(LearningChooseCollegeActivity.this, LearningFIlterListActivity.class);
                Bundle b = new Bundle();
                b.putString("college", "PES"); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);

            }
        });

        LinearLayout OtherLayout=(LinearLayout) findViewById(R.id.learningOther);
        OtherLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // ADD your action here
                Intent intent = new Intent(LearningChooseCollegeActivity.this, LearningFIlterListActivity.class);
                Bundle b = new Bundle();
                b.putString("college", "other"); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }
        });
    }

}
