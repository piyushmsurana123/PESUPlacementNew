package com.placement.pesu.pesuplacement;

import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.CheckBox;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.content.Context;

public class ExperienceFilterAdapter extends ArrayAdapter {


    FilterList filterList;
    String filterName;
    private Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    int first;

    public ExperienceFilterAdapter(String filterName, FilterList filterList, Context ctx) {

        super(ctx, R.layout.filter_row_view, filterList.fList);
        this.filterName = filterName;
        this.filterList = filterList;
        this.context = ctx;
        this.preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        this.editor = preferences.edit();
        this.first = 1;

        this.editor.clear();
        this.editor.apply();
    }

    public View getView(int index, View convertView, ViewGroup parent) {

        View v = convertView;
        FilterHolder holder = new FilterHolder();
        //First let's verify the convertView is not null
        if (convertView == null) {
            // This a new view we inflate the new layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.filter_row_view, null);
            // Now we can fill the layout with the right values
            TextView tv = (TextView) v.findViewById(R.id.filterEntryTextView);
            CheckBox chk = (CheckBox) v.findViewById(R.id.filterEntryCheckbox);
            holder.filterEntryTextView = tv;
            holder.filterEntryCheckbox = chk;
            v.setTag(holder);

        } else
            holder = (FilterHolder) v.getTag();


        String text = this.filterList.fList.get(index).item;
        holder.filterEntryTextView.setText(text);

        CheckBox chk = (CheckBox) v.findViewById(R.id.filterEntryCheckbox);
        holder.filterEntryCheckbox = chk;
        chk.setOnCheckedChangeListener((LearningFIlterListActivity) context);
        if (this.first == 1) {
            chk.setChecked(false);
            this.first = 0;
        } else {
            boolean isChecked = Boolean.valueOf(preferences.getString("FilterCheckboxId_" + this.filterName + "_" + Integer.toString(index), "false"));
            chk.setChecked(isChecked);
        }
        return v;
    }
}
