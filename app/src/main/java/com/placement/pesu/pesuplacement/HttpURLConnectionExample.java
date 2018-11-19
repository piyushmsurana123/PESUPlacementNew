package com.placement.pesu.pesuplacement;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;


public class HttpURLConnectionExample {

    private final String USER_AGENT = "Mozilla/5.0";
    private final String URL = "https://ppdb-ep.herokuapp.com/";


    // HTTP GET request
    public String sendGet(String data) throws Exception {

        String url = URL+ data;

        URL obj = new URL(url);
        HttpsURLConnection urlConnection  = (HttpsURLConnection) obj.openConnection();

        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("User-Agent", USER_AGENT);
        urlConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        int responseCode = urlConnection.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
        return response.toString();

    }

    // HTTP POST request
    public String sendPost(String data1, String params) throws Exception {

        String url = URL + data1;
        String response="";
        URL obj = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();

        //add request header
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
        httpURLConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        httpURLConnection.setRequestProperty("Content-type", "application/json");

        httpURLConnection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
        Log.d("params",params);
        wr.writeBytes(params);
        wr.flush();
        wr.close();

        InputStream in = httpURLConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(in);

        int inputStreamData = inputStreamReader.read();
        while (inputStreamData != -1) {
            char current = (char) inputStreamData;
            inputStreamData = inputStreamReader.read();
            response += current;
        }

        return response;


    }

}

