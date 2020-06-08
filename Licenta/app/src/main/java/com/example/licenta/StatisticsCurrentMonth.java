package com.example.licenta;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class StatisticsCurrentMonth extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    LineChart lineChart;
    DatabaseHelper db;
    TextView tv;
    TextView balance,expense,income;
    Button buton_expense,buton_income,buton_Veziluni,buton_selectLuna,compare_years;
    Spinner spinner;
    RelativeLayout rl_expense,rl_income;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_current_month);
        db=new DatabaseHelper(this);
        lineChart=(LineChart)findViewById(R.id.lineChart);
        compare_years=(Button)findViewById(R.id.compare_years);
        tv=(TextView) findViewById(R.id.id1);
     //   buton_expense=(Button)findViewById(R.id.button_valoare_expense);
      //  buton_income=(Button)findViewById(R.id.button_valoare_income);
        buton_Veziluni=(Button) findViewById(R.id.vezi_luni);
      //  buton_selectLuna=(Button) findViewById(R.id.selectLuna);
        rl_expense=(RelativeLayout)findViewById(R.id.relative_layout_expense);
        rl_income=(RelativeLayout)findViewById(R.id.relative_layout_income) ;
        balance=(TextView)findViewById(R.id.valoare_balanta);
        expense=(TextView)findViewById(R.id.valoare_expense);
        income=(TextView)findViewById(R.id.valoare_income);
        //luna curenta
        String luna=new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
        Calendar calendar=Calendar.getInstance();
        int spineer_position=Integer.parseInt(luna);

        spinner=(Spinner)findViewById(R.id.spinner_luna);
        ArrayAdapter<CharSequence> adapter_spinner=ArrayAdapter.createFromResource(this,R.array.months,android.R.layout.simple_spinner_item);
        adapter_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter_spinner);
        spinner.setSelection(spineer_position-1);
        spinner.setOnItemSelectedListener(this);


        rl_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToIncome();
            }
        });
        rl_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToExpense();
            }
        });
        buton_Veziluni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMonths();
            }
        });
        compare_years.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCompareYears();
            }
        });
    }

    private void goToCompareYears() {
        Intent intent=new Intent(this,Statistics2Years.class);
        startActivity(intent);

    }


    private void goToMonths() {
        Intent intent=new Intent(this, StatisticsYears.class);
        startActivity(intent);
    }


    private void goToExpense() {
        Intent intent=new Intent(this,Month_Statistics_Expense.class);
        String luna_s= spinner.getSelectedItem().toString();
        String luna=luna_0(luna_s);
        intent.putExtra("luna",luna);
        startActivity(intent);
    }

    private void goToIncome() {
        Intent intent=new Intent(this,Month_Statistics_Income.class);
        String luna_s= spinner.getSelectedItem().toString();
        String luna=luna_0(luna_s);
       // Toast.makeText(StatisticsCurrentMonth.this, luna, Toast.LENGTH_LONG).show();
        intent.putExtra("luna",luna);
        startActivity(intent);
    }
    private void grafic(String luna){
        LineDataSet lineDataSet1=new LineDataSet(values(luna),"Balance");
       // lineChart.getLegend().setTextColor(Color.BLUE);
        lineDataSet1.setColor(Color.BLUE);
       // lineDataSet1.setFillColor(Color.BLUE);
        ArrayList<ILineDataSet> dataSets=new ArrayList<>();
        dataSets.add(lineDataSet1);
        LineData data=new LineData(dataSets);
        lineChart.setData(data);
        lineChart.invalidate();
        Description description=new Description();
        description.setText("");
        lineChart.setDescription(description);


        // lineChart.setVisibleXRangeMaximum(25);

        lineChart.setDrawGridBackground(true);
        lineChart.setDrawBorders(true);
        lineChart.setBorderColor(Color.BLACK);
        lineChart.setBorderWidth(1f);
   //     lineChart.setBackgroundResource(R.drawable.background_statistici);
        lineChart.setDrawGridBackground(false);
       lineChart.getAxisRight().setEnabled(false);
      //  lineChart.getAxisLeft().setEnabled(false);
     //   lineChart.getXAxis().setEnabled(false);
        lineDataSet1.setColor(Color.BLUE);



        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setLineWidth(1.5f);
        lineDataSet1.setCircleColor(Color.RED);

        lineDataSet1.setDrawValues(false);
        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(true);
    }

    private ArrayList<Entry> values(String l){//l=01;
        ArrayList<Entry> values=new ArrayList<>();
        int luna_int=Integer.parseInt(l);
         Calendar calendar=Calendar.getInstance();
        int month=Calendar.FEBRUARY;
       // Toast.makeText(StatisticsCurrentMonth.this, month+"FEBRUARIE", Toast.LENGTH_LONG).show();

        if(luna_int==1)
            month=(Calendar.JANUARY);
        else if(luna_int==2)
            month=(Calendar.FEBRUARY);
        else if(luna_int==3)
            month=(Calendar.MARCH);
        else if(luna_int==4)
            month=(Calendar.APRIL);
        else if(luna_int==5)
            month=(Calendar.MAY);
        else if(luna_int==6)
            month=(Calendar.JUNE);
        else if(luna_int==7)
            month=(Calendar.JULY);
        else if(luna_int==8)
            month=(Calendar.AUGUST);
        else if(luna_int==9)
            month=(Calendar.SEPTEMBER);
        else if(luna_int==10)
            month=(Calendar.OCTOBER);
        else if(luna_int==11)
            month=(Calendar.NOVEMBER);
        else if (luna_int==12)
            month=(Calendar.DECEMBER);

        int year=calendar.get(Calendar.YEAR);
        int ziua=1;
        Calendar calendar1=new GregorianCalendar(year,month,ziua);
        int daysMonth=calendar1.getActualMaximum(Calendar.DAY_OF_MONTH);//zilele lunii


        String zi;
        float balanta=0;
        float suma_income_luna=db.sumaExpenseIncome_dupaAn("Income",l,year+"");
        float suma_expense_luna=db.sumaExpenseIncome_dupaAn("Expense",l,year+"");
            for(int i=0;i<daysMonth;i++){
                if(i+1<10){
                    zi=0+String.valueOf(i+1);}
                    else zi=String.valueOf(i+1);
                float pret_zi_expense=db.getPret_dupaZiLuna(zi,l,"Expense",year+"");
                float pret_zi_income=db.getPret_dupaZiLuna(zi,l,"Income",year+"")+balanta;
                balanta=pret_zi_income-pret_zi_expense;
                values.add(new Entry(i+1,balanta));
            }
            balance.setText(String.valueOf(balanta));
            expense.setText(String.valueOf(suma_expense_luna));
            income.setText(String.valueOf(suma_income_luna));
        return values;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        String luna=luna_0(text);
        grafic(luna);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public String luna_0(String l){

        if(l.equals("January"))
            return "01";
        else if (l.equals("February"))
            return "02";
        else if (l.equals("March"))
            return"03";
        else if (l.equals("April"))
            return"04";
        else if (l.equals("May"))
            return "05";
        else if (l.equals("June"))
            return"06";
        else if(l.equals("July"))
            return"07";
        else if(l.equals("August"))
            return"08";
        else if(l.equals("September"))
            return"09";
        else if(l.equals("October"))
            return"10";
        else if(l.equals("November"))
            return"11";
        else return "12";
    }



}
