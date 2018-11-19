package com.placement.pesu.pesuplacement;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        textView = findViewById(R.id.hello);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //**********************
//        String[] getParams = {"listcomp"};
//        MyGet asyncTask = (MyGet) new MyGet(new MyGet.AsyncResponse(){
//
//            @Override
//            public void processFinish(String output){
//                //Here you will receive the result fired from async class
//                //of onPostExecute(result) method.
//                //Log.d("hello",output);
//                try{
//                    JSONArray jsonArray= new JSONArray(output);
//                    Log.d("jsonObject",jsonArray.toString());
//
//                }
//                catch(JSONException e){
//                    Log.d("JSONException",e.toString());
//
//                    e.printStackTrace();
//                }
//                textView.setText(output);
//            }
//        }).execute(getParams);



        //**********************

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_companies) {

        }
        else if (id == R.id.nav_statistics) {

        }
        else if (id == R.id.nav_learning_topics) {
            Intent intent = new Intent(MainActivity.this, LearningChooseCollegeActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

class MyGet extends AsyncTask<String, Void, String> {

    public String response;
    // you may separate this or combined to caller class.
     interface AsyncResponse {
        void processFinish(String output);
    }

    private AsyncResponse delegate = null;

    MyGet(AsyncResponse delegate){
        this.delegate = delegate;
    }

    protected String doInBackground(String... params) {
        HttpURLConnectionExample httpURLConnectionExample = new HttpURLConnectionExample();
        response="";
        try {
            response = httpURLConnectionExample.sendGet(params[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}
