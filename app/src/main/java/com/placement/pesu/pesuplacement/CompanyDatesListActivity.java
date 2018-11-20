package com.placement.pesu.pesuplacement;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

public class CompanyDatesListActivity extends AppCompatActivity {

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_dates_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set visibility of list to INVISIBLE
        lv = (ListView) findViewById(R.id.compDatesListView);

        CompanyDatesList companyDatesList=new CompanyDatesList(lv,this);
        CompanyDateListAdapter companyDateListAdapter=new CompanyDateListAdapter(companyDatesList,this);
        companyDatesList.addAdapter(companyDateListAdapter);

//        companyDatesList.add("ABC","123");
//        companyDatesList.add("DEF","567");

        lv.setAdapter(companyDateListAdapter);

        companyDatesList.getCompDateItems();



    }

}
