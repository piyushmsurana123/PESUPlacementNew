package com.placement.pesu.pesuplacement;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Iterator;

import org.json.JSONObject;
import org.json.JSONArray;
import java.util.Set;
import java.util.HashSet;

public class FilterList {

    List<FilterItem> fList=new ArrayList<FilterItem>();
    int checkedCount;
    String filterName;
    ExperienceFilterAdapter filLvAdapter;
    ListView lv;
    Context ctx;
    public FilterList(String filterName,ListView lv, Context ctx){
        this.checkedCount=0;
        this.filterName=filterName;
        this.lv=lv;
        this.ctx=ctx;
        this.fList.clear();

    }
    public int elementExists(String item){
        int i=0;
        while(i<this.fList.size()){
            if(this.fList.get(i).toString().equals(item)){
                return 1;
            }
            i=i+1;
        }
        return 0;
    }

    public void addAdapter(ExperienceFilterAdapter experienceFilterAdapter){
        this.filLvAdapter=experienceFilterAdapter;
    }
    public void setChecked(){
        this.checkedCount++;
    }

    public void unsetChecked(){
        if(this.checkedCount>0)this.checkedCount--;
    }

    public void add(String item){
        FilterItem f = new FilterItem(item);
        this.fList.add(f);
    }
    public String getUrl(){
        String listString="";
        if(this.filterName=="Company")listString="listcomp";
        else if(this.filterName=="Tags")listString="listtags";
        return listString;
    }
    public void getFilterItems(){
        String[] getParams = {this.getUrl()};
        MyGet asyncTask = (MyGet) new MyGet(new MyGet.AsyncResponse(){

            @Override
            public void processFinish(String output){
                //Here you will receive the result fired from async class
                //of onPostExecute(result) method.
                Log.d("Received output",output);
                try {
                    JSONArray jsonArray = new JSONArray(output);

                    for(int i=0;i<jsonArray.length();i++){
                        String elem=jsonArray.getString(i);
                        if(FilterList.this.elementExists(elem)==0){
                            FilterList.this.add(elem);
                        }

                    }
                    FilterList.this.sortFilterList();
                } catch (JSONException e) {
                    Log.d("JSONException", e.toString());
                    e.printStackTrace();
                }
                FilterList.this.ResetAdapter();
            }
        }).execute(getParams);


    }
    public void sortFilterList(){
        ArrayList<String>res=new ArrayList<>();
        for(FilterItem f :this.fList){
            res.add(f.item);
        }
        Set<String> uniqueRes = new HashSet<String>(res);
        List<String> list = new ArrayList<String>(uniqueRes);
        this.fList.clear();
        for(String s:list){
            this.add(s);
        }
        Collections.sort(this.fList,new Comparator<FilterItem>() {

            public int compare(FilterItem a, FilterItem b) {
                return a.item.compareToIgnoreCase(b.item);
            }
        });
    }
    public void getFilterItemsPES(){
        String[] getParams = {"getexpdata"};


        MyGet asyncTask = (MyGet) new MyGet(new MyGet.AsyncResponse(){

            @Override
            public void processFinish(String output){
                String fname=FilterList.this.filterName.toLowerCase();
                //Here you will receive the result fired from async class
                //of onPostExecute(result) method.
                Log.d("Received output",output);
                try {
                    JSONArray jsonArray = new JSONArray(output);
                    if (fname.equals("company")) {
                        for(int i=0;i<jsonArray.length();i++){
                            String elem=jsonArray.getJSONObject(i).getString("company");
                            if(FilterList.this.elementExists(elem)==0){
                                FilterList.this.add(elem);
                            }
                        }
                    }
                    else if(fname.equals("tags")){
                        for(int i=0;i<jsonArray.length();i++){
                            JSONArray tArr=jsonArray.getJSONObject(i).getJSONArray("tags");
                            for(int j=0;j<tArr.length();j++){
                                String elem=tArr.getString(j);
                                if(FilterList.this.elementExists(elem)==0){
                                    FilterList.this.add(elem);
                                }
                            }
                        }
                    }

                    FilterList.this.sortFilterList();
                } catch (JSONException e) {
                    Log.d("JSONException", e.toString());
                    e.printStackTrace();
                }
                FilterList.this.ResetAdapter();
            }
        }).execute(getParams);


    }

    public void ResetAdapter(){

        this.filLvAdapter.filterList=this;
        this.lv.setAdapter(this.filLvAdapter);
        lv.setVisibility(View.INVISIBLE);

    }
}
