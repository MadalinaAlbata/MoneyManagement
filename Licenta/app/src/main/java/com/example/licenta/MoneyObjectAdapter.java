package com.example.licenta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MoneyObjectAdapter extends ArrayAdapter<MoneyObject> {
    private LayoutInflater mInflater;
    private ArrayList<MoneyObject> expenses;
    private int mViewResourcesId;
    private  int[] imgid;
    private int[] sageata_img;
    List list =new ArrayList();

    public MoneyObjectAdapter(Context context, int textViewResourceId, ArrayList<MoneyObject> expense, int[]imgid, int[] sageata) {
        super(context, textViewResourceId,expense);
        this.expenses=expense;
        this.imgid =imgid;
        this.sageata_img=sageata;
        mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourcesId=textViewResourceId;
    }

    public void add(MoneyObject object){
        list.add(object);
        super.add(object);
    }
    @Override
    public int getCount()
    {
        return super.getCount();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        convertView=mInflater.inflate(mViewResourcesId,null);
        MoneyObject expense = expenses.get(position);
        if(expense!=null){
            TextView data=(TextView)convertView.findViewById((R.id.data));
            TextView description=(TextView)convertView.findViewById(R.id.description);
            TextView type=(TextView)convertView.findViewById(R.id.type);
            TextView price=(TextView)convertView.findViewById(R.id.price);
            ImageView img=(ImageView)convertView.findViewById(R.id.iv_image);
            ImageView sageata=(ImageView)convertView.findViewById(R.id.sageata);
            sageata.setImageResource(sageata_img[0]);
            if(description!=null){
                description.setText(expense.getName());
            }
            if(price!=null){
                price.setText(expense.getPrice());
            }
            if(type!=null){
                type.setText(expense.getType());
                if(type.getText().toString().equals("Salary"))
                { sageata.setImageResource(sageata_img[1]);
                img.setImageResource(R.mipmap.salariu_icon);}
                if(type.getText().toString().equals("Coupons"))
                    sageata.setImageResource(sageata_img[1]);
                if(type.getText().toString().equals("Award"))
                    sageata.setImageResource(sageata_img[1]);
                if(type.getText().toString().equals("Bursary"))
                    sageata.setImageResource(sageata_img[1]);
                if(type.getText().toString().equals("Gift"))
                    sageata.setImageResource(sageata_img[1]);
                if(type.getText().toString().equals("Lottery"))
                    sageata.setImageResource(sageata_img[1]);
                if(type.getText().toString().equals("Refunds"))
                    sageata.setImageResource(sageata_img[1]);
                if(type.getText().toString().equals("Investments"))
                    sageata.setImageResource(sageata_img[1]);
                if(type.getText().toString().equals("Others"))
                    sageata.setImageResource(sageata_img[1]);

                if(type.getText().toString().equals("Grocery"))
                    img.setImageResource(R.mipmap.food);
                else     if(type.getText().toString().equals("Pet"))
                    img.setImageResource(R.mipmap.animale);
                else if(type.getText().toString().equals("Electricity"))
                    img.setImageResource(R.mipmap.curent_icon);
                else if(type.getText().toString().equals("Transport"))
                    img.setImageResource(R.mipmap.transport_icon);
                else if(type.getText().toString().equals("Education"))
                    img.setImageResource(R.mipmap.education_round);
                else if(type.getText().toString().equals("Toiletry"))
                    img.setImageResource(R.mipmap.toiletry_icon);
                else if(type.getText().toString().equals("Travel"))
                    img.setImageResource(R.mipmap.travel1_icon);
                else  if(type.getText().toString().equals("Shopping"))
                    img.setImageResource(R.mipmap.shopping_icon);
                else if(type.getText().toString().equals("Car"))
                    img.setImageResource(R.mipmap.car);
                else if(type.getText().toString().equals("Gym"))
                    img.setImageResource(R.mipmap.gym);
                else if(type.getText().toString().equals("Health"))
                img.setImageResource(R.mipmap.health);
                else if(type.getText().toString().equals("Rent"))
                    img.setImageResource(R.mipmap.rent_icon);
                else if(type.getText().toString().equals("Baby"))
                    img.setImageResource(R.mipmap.baby);
                else if(type.getText().toString().equals("Water"))
                    img.setImageResource(R.mipmap.water);
                else if(type.getText().toString().equals("Gas"))
                    img.setImageResource(R.mipmap.gas);
                else if(type.getText().toString().equals("Telephone"))
                    img.setImageResource(R.mipmap.telephone_icon);
                else if(type.getText().toString().equals("Social"))
                    img.setImageResource(R.mipmap.social_icon);
                else if(type.getText().toString().equals("Insurance"))
                    img.setImageResource(R.mipmap.insurance_icon);
                else if(type.getText().toString().equals("Others"))
                    img.setImageResource(R.mipmap.other_expense_icon);
                else if(type.getText().toString().equals("Grant"))
                    img.setImageResource(R.mipmap.bursa_icon);
                else if(type.getText().toString().equals("Coupons"))
                    img.setImageResource(R.mipmap.coupons_icon);
                else if(type.getText().toString().equals("Award"))
                    img.setImageResource(R.mipmap.award_icon);
                else if(type.getText().toString().equals("Gift"))
                    img.setImageResource(R.mipmap.gift_icon);
                else if(type.getText().toString().equals("Lottery"))
                    img.setImageResource(R.mipmap.lottery_icon);
                else if(type.getText().toString().equals("Other"))
                    img.setImageResource(R.mipmap.other_income_icon);
                else if(type.getText().toString().equals("Salary"))
                    img.setImageResource(R.mipmap.salariu_icon);
                else if(type.getText().toString().equals("Refunds"))
                    img.setImageResource(R.mipmap.refunds_icon);


            }
            if(data!=null){
                data.setText(expense.getData());
            }

        }
        return convertView;
    }
}
