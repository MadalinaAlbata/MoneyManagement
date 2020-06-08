package com.example.licenta;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;

import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Calendar;

public class StatisticsYears extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    BarChart barChart;
    DatabaseHelper db;
    Spinner spinner;
    Button buton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_years);
        db=new DatabaseHelper(this);
     //   buton=(Button)findViewById(R.id.vezi_luna);
        //anul curenta
       // String an=new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());
        Calendar calendar=Calendar.getInstance();
        int an=calendar.get(Calendar.YEAR);//Integer.parseInt(an);

        spinner=(Spinner)findViewById(R.id.spinner_ani);
        ArrayAdapter<CharSequence> adapter_spinner=ArrayAdapter.createFromResource(this,R.array.years,android.R.layout.simple_spinner_item);
        adapter_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter_spinner);
        int spineer_position=adapter_spinner.getPosition(an+"");
        spinner.setSelection(spineer_position);
        spinner.setOnItemSelectedListener(this);
    /*    buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLunaCurenta();
            }
        });*/


    }

    private void goToLunaCurenta() {
        Intent intent=new Intent(this,StatisticsCurrentMonth.class);
        startActivity(intent);
    }

    private void grafic(String an){
        barChart=(BarChart)findViewById(R.id.barchart);
        Description description=new Description();
        description.setText("");
        barChart.setDescription(description);

   /*     XAxis xAxis= barChart.getXAxis();
        xAxis.setValueFormatter(new MyValueFormatter(month));
*/
    /*   barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(true);
*/
        BarDataSet barDataSet1=new BarDataSet(barEntries1(an),"expense");
        barDataSet1.setColor(Color.rgb(255,36,36));
        BarDataSet barDataSet2=new BarDataSet(barEntries2(an),"income");
        barDataSet2.setColor(Color.rgb(24,148,30));
        BarData data=new BarData(barDataSet1,barDataSet2);
        barChart.setData(data);
        String[] months=new String[]{"JAN","FEB","MAR","APR","MAY","JUNE","JULY","AUG","SEP","OCT","NOV","DEC"};
        final XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(months));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        xAxis.setLabelCount(12);
        barChart.setVisibleXRangeMaximum(12);

        barChart.setDrawGridBackground(true);
        barChart.setDrawBorders(true);
        barChart.setBorderColor(Color.BLACK);
        barChart.setBorderWidth(3f);

        barChart.getAxisRight().setEnabled(false);
        float groupsace=0.30f;
        float barspace=0.1f;
        float barwidth=0.25f;
        data.setBarWidth(barwidth);
        barChart.getXAxis().setAxisMinimum(0);
        barChart.getXAxis().setAxisMaximum(12);//(0+barChart.getBarData().getGroupWidth(groupsace,barspace)*12);
        barChart.getAxisLeft().setAxisMinimum(0);
        //  barChart.setScaleEnabled(true);
        barChart.groupBars(0,groupsace,barspace);
        barChart.animateXY(800,800);
        barChart.invalidate();

    }

    private void monthStatistics() {
        Intent intent=new Intent(this, Month_Statistics_Expense.class);
         startActivity(intent);
    }

    private ArrayList<BarEntry> barEntries1(String an)
    { float expense_ian,expense_feb,expense_mar,expense_apr,expense_mai,expense_iunie,expense_iulie,expense_august,expense_sept,
         expense_oct,expense_noiemb,expense_dec;
         expense_ian=db.sumaExpenseIncome_dupaAn("Expense","01",an);
         expense_feb=db.sumaExpenseIncome_dupaAn("Expense","02",an);
         expense_mar=db.sumaExpenseIncome_dupaAn("Expense","03",an);
         expense_apr=db.sumaExpenseIncome_dupaAn("Expense","04",an);
         expense_mai=db.sumaExpenseIncome_dupaAn("Expense","05",an);
         expense_iunie=db.sumaExpenseIncome_dupaAn("Expense","06",an);
         expense_iulie=db.sumaExpenseIncome_dupaAn("Expense","07",an);
         expense_august=db.sumaExpenseIncome_dupaAn("Expense","08",an);
         expense_sept=db.sumaExpenseIncome_dupaAn("Expense","09",an);
         expense_oct=db.sumaExpenseIncome_dupaAn("Expense","10",an);
         expense_noiemb=db.sumaExpenseIncome_dupaAn("Expense","11",an);
         expense_dec=db.sumaExpenseIncome_dupaAn("Expense","12",an);
         ArrayList<BarEntry> barEntries1=new ArrayList<>();
         barEntries1.add(new BarEntry(1f, expense_ian));
         barEntries1.add(new BarEntry(2, expense_feb));
         barEntries1.add(new BarEntry(3, expense_mar));
         barEntries1.add(new BarEntry(4, expense_apr));
         barEntries1.add(new BarEntry(5, expense_mai));
         barEntries1.add(new BarEntry(6, expense_iunie));
         barEntries1.add(new BarEntry(7, expense_iulie));
         barEntries1.add(new BarEntry(8, expense_august));
         barEntries1.add(new BarEntry(9, expense_sept));
         barEntries1.add(new BarEntry(10, expense_oct));
         barEntries1.add(new BarEntry(11, expense_noiemb));
         barEntries1.add(new BarEntry(12, expense_dec));
         return barEntries1;
     }


    private ArrayList<BarEntry> barEntries2(String  an)
    {   float income_ian,income_feb,income_mar,income_apr,income_mai,income_iun,income_iul,income_aug, income_sept,income_oct,income_noiemb,income_dec;
        income_ian=db.sumaExpenseIncome_dupaAn("Income","01",an);
        income_feb=db.sumaExpenseIncome_dupaAn("Income","02",an);
        income_mar=db.sumaExpenseIncome_dupaAn("Income","03",an);
        income_apr=db.sumaExpenseIncome_dupaAn("Income","04",an);
        income_mai=db.sumaExpenseIncome_dupaAn("Income","05",an);
        income_iun=db.sumaExpenseIncome_dupaAn("Income","06",an);
        income_iul=db.sumaExpenseIncome_dupaAn("Income","07",an);
        income_aug=db.sumaExpenseIncome_dupaAn("Income","08",an);
        income_sept=db.sumaExpenseIncome_dupaAn("Income","09",an);
        income_oct=db.sumaExpenseIncome_dupaAn("Income","10",an);
        income_noiemb=db.sumaExpenseIncome_dupaAn("Income","11",an);
        income_dec=db.sumaExpenseIncome_dupaAn("Income","12",an);
        ArrayList<BarEntry> barEntries2=new ArrayList<>();
        barEntries2.add(new BarEntry(1, income_ian));
        barEntries2.add(new BarEntry(2, income_feb));
        barEntries2.add(new BarEntry(3, income_mar));
        barEntries2.add(new BarEntry(4, income_apr));
        barEntries2.add(new BarEntry(5, income_mai));
        barEntries2.add(new BarEntry(6, income_iun));
        barEntries2.add(new BarEntry(7, income_iul));
        barEntries2.add(new BarEntry(8, income_aug));
        barEntries2.add(new BarEntry(9, income_sept));
        barEntries2.add(new BarEntry(10, income_oct));
        barEntries2.add(new BarEntry(11, income_noiemb));
        barEntries2.add(new BarEntry(12, income_dec));
        return barEntries2;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String an=parent.getItemAtPosition(position).toString();
        grafic(an);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
