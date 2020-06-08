package com.example.licenta;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

public class Month_Statistics_Income extends AppCompatActivity {
    PieChart pieChart;
    DatabaseHelper db;
    Button button_expense;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month__statistics__income);
        db=new DatabaseHelper(this);
        button_expense=(Button)findViewById(R.id.btn_piechart_expense1);
        Intent intent=getIntent();
        Calendar calendar=Calendar.getInstance();
        int an=calendar.get(Calendar.YEAR);

        final String luna=intent.getStringExtra("luna");
        String tip=intent.getStringExtra("tip");
        Cursor c= db.getPrice_TypeLUNA_AN("Income",luna,an+"");
        pieChart=(PieChart)findViewById(R.id.pieChart_income);
        pieChart.getDescription().setEnabled(false);
      //  pieChart.setExtraOffsets(5,5,5,0);
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

        pieChart.setDrawEntryLabels(false);
        int color=Color.parseColor("#BA0544");
        PieData data=new PieData(dataSet);
        data.setValueTextSize(12f);
        data.setValueFormatter(new PercentFormatter(pieChart));
        pieChart.setUsePercentValues(true);
        data.setValueTextColor(Color.WHITE);
        pieChart.setData(data);

        button_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Month_Statistics_Expense.class);
                intent.putExtra("luna",luna);
                startActivity(intent);
            }
        });
    }

    private void goToExpense() {
        Intent intent=new Intent(this,Month_Statistics_Expense.class);
        String luna=intent.getStringExtra("luna");
        intent.putExtra("luna",luna);
        startActivity(intent);
    }
}
