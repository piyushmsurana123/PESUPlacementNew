package com.placement.pesu.pesuplacement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Set;

public class ExperienceListViewActivity extends AppCompatActivity {

    ListView lv;
    ExperList experList;
    ExperienceListViewAdapter expLvAdapter;
    String college;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv=(ListView)findViewById(R.id.experListView);

        ChosenFilters chosenFilters = (ChosenFilters) getIntent().getParcelableExtra("ChosenFilters");
        String param2;
        final String college=getIntent().getExtras().getString("college");

        experList=new ExperList(lv,this);
        expLvAdapter=new ExperienceListViewAdapter(this.experList,this);
        experList.addAdapter(expLvAdapter);

        if(college.equals("other")){
            param2 =chosenFilters.getFilterParams();
            experList.getExperItems(param2);
        }
        else if(college.equals("PES")){
            experList.getExperItemsPES(chosenFilters);
        }
        lv.setAdapter(expLvAdapter);


        System.out.println("len(ExperList) "+this.experList.eList.size());

        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(ExperienceListViewActivity.this, SingleExperienceActivity.class);
                Bundle b = new Bundle();
                b.putString("college", college); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                intent.putExtra("Exper",ExperienceListViewActivity.this.experList.eList.get(position));
                startActivity(intent);

            }
        });

        Button changeFilter=(Button) findViewById(R.id.changeFilterButton);
        changeFilter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExperienceListViewActivity.this, LearningFIlterListActivity.class);
                Bundle b = new Bundle();
                b.putString("college", college); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);

            }
        });


    }



}
