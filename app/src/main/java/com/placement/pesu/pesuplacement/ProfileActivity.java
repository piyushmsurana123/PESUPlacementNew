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

                ProfileActivity postObject = new ProfileActivity();
                try {
                    postObject.sendPost(srn,name,emailId,cgpa,degreeCourse,branch,tenthDetails,twelvethDetails,contactNo,yearOfGraduation);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //showToast(name);
                //showToast(emailId);

            }
        });

    }


    public void sendPost(String srn, String name, String emailId, String cgpa, String degreeCourse, String branch, String tenthDetails, String twelvethDetails, Integer contactNo, Integer yearOfGraduation ) throws Exception {

        String USER_AGENT = "Mozilla/5.0";
        String url = "https://ppdb-ep.herokuapp.com/login";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "srn="+srn+"&name="+name+"&emailId="+emailId+"&cgpa"+cgpa+"&degreeCourse"+degreeCourse+"&branch="+branch+"&tenthDetails="+tenthDetails+"&twelvethDetails="+twelvethDetails+"&contactNo"+contactNo.toString()+"&yearOfGraduation="+yearOfGraduation.toString();

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

}