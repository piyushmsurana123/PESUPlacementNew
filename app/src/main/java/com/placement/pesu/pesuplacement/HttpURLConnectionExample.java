package com.placement.pesu.pesuplacement;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
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
    public void sendPost(String endpoint, ArrayList<String> urlparameters) throws Exception {
        URL url = new URL("https://ppdb-ep.herokuapp.com/");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        if(endpoint.equals("updateprofile")){
            System.out.println("in update profile");
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("email", urlparameters.get(0))
                    .appendQueryParameter("usn", urlparameters.get(1))
                    .appendQueryParameter("contact", urlparameters.get(2))
                    .appendQueryParameter("name", urlparameters.get(3))
                    .appendQueryParameter("cgpa", urlparameters.get(4))
                    .appendQueryParameter("yog", urlparameters.get(5))
                    .appendQueryParameter("course", urlparameters.get(6))
                    .appendQueryParameter("branch",urlparameters.get(7))
                    .appendQueryParameter("details_10th", urlparameters.get(8))
                    .appendQueryParameter("details_12th", urlparameters.get(9));

            String query = builder.build().getEncodedQuery();
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
        }
        conn.connect();

    }

}

