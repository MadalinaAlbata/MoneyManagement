package com.example.licenta;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddCategoryIncome extends AppCompatActivity {
    private static final String tag="AddCategoryIncome";
    private TextView nDisplayDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    Button button_add;
    EditText description,price,date;
    int year,mont,day;
    TextView category;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category_income);
        mydb=new DatabaseHelper(this);
        Intent intent=getIntent();
        String message=intent.getStringExtra("INCOME");
        category=findViewById(R.id.category_income);
        category.setText(message);
        price=findViewById(R.id.price_income);
        date=findViewById(R.id.data_income);
        description=findViewById(R.id.description_income);

        nDisplayDate=(TextView) findViewById(R.id.data_income) ;
        nDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(AddCategoryIncome.this,
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
                String date=day1+ "/" + month1 +"/"+year;
                nDisplayDate.setText(date);

            }
        };

        button_add=(Button) findViewById(R.id.button_add_income);
        AddData();}
        public void AddData(){
            button_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(price.length()==0)
                    {
                        price.setError("Set amount");
                    }else if (nDisplayDate.length()==0){
                        nDisplayDate.setError("Set the date");
                    }
                    else{
                        boolean isInserted= mydb.insertData("Income",category.getText().toString(),
                            description.getText().toString(),
                            price.getText().toString(),
                            nDisplayDate.getText().toString());
                        if(isInserted==true)
                            Toast.makeText(AddCategoryIncome.this,"data inserted",Toast.LENGTH_LONG).show();
                        else                     Toast.makeText(AddCategoryIncome.this,"data  not inserted",Toast.LENGTH_LONG).show();




                     //   Toast.makeText(AddCategoryIncome.this,"recoded added",Toast.LENGTH_SHORT).show();
                        }

        }
});
        }
}