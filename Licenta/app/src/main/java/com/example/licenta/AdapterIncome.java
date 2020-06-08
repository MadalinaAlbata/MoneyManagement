package com.example.licenta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterIncome extends BaseAdapter {
private Context context;
ArrayList<Income> income;
private LayoutInflater inflater;

    public AdapterIncome(Context c,ArrayList<Income> income){
        context=c;
        this.income=income;

    }

    @Override
    public int getCount() {
        return income.size();
    }

    @Override
    public Object getItem(int position) {
        return income.get(position);
    }

    @Override
    public long getItemId(int position) {
        return income.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null)
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null)
            convertView=inflater.inflate(R.layout.grid_img,null);
        ImageView imageView=(ImageView)convertView.findViewById(R.id.income_grid_image);
        TextView textView=(TextView)convertView.findViewById(R.id.text_income_grid);
        imageView.setImageResource(income.get(position).getImg());
        textView.setText(income.get(position).getNume());
        return convertView;
    }
}
