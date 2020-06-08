package com.example.licenta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class AdapterImg extends BaseAdapter {
    private int[] img;
    private Context context;
    private LayoutInflater inflater;
    ArrayList<Integer> img1 ;//=new ArrayList<Integer>();

    public AdapterImg(Context c, ArrayList<Integer> img)
    {
        context=c;
        this.img1=img;
    }
    public void remove(Integer e){
        img1.remove(e);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return img1.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if(inflater==null){
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null){
            convertView=inflater.inflate(R.layout.grid_img,null);
        }
      //  ImageView imageView=convertView.findViewById(R.id.img_grid);
       // imageView.setImageResource(img1.get(position));
        return convertView;

    }

/*
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class AdapterImg extends ArrayAdapter<String> {
    private Context context;
    private  String[] mobileValues;

    public AdapterImg(Context context,int resource, String[] mobileValues) {
        super(context, resource, mobileValues);
        this.context = context;
        this.mobileValues = mobileValues;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.grid_img, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.text_grid);
            textView.setText(mobileValues[position]);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.img_grid);

            String mobile = mobileValues[position];

            if (mobile.equals("a")) {
                imageView.setImageResource(R.drawable.film);
            } else if (mobile.equals("c")) {
                imageView.setImageResource(R.drawable.food);
            } else if (mobile.equals("b")) {
                imageView.setImageResource(R.drawable.health);
            } else {
                imageView.setImageResource(R.drawable.medical);
            }

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    public void removeItem(int pos){
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(mobileValues));
        arrayList.remove(pos);
        mobileValues = new String[arrayList.size()];
        arrayList.toArray(mobileValues);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mobileValues.length;
    }

    @Override
    public String getItem(int position) {
        return mobileValues[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
*/
}
