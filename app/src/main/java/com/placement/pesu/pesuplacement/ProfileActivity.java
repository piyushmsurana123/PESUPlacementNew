package com.placement.pesu.pesuplacement;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ProfileActivity extends AppCompatActivity {

    private static final int REQUEST_WRITE_STORAGE = 112;
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

    Button logoutButton;

    Button submitButton;
    String imagepath;
    File sourceFile;
    int totalSize = 0;
    String FILE_UPLOAD_URL = "https://ppdb-ep.herokuapp.com/updateprofile";

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

        //uploadResume = (Button) findViewById(R.id.upload_resume_button);
        //changePassword = (Button) findViewById(R.id.change_password_button);
        logoutButton = (Button) findViewById(R.id.logout_button);
        submitButton = (Button) findViewById(R.id.submit_details_button);

        /*
        Boolean hasPermission = (ContextCompat.checkSelfPermission(ProfileActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(ProfileActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }else {
            Log.d("Permission","user message, No permission to upload");
        }



        uploadResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("document/*"); // intent.setType("video/*"); to select videos to upload
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select File"), 1);
            }
        });
        */

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
                    sp.edit().putBoolean("logged",false).apply();
                    FirebaseMessaging.getInstance().unsubscribeFromTopic(LoginActivity.USN);
                    Intent intent = new Intent(ProfileActivity.this,LoginActivity.class);
                    finish();
                    startActivity(intent);

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject postData = new JSONObject();
                try {
                    postData.put("email", emailIdInput.getText().toString());
                    postData.put("usn", srnInput.getText().toString());
                    postData.put("contact",contactNoInput.getText().toString());
                    postData.put("name", nameInput.getText().toString());
                    postData.put("score_gpa", cgpaInput.getText().toString());
                    postData.put("yog", yearOfGraduationInput.getText().toString());
                    postData.put("course", degreeCourseInput.getText().toString());
                    postData.put("branch", branchInput.getText().toString());
                    postData.put("details_10th", tenthDetailsInput.getText().toString());
                    postData.put("details_12th", twelfthDetailsInput.getText().toString());

                    /*
                    Log.d("check if File exists ", sourceFile.getAbsolutePath());
                    postData.put("upload_resume",sourceFile);
                    */
                    Log.d("Initiating POST request", "finished collecting url parameters");
                    new SendProfileDetails().execute("https://ppdb-ep.herokuapp.com/updateprofile", postData.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        String param = "formdata?usn="+LoginActivity.USN;

        MyGet asyncTask = (MyGet) new MyGet(new MyGet.AsyncResponse(){

            @Override
            public void processFinish(String output){
                //Here you will receive the result fired from async class
                //of onPostExecute(result) method.
                //Log.d("GET response",output);
                try {

                    JSONArray response = new JSONArray(output);
                    Log.d("hello",response.toString());
                    srnInput.setText(response.getJSONObject(0).getString("usn"));
                    srnInput.setFocusable(false);
                    emailIdInput.setText(response.getJSONObject(0).getString("email"));
                    //emailIdInput.setFocusable(false);
                    nameInput.setText(response.getJSONObject(0).getString("name"));
                    nameInput.setFocusable(false);
                    cgpaInput.setText(response.getJSONObject(0).getString("score_gpa"));
                    cgpaInput.setFocusable(false);

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }).execute(param);




    }


    /*
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_WRITE_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //reload my activity with permission granted or use the features what required the permission
                } else
                {
                    Toast.makeText(ProfileActivity.this, "You must give access to storage.", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK) {

            Uri selectedImageUri = data.getData();
            imagepath = getPath(selectedImageUri);
            System.out.println("inside onactivityresult , got path"+imagepath);
            sourceFile = new File(imagepath);
            Log.d("TAG1",sourceFile.getName());


        }
    }


    public String getPath(Uri uri) {
        Log.d("getPath","inside get path");
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    */
}