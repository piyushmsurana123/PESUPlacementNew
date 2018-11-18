package com.placement.pesu.pesuplacement;

import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.json.JSONObject;
import org.json.JSONArray;

public class FilterList {

    List<FilterItem> fList=new ArrayList<FilterItem>();
    int checkedCount;
    String filterName;
    public FilterList(String filterName){
        this.checkedCount=0;
        this.filterName=filterName;

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
    public String getUrl(String baseUrl){
        String list="";
        if(this.filterName=="Company")list="listcomp";
        else if(this.filterName=="Tags")list="listtags";
        return baseUrl+list;
    }
}
