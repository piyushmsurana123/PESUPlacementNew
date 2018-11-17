package com.placement.pesu.pesuplacement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ExperienceListViewActivity extends AppCompatActivity {

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv=(ListView)findViewById(R.id.experListView);

        ChosenFilters chosenFilters = (ChosenFilters) getIntent().getParcelableExtra("ChosenFilters");


        final ExperList experList=new ExperList();


        experList.add("Given two Linked Lists, create union and intersection lists that contain union and intersection of the elements present in the given lists. Order of elments in output lists doesn’t matter.\n" +
                "Example:\n" +
                "Method 1 (Simple)\n" +
                "Following are simple algorithms to get union and intersection lists respectively.\n" +
                "Intersection (list1, list2)\n" +
                "Initialize result list as NULL. Traverse list1 and look for its each element in list2, if the element is present in list2, then add the element to result.\n" +
                "Union (list1, list2):\n" +
                "Initialize result list as NULL. Traverse list1 and add all of its elements to the result.");
        experList.add("Write an efficient program to find the sum of contiguous subarray within a one-dimensional array of numbers which has the largest sum. \n" +
                "\n" +
                "Kadane’s Algorithm:\n" +
                "Explanation:\n" +
                "Simple idea of the Kadane’s algorithm is to look for all positive contiguous segments of the array (max_ending_here is used for this).  And keep track of maximum sum contiguous segment  among all positive segments (max_so_far is used for this).  Each time we get a positive sum compare it with max_so_far and update max_so_far if it is greater than max_so_far\n" +
                "Program:");
        experList.add("The eligibility criteria was 7.5+ CGPA with no standing arrears . Around 200 were eligible .\n" +
                "Round 1: The first round was hosted in Hackerrank . It consisted of 20 MCQ’s(aptitude, algorithms, DS, O/P and programming questions), 2 Coding questions ( 1 – Easy and 1 – Medium ) and 3 DBMS queries.\n" +
                "Around 16 were shortlisted to the next round.\n" +
                "Round 2: The first round was followed by series of 2 Tech interviews and 1 HR . In the first tech round, I was asked a lot of DBMS (especially queries based on join) and puzzle questions – (geekforgeeks puzzle section is more than enough). The Second tech round consisted of DS and DBMS questions and went on for a hour . Both the interview were elimination round .");

        final   ExperienceListViewAdapter expLvAdapter=new ExperienceListViewAdapter(experList,this);

        lv.setAdapter(expLvAdapter);

        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(ExperienceListViewActivity.this, SingleExperienceActivity.class);
                intent.putExtra("Exper",experList.eList.get(position));
                startActivity(intent);

            }
        });

        Button changeFilter=(Button) findViewById(R.id.changeFilterButton);
        changeFilter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExperienceListViewActivity.this, LearningFIlterListActivity.class);
                startActivity(intent);

            }
        });


    }

}
