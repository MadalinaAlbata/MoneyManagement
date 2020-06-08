package com.example.licenta;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MoneyApp extends AppCompatActivity {

    Button button_expense;
    Button button_income;
    Button button_transaction;
    Button button_statistics;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_app);
        button_expense=(Button) findViewById(R.id.button_expense);
        button_income=(Button) findViewById(R.id.button_income);
        button_transaction=(Button) findViewById(R.id.button_transaction);
        button_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpense();
            }
        });
        db=new DatabaseHelper(this);
        button_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIncome();
            }
        });
        button_statistics=(Button)findViewById(R.id.button_statistics);
        button_statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statistics();
            }
        });
}

    private void addIncome() {
        Intent intent=new Intent(this, IncomeMenuGrid.class);
        startActivity(intent);
    }

    private void addExpense() {
        Intent intent=new Intent(this, ExpenseMenuGrid.class);
        startActivity(intent);
    }
    private void statistics(){
        Intent intent=new Intent(this,StatisticsCurrentMonth.class);
    startActivity(intent);}

    public void send(View view){

              Cursor rez=  db.getAllData();
           /*   if(rez.getCount()==0)
              { Toast.makeText(MoneyApp.this,"recoded added",Toast.LENGTH_SHORT).show();

                  return ;}*/
              StringBuffer buffer=new StringBuffer();
              while(rez.moveToNext()){
                  buffer.append("Type :"+rez.getString(1)+"\n");
                  buffer.append("Description :"+rez.getString(2)+"\n");
                  buffer.append("Price :"+rez.getString(3)+"\n");
                  buffer.append("Data :"+rez.getString(4)+"\n");
              }
              Intent intent=new Intent(this,Transaction.class);
              intent.putExtra("transaction",buffer.toString());
              startActivity(intent);
            }



}
