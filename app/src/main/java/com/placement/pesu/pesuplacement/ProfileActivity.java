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

    String emailId, srn, name, cgpa, degreeCourse, branch, tenthDetails, twelvethDetails;
    int contactNo, yearOfGraduation;
    EditText emailIdInput;
    EditText srnInput;
    EditText contactNoInput;
    EditText nameInput;
    EditText cgpaInput;
    EditText yearOfGraduationInput;
    EditText degreeCourseInput;
    EditText branchInput;
    EditText tenthDetailsInput;
    EditText twelfthDetailsInput;

    Button uploadResume;
    Button changePassword;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        emailIdInput = (EditText) findViewById(R.id.email);
        srnInput = (EditText) findViewById(R.id.usn);
        contactNoInput = (EditText) findViewById(R.id.contact);
        nameInput = (EditText) findViewById(R.id.name);
        yearOfGraduationInput = (EditText) findViewById(R.id.yog);
        cgpaInput = (EditText) findViewById(R.id.cgpa);
        degreeCourseInput = (EditText) findViewById(R.id.course);
        branchInput = (EditText) findViewById(R.id.branch);
        tenthDetailsInput = (EditText) findViewById(R.id.details_10th);
        twelfthDetailsInput = (EditText) findViewById(R.id.details_12th);

        uploadResume = (Button) findViewById(R.id.upload_resume_button);
        changePassword = (Button) findViewById(R.id.change_password_button)
        submitButton = (Button) findViewById(R.id.submit_details_button);

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



            }
        });

    }



}