package com.placement.pesu.pesuplacement;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    Button register;
    AutoCompleteTextView emailView;
    EditText passwordView;
    EditText confirmPasswordView;
    EditText usnView;
    EditText nameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.email_register_button);
        emailView = findViewById(R.id.email);
        passwordView = findViewById(R.id.password);
        confirmPasswordView = findViewById(R.id.password_confirm);
        usnView = findViewById(R.id.usn);
        nameView = findViewById(R.id.name);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });
    }

    private void attemptRegister() {


        if(nameView.getText().toString().equals("")) {
            nameView.setError(getString(R.string.error_field_required));
            nameView.requestFocus();
        }
        else if(usnView.getText().toString().equals("")) {
            usnView.setError(getString(R.string.error_field_required));
            usnView.requestFocus();
        }
        else if(emailView.getText().toString().equals("")) {
            emailView.setError(getString(R.string.error_field_required));
            emailView.requestFocus();
        }
        else if(passwordView.getText().toString().equals("")) {
            passwordView.setError(getString(R.string.error_field_required));
            passwordView.requestFocus();
        }
        else if(confirmPasswordView.getText().toString().equals("")) {
            confirmPasswordView.setError(getString(R.string.error_field_required));
            confirmPasswordView.requestFocus();
        }

        else if(!passwordView.getText().toString().equals(confirmPasswordView.getText().toString())){
            confirmPasswordView.setError(getString(R.string.error_password_no_match));
            confirmPasswordView.requestFocus();
        }

        else {
            UserRegisterTask userRegisterTask = new UserRegisterTask(nameView.getText().toString(), usnView.getText().toString(), emailView.getText().toString(), passwordView.getText().toString());
            userRegisterTask.execute();
        }
    }


    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private final String mName;
        private final String mUsn;

        UserRegisterTask(String name, String usn, String email, String password) {
            mEmail = email;
            mPassword = password;
            mName = name;
            mUsn = usn;
        }

        @Override
        protected Boolean doInBackground(Void... params) {


            JSONObject postData = new JSONObject();
            try {
                postData.put("name", mName);
                postData.put("usn", mUsn);
                postData.put("password", mPassword);
                postData.put("email", mEmail);
                HttpURLConnectionExample httpURLConnectionExample = new HttpURLConnectionExample();
                String response = httpURLConnectionExample.sendPost("signup", postData.toString());
                if(response.toString().contains("Exists")){
                    usnView.setError("USN already exists. Please try again");
                    usnView.requestFocus();
                    return false;
                }

                else if(response.toString().contains("Saved")){

                        return true;
                }
                else
                    return false;

            }
            catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }


        }

        @Override
        protected void onPostExecute(final Boolean success) {


            if (success) {
                Toast.makeText(getBaseContext(),"Succesfully Registered",
                        Toast.LENGTH_SHORT).show();
                finish();

            } else {
                nameView.setError("Invalid Request...Try again ");
                nameView.requestFocus();
            }
        }

    }
}

