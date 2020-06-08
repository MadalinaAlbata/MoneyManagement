package com.example.licenta;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Add_category_income extends AppCompatActivity {
    private static final String tag="AddCategoryExpense";

    private TextView nDisplayDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    Button button_add,button_add_data,button_close;
    EditText description,price;
    TextView date;
    int year,month,day;
    TextView category;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category_income2);
        Intent intent=getIntent();
        String message=intent.getStringExtra("add_income");
        category=findViewById(R.id.add_category_income1);
        category.setText(message);
        mydb=new DatabaseHelper(this);

        description= (EditText)findViewById(R.id.add_description_income1);
        price=(EditText)findViewById(R.id.add_price_income1);
        date=(TextView)findViewById(R.id.add_data_income1);
        button_close=(Button)findViewById(R.id.button_close_income1);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });
        button_add_data=(Button) findViewById(R.id.button_add_data_income1) ;
        button_add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(Add_category_income.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,onDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        onDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;
                String day1=""+day;
                String month1=""+month;

                if(month < 10){
                    month1 = "0" + month;
                }
                if(day < 10){
                    day1 = "0" + day ;
                }
                Log.d(tag,"onDateSet:mm/dd/yy: "+day+"/"+month+"/"+year);
                String d=day1+ "/" + month1 +"/"+year;
                date.setText(d);
            }
        };

        button_add=(Button) findViewById(R.id.button_add_income1);
        AddData();}

    public void AddData(){
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(price.length()==0)
                {
                    price.setError("Set price");
                }else if (date.length()==0){
                    date.setError("Set date");
                }
                else {
                    boolean isInserted = mydb.insertData("Income", category.getText().toString(),
                            description.getText().toString(),
                            price.getText().toString(),
                            date.getText().toString());
                    if (isInserted == true)
                        Toast.makeText(Add_category_income.this, "data inserted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(Add_category_income.this, "data  not inserted", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }

    public void close(){
        //  Intent intent=new Intent(this,ExpenseMenuGrid.class);
        finish();
        // startActivity(intent);
    }
}
