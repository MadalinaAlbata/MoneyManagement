package com.example.licenta;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Transaction extends AppCompatActivity implements AdapterView.OnItemSelectedListener{ private TextView date;
  private TextView type;
  private TextView price;
  private TextView description;
  Spinner spinner_an;
  TextView luna;
  TextView expense_nr,income_nr;
  Button button_next,button_prev;
  int[] imgid={R.mipmap.curent_round,R.mipmap.transport_round,R.mipmap.education_round,R.mipmap.animale_round ,
          R.mipmap.toiletry_round ,R.mipmap.travel_round,R.mipmap.food_round,R.mipmap.shopping_round,R.mipmap.car_round,
          R.mipmap.gym_round,R.mipmap.health_round,R.mipmap.rent_round,R.mipmap.baby_round,R.mipmap.water_round,
          R.mipmap.gas_round};
  int [] sageata={R.drawable.sageata_jos,R.drawable.sageata_sus};

  DatabaseHelper db;
  Button button_delete;
    ArrayList<MoneyObject> userlist;
    ArrayList<MoneyObject> userlist_food;
    MoneyObject user;
    ListView listView,listViewfood;
    Calendar cal=Calendar.getInstance();
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        //pentru spinner luna
        spinner  =findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter_spinner=ArrayAdapter.createFromResource(this,R.array.transaction,android.R.layout.simple_spinner_item);
        adapter_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter_spinner);
        spinner.setOnItemSelectedListener(this);
        //pentru spinner an
        Calendar calendar=Calendar.getInstance();
        int an=calendar.get(Calendar.YEAR);
        spinner_an=(Spinner) findViewById(R.id.spinner_year_transaction);
        ArrayAdapter<CharSequence> adapter_year=ArrayAdapter.createFromResource(this,R.array.years,android.R.layout.simple_spinner_item);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_an.setAdapter(adapter_year);
        int spineer_position=adapter_year.getPosition(an+"");
        spinner_an.setSelection(spineer_position);
        spinner_an.setOnItemSelectedListener(this);
        //pt calendar
        luna=(TextView)findViewById(R.id.tv_month);
        button_next=(Button)findViewById(R.id.next_button);
        button_prev=(Button)findViewById(R.id.prev_button);
        luna.setText(getCurrentDate());
        //sf calendar
        date=(TextView)findViewById(R.id.data);
        price=(TextView)findViewById(R.id.price);
        description=(TextView)findViewById(R.id.description);
        db=new DatabaseHelper(this);
        expense_nr=(TextView) findViewById(R.id.tv_expense_nr);
        income_nr=(TextView) findViewById(R.id.tv_income_nr);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(v);
            }
        });
        button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prev(v);
            }
        });

         }

         public String getCurrentDate(){
                 int month=cal.get(Calendar.MONTH);
                 int an=cal.get(Calendar.YEAR);
                 month=month+1;
                 String l=luna(month);
                 return l;
             }

         private String getnextmonth() {
            String luna_next=luna.getText().toString();
            int luna_int=  int_luna(luna_next);//aflu stringul
             luna_int++;//cresc
             if(luna_int==13)
                 luna_int=1;
             String  luna_next2=luna(luna_int);//afisez luna in string
             return luna_next2;
             }

             private String getpreviousmonth() {
                 String luna_prev=luna.getText().toString();
                 int luna_int=int_luna(luna_prev);
                 luna_int--;
                 if(luna_int==0)
                     luna_int=12;
                 String  luna_prev2=luna(luna_int);
                 return luna_prev2;
             }

             public int int_luna(String l){
                if(l.equals("January"))
                    return 1;
                else if(l.equals("February"))
                    return 2;
                else if(l.equals("March"))
                    return 3;
                else if(l.equals("April"))
                        return 4;
                else if(l.equals("May"))
                    return 5;
                else if(l.equals("June"))
                    return 6;
                else if(l.equals("July"))
                    return 7;
                else if(l.equals("August"))
                    return 8;
                else if(l.equals("September"))
                    return 9;
                else if(l.equals("October"))
                    return 10;
                else if(l.equals("November"))
                    return 11;
                else
                    return 12;
             }


             public String luna(int l)
             {
                 if(l==1)
                     return "January";
                 else if(l==2)
                     return "February";
                 else if(l==3)
                     return "March";
                 else if(l==4)
                     return "April";
                 else if(l==5)
                     return "May";
                 else if(l==6)
                     return"June";
                 else if(l==7)
                     return "July";
                 else if(l==8)
                     return"August";
                 else if(l==9)
                     return"September";
                 else if(l==10)
                     return"October";
                 else if(l==11)
                     return "November";
                 else
                     return "December"; }



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

        public void next(View v) {
        luna.setText(getnextmonth());
            ArrayAdapter<CharSequence> adapter_spinner=ArrayAdapter.createFromResource(this,R.array.transaction,android.R.layout.simple_spinner_item);
            adapter_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter_spinner);
            spinner.setOnItemSelectedListener(this);

        }
        public void prev(View v) {
        luna.setText(getpreviousmonth());
            ArrayAdapter<CharSequence> adapter_spinner=ArrayAdapter.createFromResource(this,R.array.transaction,android.R.layout.simple_spinner_item);
            adapter_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter_spinner);
            spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String year=spinner_an.getSelectedItem().toString();
        String text=spinner.getSelectedItem().toString();//parent.getItemAtPosition(position).toString();
      //  Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
        listView = (ListView) findViewById(R.id.listView);
        db=new DatabaseHelper(this);
        userlist = new ArrayList<>();
        userlist_food=new ArrayList<>();
        if(text.equals("All Categories")){
            Cursor data;
            String l=luna.getText().toString();
            String l1=luna_0(l);
         //   float sumExpense = db.sumaExpense_Income("Expense",l1);
            float sumExpense = db.sumaExpenseIncome_dupaAn("Expense",l1,year);
            expense_nr.setText(Float.toString(sumExpense));
            float sumIncome=db.sumaExpenseIncome_dupaAn("Income",l1,year);//db.sumaExpense_Income("Income",l1);
            income_nr.setText(Float.toString(sumIncome));
            data= db.get_dupaluna_an(l1,year);//schimbat
            int numrows = data.getCount();
            if (numrows == 0) {
            Toast.makeText(Transaction.this, "No category find", Toast.LENGTH_LONG).show();
             } else {
            while (data.moveToNext()) {
                user = new MoneyObject(data.getString(0), data.getString(1), data.getString(2),data.getString(3),data.getString(4),data.getString(5));
                userlist.add(user); }
            }
            MoneyObjectAdapter adapter = new MoneyObjectAdapter(this, R.layout.activity_transaction_line, userlist,imgid,sageata);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent= new Intent(getApplicationContext(),TransactionDetail.class);
                    intent.putExtra("id",userlist.get(position).getId());
                    intent.putExtra("type",userlist.get(position).getType());
                    intent.putExtra("description",userlist.get(position).getName());
                    intent.putExtra("price",userlist.get(position).getPrice());
                    intent.putExtra("data",userlist.get(position).getData());
                    startActivity(intent); }
            });
            //listView.setVisibility(View.VISIBLE);
        }
        else{ String l=luna.getText().toString();
            String l1=luna_0(l);
            Cursor c=db.getTransaction_dupaCategorie_luna_an(text,l1,year);
            int nr=c.getCount();
            if(nr==0)
                Toast.makeText(Transaction.this, "This category is empty", Toast.LENGTH_LONG).show();
            else{
                while(c.moveToNext()){
                    user = new MoneyObject(c.getString(0), c.getString(1), c.getString(2),c.getString(3),c.getString(4),c.getString(5));
                    userlist.add(user); }
            }
            MoneyObjectAdapter adapter = new MoneyObjectAdapter(this, R.layout.activity_transaction_line, userlist,imgid,sageata);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent= new Intent(getApplicationContext(),TransactionDetail.class);
                    intent.putExtra("id",userlist.get(position).getId());
                    intent.putExtra("type",userlist.get(position).getType());
                    intent.putExtra("description",userlist.get(position).getName());
                    intent.putExtra("price",userlist.get(position).getPrice());
                    intent.putExtra("data",userlist.get(position).getData());
                    startActivity(intent);
                }
            });
        }}

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public void onBackPressed(){
        Intent intent=new Intent(Transaction.this,MoneyApp.class);
        startActivity(intent);
        super.onBackPressed();
        finish();
    }


}
