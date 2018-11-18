package com.placement.pesu.pesuplacement;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ExperienceListViewAdapter extends ArrayAdapter {

    ExperList experList;
    private Context context;



    public ExperienceListViewAdapter(ExperList experList, Context ctx) {
        super(ctx, R.layout.exper_row_view,experList.eList);
        this.experList = experList;
        this.context = ctx;
    }

    public View getView(int index, View convertView, ViewGroup parent) {

        View v = convertView;
        FilterHolder holder = new FilterHolder();
        //First let's verify the convertView is not null
        if (convertView == null) {
            // This a new view we inflate the new layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.exper_row_view, null);
            // Now we can fill the layout with the right values
            TextView tv = (TextView) v.findViewById(R.id.experEntryTextView);
            holder.filterEntryTextView= tv;
            v.setTag(holder);

        }
        else
            holder = (FilterHolder) v.getTag();


        String text=this.experList.eList.get(index).experTitle;
        holder.filterEntryTextView.setText(text);


        return v;
    }
}
