package com.placement.pesu.pesuplacement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;


public class
ProfileActivity extends AppCompatActivity {

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
        changePassword = (Button) findViewById(R.id.change_password_button);
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

/*
                ArrayList<String> urlparameters = new ArrayList<String>();
                urlparameters.add(emailId);
                urlparameters.add(srn);
                urlparameters.add(contactNo);
                urlparameters.add(name);
                urlparameters.add(cgpa);
                urlparameters.add(yearOfGraduation);
                urlparameters.add(degreeCourse);
                urlparameters.add(branch);
                urlparameters.add(tenthDetails);
                urlparameters.add(twelvethDetails);

                HttpURLConnectionExample httpURLConnectionExample = new HttpURLConnectionExample();
                httpURLConnectionExample.sendPost("updateprofile",urlparameters);

 */
                JSONObject postData = new JSONObject();
                try {
                    postData.put("emailId", srnInput.getText().toString());
                    postData.put("usn", srnInput.getText().toString());
                    postData.put("contact",contactNoInput.getText().toString());
                    postData.put("name", nameInput.getText().toString());
                    postData.put("cgpa", cgpaInput.getText().toString());
                    postData.put("yog", yearOfGraduationInput.getText().toString());
                    postData.put("course", degreeCourseInput.getText().toString());
                    postData.put("branch", branchInput.getText().toString());
                    postData.put("details_10th", tenthDetailsInput.getText().toString());
                    postData.put("details_12th", twelfthDetailsInput.getText().toString());
                    Log.d("Initiating POST request", "finished collecting url parameters");
                    new SendProfileDetails().execute("https://ppdb-ep.herokuapp.com/updateprofile", postData.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }



}