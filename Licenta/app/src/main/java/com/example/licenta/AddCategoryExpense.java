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

public class AddCategoryExpense extends AppCompatActivity {
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
        setContentView(R.layout.activity_add_category);
        Intent intent=getIntent();
        String message=intent.getStringExtra("EXTRA_MESSAGE");
        category=findViewById(R.id.add_category);
        category.setText(message);
        mydb=new DatabaseHelper(this);

        description= (EditText)findViewById(R.id.add_description);
        price=(EditText)findViewById(R.id.add_price);
        date=(TextView)findViewById(R.id.add_data);
        button_close=(Button)findViewById(R.id.button_close);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });
        button_add_data=(Button) findViewById(R.id.button_add_data) ;
        button_add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(AddCategoryExpense.this,
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

        button_add=(Button) findViewById(R.id.button_add);
        AddData();}

    public void AddData(){
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(price.length()==0)
                {
                    price.setError("Set amount");
                }else if (date.length()==0){
                    date.setError("Set the date");
                }
                else {
                    boolean isInserted = mydb.insertData("Expense", category.getText().toString(),
                            description.getText().toString(),
                            price.getText().toString(),
                            date.getText().toString());
                    if (isInserted == true)
                        Toast.makeText(AddCategoryExpense.this, "Data inserted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(AddCategoryExpense.this, "Data  not inserted", Toast.LENGTH_LONG).show();
                finish();}
            }
        });

    }

    public void close(){
      //  Intent intent=new Intent(this,ExpenseMenuGrid.class);
       finish();
        // startActivity(intent);
    }
}
