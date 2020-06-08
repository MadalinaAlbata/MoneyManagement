package com.example.licenta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class IncomeMenuGrid extends AppCompatActivity {
    GridView gridView;
    AdapterIncome adapter;
    String[] nume={"Salary","Grant","Coupons","Award","Gift","Lottery","Refunds","Other"
            };
    Integer[] img={R.mipmap.salariu,R.mipmap.bursa,R.mipmap.coupons ,
            R.mipmap.award,R.mipmap.gift_round,R.mipmap.lottery_round ,R.mipmap.refunds_round,
    R.mipmap.other_income_round};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_menu_grid);
        gridView=(GridView)findViewById(R.id.grid_view_income);
        adapter=new AdapterIncome(IncomeMenuGrid.this,this.getIncome());
        gridView.setAdapter(adapter);
        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent= new Intent(getApplicationContext(),Add_category_income.class);
                Income income= (Income) parent.getItemAtPosition(position);//=tv.getText().toString();
                String text=income.getNume();
                intent.putExtra("add_income",text);
                startActivity(intent);
            }
        });
    }

    private ArrayList<Income> getIncome()
    {   ArrayList<Income> income=new ArrayList<Income>();
        Income income1;
        for(int i=0;i<nume.length;i++)
        {   income1=new Income(nume[i],img[i]);
            income.add(income1); }

        return income;}
}
