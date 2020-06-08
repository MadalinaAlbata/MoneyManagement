package com.example.licenta;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;

public class Month_Statistics_Expense extends AppCompatActivity {
    PieChart pieChart;
    DatabaseHelper db;
    Button button_income;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month__statistics_expense);
        db=new DatabaseHelper(this);
        button_income=(Button) findViewById(R.id.btn_piechart_income);
        Intent intent=getIntent();
        Calendar calendar=Calendar.getInstance();
        int an=calendar.get(Calendar.YEAR);
        final String luna=intent.getStringExtra("luna");
        Cursor c= db.getPrice_TypeLUNA_AN("Expense",luna,an+"");
        pieChart=(PieChart)findViewById(R.id.pieChart_expense);
        pieChart.getDescription().setEnabled(false);
     //   pieChart.setExtraOffsets(5,5,5,0);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setHoleRadius(45f);

        pieChart.setTransparentCircleRadius(50f);

        ArrayList<String> a=new ArrayList<String>();
        ArrayList<String> b=new ArrayList<String>();

        while(c.moveToNext()){
            a.add(c.getString(1));
            b.add(c.getString(0));
        }
        float suma=0;
        for(int i=0;i<b.size();i++)
            suma=suma+Float.valueOf(b.get(i));
        for(int i=1;i<a.size()-1;i++){
            float pret_nou=Float.valueOf(b.get(i));
            for(int j=i+i;j<a.size();j++) {
                if (a.get(i).equals(a.get(j))) {
                    Float pr=Float.valueOf(b.get(j));
                    pret_nou =pret_nou + pr;
                    a.remove(j);
                    b.remove(j);
                }
            }
            b.remove(i);
            b.add(i,String.valueOf(pret_nou));
        }
        pieChart.setDrawCenterText(true);
        pieChart.setCenterTextColor(Color.RED);
        pieChart.setCenterTextSize(15f);
        pieChart.setCenterText("Value:"+suma);

        final ArrayList<PieEntry> values=new ArrayList<>();
            for(int i=0;i<a.size();i++)
            values.add(new PieEntry(Float.parseFloat(b.get(i)),a.get(i)));


        pieChart.animateY(1000, Easing.EaseInOutCubic);

        PieDataSet dataSet=new PieDataSet(values,"");
        dataSet.setSliceSpace(1.5f);
        dataSet.setSelectionShift(10f);
        pieChart.getLegend().setWordWrapEnabled(true);
        pieChart.getLegend().setOrientation(Legend.LegendOrientation.HORIZONTAL);//setEnabled(false);//
        pieChart.getLegend().setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM );
        pieChart.getLegend().setForm(Legend.LegendForm.SQUARE);

       // pieChart.getLegend().getFormSize();//setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        ArrayList<Integer> colors=new ArrayList<>();
        for (int color:ColorTemplate.COLORFUL_COLORS)
            colors.add(color);
        for(int color:ColorTemplate.JOYFUL_COLORS)
            colors.add(color);
        for(int color:ColorTemplate.PASTEL_COLORS)
            colors.add(color);
        for(int color:ColorTemplate.LIBERTY_COLORS)
            colors.add(color);

        dataSet.setColors(colors);


     //   dataSet.setValueLinePart1OffsetPercentage(30f);
      //  dataSet.setValueLinePart1Length(0.45f);
       // dataSet.setValueLinePart2Length(0.25f);
    //    dataSet.setXValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);
        pieChart.setDrawEntryLabels(false);
        int color=Color.parseColor("#BA0544");
       // pieChart.setEntryLabelColor(color);

        PieData data=new PieData(dataSet);
        data.setValueTextSize(12f);
        data.setValueFormatter(new PercentFormatter(pieChart));
        pieChart.setUsePercentValues(true);
        data.setValueTextColor(Color.WHITE);
        pieChart.setData(data);


        button_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Month_Statistics_Income.class);
              //  String luna=intent.getStringExtra("luna_i");
                intent.putExtra("luna",luna);
                startActivity(intent);//goToIncome();
            }
        });

    }

    private void goToIncome() {
        Intent intent=new Intent(this,Month_Statistics_Income.class);
        String luna=intent.getStringExtra("luna_i");
        intent.putExtra("luna",luna);
        startActivity(intent);
    }

    /*public void findType(String l,String type){
       float luna=Float.parseFloat(l);
        if(luna<0.5&& luna>0)
        {type="Expense";
        l="01";}
        else if(luna>0.5&&luna<1) {
            type = "Income";
            l="01";
        }

    }*/
}
