package com.placement.pesu.pesuplacement;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NotifTextListAdapter extends ArrayAdapter {
    NotifTextList notifTextList;
    private Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public NotifTextListAdapter( NotifTextList notifTextList, Context ctx) {

        super(ctx, R.layout.companydate_row_view, notifTextList.nList);
        this.notifTextList=notifTextList;
        this.context = ctx;
        this.preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        this.editor = preferences.edit();

        this.editor.clear();
        this.editor.apply();
    }

    public View getView(int index, View convertView, ViewGroup parent) {

        View v = convertView;
        NotifTextHolder holder = new NotifTextHolder();
        //First let's verify the convertView is not null
        if (convertView == null) {
            // This a new view we inflate the new layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.notif_row_view, null);
            // Now we can fill the layout with the right values
            TextView tvc = (TextView) v.findViewById(R.id.notifTextView);
            holder.notifTextView= tvc;
            v.setTag(holder);

        } else
            holder = (NotifTextHolder) v.getTag();


        String notifText = this.notifTextList.nList.get(index).notificationText;
        holder.notifTextView.setText(notifText);

        return v;
    }
}
