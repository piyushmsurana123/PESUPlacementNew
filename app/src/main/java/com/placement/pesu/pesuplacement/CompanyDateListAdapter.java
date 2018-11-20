package com.placement.pesu.pesuplacement;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CompanyDateListAdapter extends ArrayAdapter {
    CompanyDatesList companyDatesList;
    private Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public CompanyDateListAdapter( CompanyDatesList companyDatesList, Context ctx) {

        super(ctx, R.layout.companydate_row_view, companyDatesList.cList);
        this.companyDatesList=companyDatesList;
        this.context = ctx;
        this.preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        this.editor = preferences.edit();

        this.editor.clear();
        this.editor.apply();
    }

    public View getView(int index, View convertView, ViewGroup parent) {

        View v = convertView;
        CompanyDateHolder holder = new CompanyDateHolder();
        //First let's verify the convertView is not null
        if (convertView == null) {
            // This a new view we inflate the new layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.companydate_row_view, null);
            // Now we can fill the layout with the right values
            TextView tvc = (TextView) v.findViewById(R.id.companyNameTextView);
            TextView tvd = (TextView) v.findViewById(R.id.companyDateTextView);
            holder.companyNameTextView = tvc;
            holder.companyDateTextView = tvd;
            v.setTag(holder);

        } else
            holder = (CompanyDateHolder) v.getTag();


        String companyName = this.companyDatesList.cList.get(index).companyName;
        String companyDate = this.companyDatesList.cList.get(index).companyDate;
        holder.companyNameTextView.setText(companyName);
        holder.companyDateTextView.setText(companyDate);

        return v;
    }
}
