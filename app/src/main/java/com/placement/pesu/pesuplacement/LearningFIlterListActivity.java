package com.placement.pesu.pesuplacement;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class LearningFIlterListActivity extends AppCompatActivity implements android.widget.CompoundButton.OnCheckedChangeListener{

    ListView lv;
    ArrayList<String> filterNames=new ArrayList<String>();
    ChosenFilters chosenFilters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_filter_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        this.filterNames.add("College");
        this.filterNames.add("Company");
        this.filterNames.add("Tags");
        this.chosenFilters=new ChosenFilters(this.filterNames);





        // set visibility of list to INVISIBLE
        lv = (ListView) findViewById(R.id.listView);
        lv.setVisibility(View.INVISIBLE);

        FilterList collFilterList = new FilterList("College");

        FilterList compFilterList = new FilterList("Company");

        FilterList tagsFilterList = new FilterList("Tags");


        collFilterList.add("PES University");
        collFilterList.add("Other");


        //compFilterList.getFilterElements();
//        tagsFilterList.getFilterElements();

        compFilterList.add("Oracle");
        compFilterList.add("company 1");
        tagsFilterList.add("tags 1");
        tagsFilterList.add("tags 2");

        final ExperienceFilterAdapter collAdapter=new ExperienceFilterAdapter("College",collFilterList,this);
        final ExperienceFilterAdapter compAdapter=new ExperienceFilterAdapter("Company",compFilterList,this);
        final ExperienceFilterAdapter tagsAdapter=new ExperienceFilterAdapter("Tags",tagsFilterList,this);

        Button collfilterButton=(Button)findViewById(R.id.collFilterButton);
        Button compfilterButton=(Button)findViewById(R.id.compFilterButton);
        Button tagsfilterButton=(Button)findViewById(R.id.tagsFilterButton);


        collfilterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lv.setVisibility(View.INVISIBLE);
                lv.setAdapter(collAdapter);
                lv.setVisibility(View.VISIBLE);

            }
        });
        compfilterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lv.setVisibility(View.INVISIBLE);
                lv.setAdapter(compAdapter);
                lv.setVisibility(View.VISIBLE);

            }
        });
        tagsfilterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lv.setVisibility(View.INVISIBLE);
                lv.setAdapter(tagsAdapter);
                lv.setVisibility(View.VISIBLE);
            }
        });


        Button applyFilterButton = (Button)findViewById(R.id.applyFilterButton);
        applyFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(LearningFIlterListActivity.this, ExperienceListViewActivity.class);


                intent.putExtra("ChosenFilters",chosenFilters);

                startActivity(intent);
            }
        });

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        ListView lv=(ListView) findViewById(R.id.listView);
        int pos = lv.getPositionForView(buttonView);
        ExperienceFilterAdapter adapt=(ExperienceFilterAdapter)lv.getAdapter();
        String filterName=adapt.filterList.filterName;
        FilterList filterList=adapt.filterList;



        int index=-1;
        if(filterName=="Company")index=0;
        else if(filterName=="Tags")index=3;


        if (index!=-1 && pos != ListView.INVALID_POSITION) {
            String checkedText=filterList.fList.get(pos).item;
            TextView tv=(TextView) findViewById(R.id.countTextView);
            String text=(String)tv.getText();
            String[] text_array=text.split(" ");

            int count=Integer.parseInt(text_array[index]);

            if(isChecked){
                adapt.filterList.setChecked();
                adapt.editor.putString("FilterCheckboxId_"+adapt.filterName+"_"+Integer.toString(pos),"true");
                adapt.editor.commit();
                this.chosenFilters.addElement(filterName,checkedText);
            }
            else{
                adapt.filterList.unsetChecked();
                adapt.editor.putString("FilterCheckboxId_"+adapt.filterName+"_"+Integer.toString(pos),"false");
                adapt.editor.commit();
                this.chosenFilters.removeElement(filterName,checkedText);
            }
            count=adapt.filterList.checkedCount;
            text_array[index]=String.valueOf(count);
            text=TextUtils.join(" ",text_array);
            tv.setText(text);

        }
    }

}
