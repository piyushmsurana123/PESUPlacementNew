package com.placement.pesu.pesuplacement;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SingleExperienceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_experience);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Exper exper = (Exper) getIntent().getParcelableExtra("Exper");

        TextView tv = (TextView) findViewById(R.id.singleExperTextView);
        tv.setText(exper.experText);


        Button singleExperChangeFilterButton=(Button) findViewById(R.id.singleExperChangeFilterButton);
        singleExperChangeFilterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SingleExperienceActivity.this, LearningFIlterListActivity.class);
                startActivity(intent);

            }
        });
        Button singleExperBackButton=(Button) findViewById(R.id.singleExperBackButton);
        singleExperChangeFilterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SingleExperienceActivity.this, ExperienceListViewActivity.class);
                startActivity(intent);

            }
        });
    }
}
