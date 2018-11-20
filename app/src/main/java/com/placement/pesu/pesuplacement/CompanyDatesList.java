package com.placement.pesu.pesuplacement;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;
import android.view.View;
public class CompanyDatesList {

    List<CompanyDate> cList=new ArrayList<>();
   CompanyDateListAdapter  companyDateListAdapter;
    ListView lv;
    Context ctx;

    public CompanyDatesList(ListView lv, Context ctx){
        this.lv=lv;
        this.ctx=ctx;
        this.cList.clear();

    }
    public void addAdapter(CompanyDateListAdapter companyDateListAdapter){
        this.companyDateListAdapter=companyDateListAdapter;
    }
    public void add(String companyName,String companyDate){
        this.cList.add(new CompanyDate(companyName,companyDate));
    }


    public void getCompDateItems(){
        String[] getParams = {"compdata"};
        MyGet asyncTask = (MyGet) new MyGet(new MyGet.AsyncResponse(){

            @Override
            public void processFinish(String output){
                //Here you will receive the result fired from async class
                //of onPostExecute(result) method.
                Log.d("Received output",output);
                try {
                    JSONArray jsonArray = new JSONArray(output);

                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        if(jsonObject.has("Date")){
                            String compName=jsonObject.getString("Company");
                            String compDate=jsonObject.getString("Date");
                            if(compName.length()>0 && compDate.length()>0)
                            CompanyDatesList.this.add(compName,compDate);
                        }

                    }
                } catch (JSONException e) {
                    Log.d("JSONException", e.toString());
                    e.printStackTrace();
                }
                CompanyDatesList.this.ResetAdapter();
            }
        }).execute(getParams);


    }


    public void ResetAdapter(){

        this.companyDateListAdapter.companyDatesList=this;
        this.lv.setAdapter(this.companyDateListAdapter);

    }
}
