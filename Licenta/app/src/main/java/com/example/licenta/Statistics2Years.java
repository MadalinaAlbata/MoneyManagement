package com.example.licenta;

import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Statistics2Years extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    BarChart barChart;
    DatabaseHelper db;
    Spinner spinner1,spinner2;
    int[]color1=new int[]{Color.rgb(213,0,0),Color.rgb(24,148,30)};//{Color.GREEN,Color.RED};//
    int[]color2=new int[]{Color.rgb(241 ,53,135),Color.rgb(32,201,49)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_2_years);
        db=new DatabaseHelper(this);
        Calendar calendar=Calendar.getInstance();
        final int an=calendar.get(Calendar.YEAR);
        spinner1=(Spinner)findViewById(R.id.spinner_an1);
        spinner2=(Spinner)findViewById(R.id.spinner_an2);
       // String ani[]=getResources().getStringArray(R.array.years);
       // ArrayAdapter<String>adapter_spinner1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ani);
        ArrayAdapter<CharSequence> adapter_spinner1=ArrayAdapter.createFromResource(this,R.array.years,android.R.layout.simple_spinner_item);
        adapter_spinner1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter_spinner1);
        ArrayAdapter<CharSequence> adapter_spinner2=ArrayAdapter.createFromResource(this,R.array.years,android.R.layout.simple_spinner_item);
        adapter_spinner2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter_spinner2);
        int spineer_position2=adapter_spinner2.getPosition(an+"");
        spinner2.setSelection(spineer_position2);//de verificat
        spinner2.setOnItemSelectedListener(this);
        int spineer_position1=adapter_spinner1.getPosition((an-1)+"");
        spinner1.setSelection(spineer_position1);
        spinner1.setOnItemSelectedListener(this);
        grafic(bardataset1(spineer_position1+""),bardataset2(spineer_position2+""));


    }


    private BarDataSet bardataset1(String an){
        BarDataSet barDataSet1=new BarDataSet(barEntries1(an),"");
        return barDataSet1;

    }

    private BarDataSet bardataset2(String an){
        BarDataSet barDataSet2=new BarDataSet(barEntries2(an),"");
        return barDataSet2;

    }

    private void grafic(BarDataSet set1,BarDataSet set2)
    {
        barChart=(BarChart)findViewById(R.id.barchart2);
        Description description=new Description();
        description.setText("");
        barChart.setDescription(description);
        barChart.setExtraOffsets(0,5,0,8);

        Legend legend=barChart.getLegend();
        LegendEntry legendEntry1=new LegendEntry("expense first year",Legend.LegendForm.SQUARE,10f,2f,null,Color.rgb(213,0,0));
        LegendEntry legendEntry2=new LegendEntry("income first year",Legend.LegendForm.SQUARE,10f,2f,null,Color.rgb(24,148,30));
        LegendEntry legendEntry3=new LegendEntry("expense second year",Legend.LegendForm.SQUARE,10f,2f,null,Color.rgb(241 ,53,135));
        LegendEntry legendEntry4=new LegendEntry("income second year",Legend.LegendForm.SQUARE,10f,2f,null,Color.rgb(32,201,49));
        barChart.getLegend().setWordWrapEnabled(true);

        legend.setCustom(new LegendEntry[]{legendEntry1,legendEntry2,legendEntry3,legendEntry4});
        BarDataSet barDataSet1=set1;//new BarDataSet(barEntries1(an1),"bar set");
        barDataSet1.setColors(color1);
      //  barDataSet1.setValueTextSize(11f);
        BarDataSet barDataSet2=set2;//new BarDataSet(barEntries2(an2),"XSDSD");
        barDataSet2.setColors(color2);

        //barDataSet2.setValueTextSize(11f);
      //  barDataSet1.setDrawValues(false);
       // barDataSet2.setDrawValues(false);
     //  barChart.getXAxis().setPosition(XAxis.XAxisPosition.TOP_INSIDE);
        BarData barData=new BarData(barDataSet1,barDataSet2);
        barChart.setData(barData);

        String[] months=new String[]{"JAN","FEB","MAR","APR","MAY","JUNE","JULY","AUG","SEP","OCT","NOV","DEC"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(months));

        xAxis.setCenterAxisLabels(true);

        xAxis.setLabelCount(12);
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(12);

        barChart.getAxisRight().setEnabled(false);
        float groupsace=0.10f;
        float barspace=0.02f;
        float barwidth=0.43f;
        barData.setBarWidth(barwidth);
        barChart.getXAxis().setAxisMinimum(0);

         barChart.getXAxis().setAxisMaximum(12);//0 +barChart.getBarData().getGroupWidth(groupsace,barspace)*12);
        barChart.getAxisLeft().setAxisMinimum(0);
        barChart.groupBars(0,groupsace,barspace);
        barChart.animateXY(800,800);
        barChart.invalidate();
    }

    private ArrayList<BarEntry> barEntries1(String an)
    { float expense_ian,expense_feb,expense_mar,expense_apr,expense_mai,expense_iunie,expense_iulie,expense_august,expense_sept, expense_oct,expense_noiemb,expense_dec;
        expense_ian=db.sumaExpenseIncome_dupaAn("Expense","01",an);
        db.close();
        expense_feb=db.sumaExpenseIncome_dupaAn("Expense","02",an);
        db.close();
        expense_mar=db.sumaExpenseIncome_dupaAn("Expense","03",an);
        db.close();
        expense_apr=db.sumaExpenseIncome_dupaAn("Expense","04",an);
        db.close();
        expense_mai=db.sumaExpenseIncome_dupaAn("Expense","05",an);
        db.close();
        expense_iunie=db.sumaExpenseIncome_dupaAn("Expense","06",an);
        db.close();
        expense_iulie=db.sumaExpenseIncome_dupaAn("Expense","07",an);
        db.close();
        expense_august=db.sumaExpenseIncome_dupaAn("Expense","08",an);
        db.close();
        expense_sept=db.sumaExpenseIncome_dupaAn("Expense","09",an);
        db.close();
        expense_oct=db.sumaExpenseIncome_dupaAn("Expense","10",an);
        db.close();
        expense_noiemb=db.sumaExpenseIncome_dupaAn("Expense","11",an);
        db.close();
        expense_dec=db.sumaExpenseIncome_dupaAn("Expense","12",an);
        db.close();
        float income_ian,income_feb,income_mar,income_apr,income_mai,income_iun,income_iul,income_aug, income_sept,income_oct,income_noiemb,income_dec;
        income_ian=db.sumaExpenseIncome_dupaAn("Income","01",an);
        db.close();
        income_feb=db.sumaExpenseIncome_dupaAn("Income","02",an);
        db.close();
        income_mar=db.sumaExpenseIncome_dupaAn("Income","03",an);
        db.close();
        income_apr=db.sumaExpenseIncome_dupaAn("Income","04",an);
        db.close();
        income_mai=db.sumaExpenseIncome_dupaAn("Income","05",an);
        db.close();
        income_iun=db.sumaExpenseIncome_dupaAn("Income","06",an);
        db.close();
        income_iul=db.sumaExpenseIncome_dupaAn("Income","07",an);
        db.close();
        income_aug=db.sumaExpenseIncome_dupaAn("Income","08",an);
        db.close();
        income_sept=db.sumaExpenseIncome_dupaAn("Income","09",an);
        db.close();
        income_oct=db.sumaExpenseIncome_dupaAn("Income","10",an);
        db.close();
        income_noiemb=db.sumaExpenseIncome_dupaAn("Income","11",an);
        db.close();
        income_dec=db.sumaExpenseIncome_dupaAn("Income","12",an);
        db.close();
        ArrayList<BarEntry> barEntries1=new ArrayList<>();


        barEntries1.add(new BarEntry(1,new float[]{expense_ian,income_ian}));
        barEntries1.add(new BarEntry(2, new float[]{expense_feb,income_feb}));
        barEntries1.add(new BarEntry(3, new float[]{expense_mar,income_mar}));
        barEntries1.add(new BarEntry(4, new float[]{expense_apr,income_apr}));
        barEntries1.add(new BarEntry(5,new float[]{expense_mai,income_mai}));
        barEntries1.add(new BarEntry(6,new float[]{expense_iunie,income_iun}));
        barEntries1.add(new BarEntry(7,new float[]{expense_iulie,income_iul}));
        barEntries1.add(new BarEntry(8,new float[]{expense_august,income_aug}));
        barEntries1.add(new BarEntry(9,new float[]{expense_sept,income_sept}));
        barEntries1.add(new BarEntry(10,new float[]{expense_oct,income_oct}));
        barEntries1.add(new BarEntry(11,new float[]{expense_noiemb,income_noiemb}));
        barEntries1.add(new BarEntry(12,new float[]{expense_dec,income_dec}));
        return barEntries1;
    }

    private ArrayList<BarEntry> barEntries2(String  an)
    {   float expense_ian,expense_feb,expense_mar,expense_apr,expense_mai,expense_iunie,expense_iulie,expense_august,expense_sept, expense_oct,expense_noiemb,expense_dec;
        expense_ian=db.sumaExpenseIncome_dupaAn("Expense","01",an);
        db.close();

        expense_feb=db.sumaExpenseIncome_dupaAn("Expense","02",an);
        db.close();

        expense_mar=db.sumaExpenseIncome_dupaAn("Expense","03",an);
        db.close();

        expense_apr=db.sumaExpenseIncome_dupaAn("Expense","04",an);
        db.close();

        expense_mai=db.sumaExpenseIncome_dupaAn("Expense","05",an);
        db.close();

        expense_iunie=db.sumaExpenseIncome_dupaAn("Expense","06",an);
        db.close();

        expense_iulie=db.sumaExpenseIncome_dupaAn("Expense","07",an);
        db.close();

        expense_august=db.sumaExpenseIncome_dupaAn("Expense","08",an);
        db.close();

        expense_sept=db.sumaExpenseIncome_dupaAn("Expense","09",an);
        db.close();

        expense_oct=db.sumaExpenseIncome_dupaAn("Expense","10",an);
        db.close();

        expense_noiemb=db.sumaExpenseIncome_dupaAn("Expense","11",an);
        db.close();

        expense_dec=db.sumaExpenseIncome_dupaAn("Expense","12",an);
        db.close();

        float income_ian,income_feb,income_mar,income_apr,income_mai,income_iun,income_iul,income_aug, income_sept,income_oct,income_noiemb,income_dec;
        income_ian=db.sumaExpenseIncome_dupaAn("Income","01",an);
        db.close();

        income_feb=db.sumaExpenseIncome_dupaAn("Income","02",an);
        db.close();
        income_mar=db.sumaExpenseIncome_dupaAn("Income","03",an);
        db.close();
        income_apr=db.sumaExpenseIncome_dupaAn("Income","04",an);
        db.close();
        income_mai=db.sumaExpenseIncome_dupaAn("Income","05",an);
        db.close();
        income_iun=db.sumaExpenseIncome_dupaAn("Income","06",an);
        db.close();
        income_iul=db.sumaExpenseIncome_dupaAn("Income","07",an);
        db.close();
        income_aug=db.sumaExpenseIncome_dupaAn("Income","08",an);
        db.close();
        income_sept=db.sumaExpenseIncome_dupaAn("Income","09",an);
        db.close();
        income_oct=db.sumaExpenseIncome_dupaAn("Income","10",an);
        db.close();
        income_noiemb=db.sumaExpenseIncome_dupaAn("Income","11",an);
        db.close();
        income_dec=db.sumaExpenseIncome_dupaAn("Income","12",an);
        db.close();
        ArrayList<BarEntry> barEntries2=new ArrayList<>();
        barEntries2.add(new BarEntry(1,new float[]{expense_ian,income_ian}));
        barEntries2.add(new BarEntry(2, new float[]{expense_feb,income_feb}));
        barEntries2.add(new BarEntry(3, new float[]{expense_mar,income_mar}));
        barEntries2.add(new BarEntry(4, new float[]{expense_apr,income_apr}));
        barEntries2.add(new BarEntry(5,new float[]{expense_mai,income_mai}));
        barEntries2.add(new BarEntry(6,new float[]{expense_iunie,income_iun}));
        barEntries2.add(new BarEntry(7,new float[]{expense_iulie,income_iul}));
        barEntries2.add(new BarEntry(8,new float[]{expense_august,income_aug}));
        barEntries2.add(new BarEntry(9,new float[]{expense_sept,income_sept}));
        barEntries2.add(new BarEntry(10,new float[]{expense_oct,income_oct}));
        barEntries2.add(new BarEntry(11,new float[]{expense_noiemb,income_noiemb}));
        barEntries2.add(new BarEntry(12,new float[]{expense_dec,income_dec}));
        return barEntries2;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {   String an1,an2;
        an1=spinner1.getSelectedItem().toString();
        an2=spinner2.getSelectedItem().toString();
        grafic(bardataset1(an1),bardataset2(an2));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
