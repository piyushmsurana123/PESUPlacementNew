package com.placement.pesu.pesuplacement;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class CompanyDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String companyDetailsJsonString = getIntent().getStringExtra("company_details");
        JSONObject companyDetailsJson = null;
        JSONArray branchArray = null;
        try {
            companyDetailsJson = new JSONObject(companyDetailsJsonString);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_company_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TextView nameView = findViewById(R.id.company_name);
        TextView dateView = findViewById(R.id.company_date);
        TextView eligibilityView = findViewById(R.id.company_eligibility);
        TextView addressView = findViewById(R.id.company_address);
        TextView applyDateView = findViewById(R.id.company_apply_date);
        TextView branchView = findViewById(R.id.company_branch);
        TextView ctcView = findViewById(R.id.company_ctc);
        TextView fteIntView = findViewById(R.id.company_fte_int);
        TextView jobDesView = findViewById(R.id.company_job_des);
        TextView noRoundsView = findViewById(R.id.company_no_rounds);
        TextView testPlatformView = findViewById(R.id.company_test_platform);

        try {
            branchArray = companyDetailsJson.getJSONArray("Branch");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String branchText = "";
        for(int i=0;i<branchArray.length()-1;i++)
        {
            try {
                branchText+=branchArray.getString(i)+", ";
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            branchText+=branchArray.getString(branchArray.length()-1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            nameView.setText(nameView.getText() + companyDetailsJson.getString("Company"));
            dateView.setText(dateView.getText() + companyDetailsJson.getString("Date"));
            eligibilityView.setText(eligibilityView.getText() + companyDetailsJson.getString("Eligibilty"));
            branchView.setText(branchView.getText() + branchText);
            ctcView.setText(ctcView.getText() + companyDetailsJson.getString("CTC"));
            fteIntView.setText(fteIntView.getText() + companyDetailsJson.getString("FTE/Internship"));
            jobDesView.setText(jobDesView.getText() + companyDetailsJson.getString("Job Description"));
            noRoundsView.setText(noRoundsView.getText() + companyDetailsJson.getString("Number of Rounds"));
            testPlatformView.setText(testPlatformView.getText() + companyDetailsJson.getString("Online Test Platform"));
            applyDateView.setText(applyDateView.getText() + companyDetailsJson.getString("Last Date to submit"));
            addressView.setText(addressView.getText() + companyDetailsJson.getString("Address"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
