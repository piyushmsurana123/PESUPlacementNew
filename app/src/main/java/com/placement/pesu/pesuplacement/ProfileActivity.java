package com.placement.pesu.pesuplacement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class
ProfileActivity extends AppCompatActivity {

    String srn,name, emailId, cgpa, degreeCourse, branch, tenthDetails, twelvethDetails;
    int contactNo, yearOfGraduation;
    EditText srnInput;
    EditText nameInput;
    EditText emailIdInput;
    EditText cgpaInput;
    EditText degreeCourseInput;
    EditText branchInput;
    EditText tenthDetailsInput;
    EditText twelfthDetailsInput;
    EditText contactNoInput;
    EditText yearOfGraduationInput;
    Button uploadResume;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        srnInput = (EditText) findViewById(R.id.srnInput);
        nameInput = (EditText) findViewById(R.id.nameInput);
        emailIdInput = (EditText) findViewById(R.id.emailIdInput);
        cgpaInput = (EditText) findViewById(R.id.cgpaInput);
        degreeCourseInput = (EditText) findViewById(R.id.degreeCourseInput);
        branchInput = (EditText) findViewById(R.id.branchInput);
        tenthDetailsInput = (EditText) findViewById(R.id.tenthDetailsInput);
        twelfthDetailsInput = (EditText) findViewById(R.id.twelvethDetailsInput);
        contactNoInput = (EditText) findViewById(R.id.contactNoInput);
        yearOfGraduationInput = (EditText) findViewById(R.id.yearOfGraduationInput);
        
        uploadResume = (Button) findViewById(R.id.uploadResume);
        submitButton = (Button) findViewById(R.id.submitButton);

        uploadResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //function to take care of resume upload


            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                srn = srnInput.getText().toString();
                name = nameInput.getText().toString();
                emailId = emailIdInput.getText().toString();
                cgpa = cgpaInput.getText().toString();
                degreeCourse = degreeCourseInput.getText().toString();
                branch = branchInput.getText().toString();
                tenthDetails = tenthDetailsInput.getText().toString();
                twelvethDetails = twelfthDetailsInput.getText().toString();
                contactNo = Integer.valueOf(contactNoInput.getText().toString());
                yearOfGraduation = Integer.valueOf(yearOfGraduationInput.getText().toString());
                /*
                ProfileActivity postObject = new ProfileActivity();
                try {
                    //postObject.sendPost(srn,name,emailId,cgpa,degreeCourse,branch,tenthDetails,twelvethDetails,contactNo,yearOfGraduation);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //showToast(name);
                //showToast(emailId);
                */

            }
        });

    }



}