package com.example.licenta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class ExpenseMenuGrid extends AppCompatActivity {
    GridView gridView;
    SearchView searchView;
    AdapterExp adapter;
    AdapterImg adapter_img;
    Button buton_adauga;
    ArrayList<Expense> expense=new ArrayList<Expense>();
    ArrayList<Expense> expense1=new ArrayList<Expense>();
    DatabaseHelper db;

    int nr=0;
    String[] nume={"Electricity","Transport","Education","Pet","Toiletry",
            "Travel","Grocery","Shopping", "Car","Gym","Health","Rent",
            "Baby","Water","Gas","Telephone","Social","Insurance","Others"};
    Integer[] img={R.mipmap.curent_round,R.mipmap.transport_round,R.mipmap.education_round,R.mipmap.animale_round ,
            R.mipmap.toiletry_round ,R.mipmap.travel1_round,R.mipmap.food_round,R.mipmap.shopping_round,R.mipmap.car_round,
            R.mipmap.gym_round,R.mipmap.health_round,R.mipmap.rent_round,R.mipmap.baby_round,R.mipmap.water_round,
            R.mipmap.gas_round,R.mipmap.telephone_round,R.mipmap.social_round,R.mipmap.insurance,R.mipmap.other_expense};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_menu_grid);
       // adaugaImaginiStart();
        gridView= (GridView)findViewById(R.id.grid_view);
        searchView=(SearchView)findViewById(R.id.search_view);
        adapter=new AdapterExp(ExpenseMenuGrid.this,this.getExpense1());
        gridView.setAdapter(adapter);
        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              Intent intent= new Intent(getApplicationContext(), AddCategoryExpense.class);
               Expense exp= (Expense) parent.getItemAtPosition(position);//=tv.getText().toString();
               String text=exp.getNume();

               intent.putExtra("EXTRA_MESSAGE",text);
               startActivity(intent);
           }
        });


        //search elements
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            public boolean onQueryTextSubmit(String a)
            {
                return false;
            }
            public boolean onQueryTextChange(String a){
                adapter.getFilter().filter(a);
                return false;}
        });
      /*  gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });*/
    }

    private ArrayList<Integer> getIMG() {
        ArrayList<Integer> img1=new ArrayList<Integer>();
        for(int i=0;i<img.length;i++){
            img1.add(img[i]);
        }
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            Integer imagine = bundle.getInt("img");
            img1.add(imagine);
            Toast.makeText(ExpenseMenuGrid.this, "item added", Toast.LENGTH_LONG).show();
        }
            return img1;
    }

 /*   private ArrayList<Expense> getExpense()
    {   ArrayList<Expense> expense=new ArrayList<Expense>();
        Expense e;
        expense=db.getAllImag();

       // for(int i=0;i<nume.length;i++)
        //{   e=new Expense(nume[i],img[i]);
          //  expense.add(e); }
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {   int imagine=bundle.getInt("img");
         //   img1.add(imagine);
            e=new Expense("imagine",imagine);
            expense.add(expense.size(),e);
        }
        return expense;}
*/


    private ArrayList<Expense> getExpense1()
    {   ArrayList<Expense> expense=new ArrayList<Expense>();
        Expense e;
        for(int i=0;i<nume.length;i++)
        {   e=new Expense(nume[i],img[i]);
            expense.add(e); }
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {   int imagine=bundle.getInt("img");
            //   img1.add(imagine);
            e=new Expense("imagine",imagine);
            expense.add(expense.size(),e);
        }
        return expense;}




}
