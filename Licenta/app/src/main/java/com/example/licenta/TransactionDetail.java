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

public class TransactionDetail extends AppCompatActivity {
EditText descriere,pret,id;
Button button_update,button_delete,button_data;
TextView type,data;
DatabaseHelper db;
private static final String tag="TransactionDetail";
private TextView nDisplayDate;
private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_transaction);
        Intent intent=getIntent();
        String d=intent.getStringExtra("description");
        descriere=(EditText)findViewById(R.id.edit_description);
        descriere.setText(d);
        String p=intent.getStringExtra("price");
        pret=(EditText)findViewById(R.id.edit_price);
        pret.setText(p);
        String dt=intent.getStringExtra("data");
        data=(TextView) findViewById(R.id.edit_data);
        data.setText(dt);
        String t=intent.getStringExtra("type");
        type=(TextView) findViewById(R.id.edit_type);
        type.setText(t);
        String i=intent.getStringExtra("id");
        id=(EditText) findViewById(R.id.edit_id);
        id.setText(i);
        button_update=(Button) findViewById((R.id.button_update2));
        button_delete=(Button)findViewById(R.id.button_delete);
        button_data=(Button)findViewById(R.id.button_data);
        //data picker
        nDisplayDate=(TextView) findViewById(R.id.edit_data) ;
        button_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(TransactionDetail.this,
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
        //sf data picker
        db=new DatabaseHelper(this);
        updateData();
        deleteData();
    }
    public void updateData(){
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pret.length()==0)
                {
                    pret.setError("Set amount");}
                    else {
                    boolean isUpdated = db.updateData(descriere.getText().toString(), pret.getText().toString(), data.getText().toString(), id.getText().toString());
                    if (isUpdated == true)
                        Toast.makeText(TransactionDetail.this, " EDIT", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(TransactionDetail.this, "  NOT EDIT", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(TransactionDetail.this, Transaction.class);
                    startActivity(intent);
                    //  finish();
                }
            }
        });

    }

    public void deleteData(){
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows=db.deleteData(id.getText().toString());
                if(deleteRows>0)
                    Toast.makeText(TransactionDetail.this, " data deleted", Toast.LENGTH_LONG).show();
                else                    Toast.makeText(TransactionDetail.this, "   data not deleted", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(TransactionDetail.this,Transaction.class);
                startActivity(intent);
                //finish();



            }
        });
    }
}
