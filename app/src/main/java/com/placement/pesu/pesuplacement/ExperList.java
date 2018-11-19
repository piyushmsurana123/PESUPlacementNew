package com.placement.pesu.pesuplacement;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class ExperList {

    List<Exper> eList=new ArrayList<Exper>();
    int gotElems;
    ExperienceListViewAdapter expLvAdapter;
    ListView lv;
    Context ctx;
    public ExperList( ListView lv,Context ctx){
        this.lv=lv;
        this.ctx=ctx;
    }
    public void addAdapter(ExperienceListViewAdapter experienceListViewAdapter){
        this.expLvAdapter=experienceListViewAdapter;
    }


    public void add (String item){
        int slice=item.length()-1;
        if(item.length()>50)slice=50;
        Exper e = new Exper(item,item.substring(0,slice)+"....");
        this.eList.add(e);
    }

    public void getExperItems(String param2){
        //this.experList=new ExperList();
        String[] getParams = {"gettaggeddata"+param2};
        Log.d("Getting tagged data","");
        System.out.println("Getting TaggedData ");
        MyGet asyncTask = (MyGet) new MyGet(new MyGet.AsyncResponse(){

            @Override
            public void processFinish(String output){
                //Here you will receive the result fired from async class
                //of onPostExecute(result) method.
                Log.d("Received TaggedData",output);
                System.out.println("Received TaggedData "+output);
                System.out.println("len(ExperList) "+ExperList.this.eList.size());
                if(output!=null && output.length()>0) {
                    output = output.substring(1, output.length() - 1);
                    String delim = "\\}\\{";
                    String[] arrayList = output.split(delim);
                    if (arrayList.length > 0) {

                        for (String e : arrayList) {
                            ExperList.this.add(e);
                        }
                    }
                    ExperList.this.ResetAdapter();
                }
            }
        }).execute(getParams);

    }


    public void ResetAdapter(){

        this.expLvAdapter.experList=this;
        this.lv.setAdapter(this.expLvAdapter);
    }

    public void getExperItemsPES(final ChosenFilters chosenFilters){
        String[] getParams = {"getexpdata"};


        Log.d("Getting tagged data","");
        System.out.println("Getting TaggedData ");
        MyGet asyncTask = (MyGet) new MyGet(new MyGet.AsyncResponse(){

            @Override
            public void processFinish(String output){
                //Here you will receive the result fired from async class
                //of onPostExecute(result) method.
                Log.d("Received TaggedData PES",output);
                System.out.println("Received TaggedData PES"+output);
                System.out.println("len(ExperList) "+ExperList.this.eList.size());
                if(output!=null && output.length()>0) {
                    try{
                        JSONArray jsonArray = new JSONArray(output);
                        System.out.println("Received TaggedData PES JSON"+jsonArray.toString());
                        jsonArray=ExperList.this.filterUnivResults(jsonArray,chosenFilters);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject element=jsonArray.getJSONObject(i);
                            String companyName=element.getString("company");
                            JSONArray tags=element.getJSONArray("tags");
                            String diffLevel=element.getString("How would you rate the difficulty level of the questions asked ? (Across rounds)");
                            String res=ExperList.this.getUnivExperString(companyName,tags,diffLevel);
                            ExperList.this.add(res);
                        }
                    }
                    catch(JSONException e){
                        Log.d("JSONException",e.toString());
                    }
                    ExperList.this.ResetAdapter();
                }
            }
        }).execute(getParams);


    }

    public String getUnivExperString(String companyName, JSONArray tags,String diffLevel){
        String res="";
        res=res+"Company : "+companyName+"\n";
        if(tags.length()>0)res=res+"Topics covered : ";
        for(int i=0;i<tags.length();i++){
            try {
                res=res+", "+tags.getString(i);
            } catch (JSONException e) {}
        }
        res=res+"\n";
        if(diffLevel.length()>0){
            res=res+"The difficulty level (on a scale from 1-10) : "+diffLevel+"\n";
        }
        return res;
    }

    public JSONArray filterUnivResults(JSONArray jsonArray, ChosenFilters chosenFilters){
        ArrayList<String> chosenCompanies=chosenFilters.cFilters.get("Company");
        ArrayList<String> chosenTags=chosenFilters.cFilters.get("Tags");

        JSONArray result=new JSONArray();

        for(int i=0;i<jsonArray.length();i++){
            try{
                JSONObject element=jsonArray.getJSONObject(i);
                String companyName=element.getString("company");
                if(chosenCompanies.contains(companyName)){
                    result.put(element);
                }
                else{
                    JSONArray tags=element.getJSONArray("tags");
                    ArrayList<Object> tagsList = new ArrayList<Object>();
                    try {
                        for (int k=0; k<tags.length();k++){
                            tagsList.add(tags.get(k));
                        }
                    } catch (JSONException e) {}
                    for(String tag : chosenTags){
                        if(tagsList.contains(tag)){
                            result.put(element);
                        }
                    }
                }

            }
            catch(JSONException e){
                Log.d("JSONException",e.toString());
            }

        }
        return result;
    }

}
