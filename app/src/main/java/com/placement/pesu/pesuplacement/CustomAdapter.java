package com.placement.pesu.pesuplacement;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class CustomAdapter  extends BaseAdapter {

    private Context context;


    public CustomAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return MainActivity.modelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return MainActivity.modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder(); LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.company_item, null, true);


            holder.tvCompany = (TextView) convertView.findViewById(R.id.company);
            holder.tvCtc = (TextView) convertView.findViewById(R.id.ctc);
            holder.apply = (Button) convertView.findViewById(R.id.plus);
            holder.btn_details = (Button) convertView.findViewById(R.id.minus);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvCompany.setText(MainActivity.modelArrayList.get(position).getCompany());
        holder.tvCtc.setText(String.valueOf(MainActivity.modelArrayList.get(position).getCtc()));


        holder.apply.setTag(R.integer.apply_view, convertView);
        holder.apply.setTag(R.integer.apply_pos, position);
        holder.apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://www.google.co.in";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);

            }
        });

        holder.btn_details.setTag(R.integer.details_view, convertView);
        holder.btn_details.setTag(R.integer.details_pos, position);
        holder.btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View tempview = (View) holder.btn_details.getTag(R.integer.details_view);
                TextView tv = (TextView) tempview.findViewById(R.id.company);
                Integer pos = (Integer) holder.btn_details.getTag(R.integer.details_pos);

                String ctc = tv.getText().toString();

                //MainActivity.modelArrayList.get(pos).setCtc(number);

            }
        });

        return convertView;
    }

    private class ViewHolder {

        protected Button apply, btn_details;
        private TextView tvCompany, tvCtc;

    }

}