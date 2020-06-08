package com.example.licenta;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class ExpenseMenuGrid2 extends AppCompatActivity {
    GridView gridView;
    SearchView searchView;
    AdapterExp adapter;
    AdapterImg adapter_img;
    Button buton_adauga;
    ArrayList<Expense> expense=new ArrayList<Expense>();
    ArrayList<Expense> expense1=new ArrayList<Expense>();
    int nr=0;
    String[] nume={"Electricity","Transport","Education","Pet","Toiletry",
            "Travel","Grocery","Shopping", "Car","Gym","Health","Rent",
            "Baby","Water","Gas"};
    Integer[] img={R.mipmap.curent_round,R.mipmap.transport_round,R.mipmap.education_round,R.mipmap.animale_round ,
            R.mipmap.toiletry_round ,R.mipmap.travel_round,R.mipmap.food_round,R.mipmap.shopping_round,R.mipmap.car_round,
            R.mipmap.gym_round,R.mipmap.health_round,R.mipmap.rent_round,R.mipmap.baby_round,R.mipmap.water_round,
            R.mipmap.gas_round};

    ArrayList<Integer> img1 =new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_menu_grid);
       // buton_adauga=(Button)findViewById(R.id.buton_adauga_exp);
        /*buton_adauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImg();
            }
        });*/
        gridView= (GridView)findViewById(R.id.grid_view);
        searchView=(SearchView)findViewById(R.id.search_view);
       adapter=new AdapterExp(ExpenseMenuGrid2.this,this.getExpense());
        gridView.setAdapter(adapter);
       /* Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {   Integer imagine=bundle.getInt("img");
            img1.add(imagine);
            adapter_img.notifyDataSetChanged();
            Integer ad =img1.get(img1.size()-1);
            Toast.makeText(ExpenseMenuGrid.this,"item added", Toast.LENGTH_LONG).show();
        }*/

        gridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
        gridView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                nr++;
                mode.setTitle(nr+" items selected");
                expense1.add((Expense) adapter.getItem(position));

            }
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater=mode.getMenuInflater();
                inflater.inflate(R.menu.my_context,menu);
                return true;
            }
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()){
                    case R.id.delete_id:
                        for(Expense e:expense1) {
                            adapter.remove(e);
                            adapter.notifyDataSetChanged();
                        }
                        Toast.makeText(ExpenseMenuGrid2.this,nr+ "items removed", Toast.LENGTH_LONG).show();
                        nr=0;
                        mode.finish();
                        return true;
                    default:
                        return false;
                }}

            @Override
            public void onDestroyActionMode(ActionMode mode) {

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
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
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
            Toast.makeText(ExpenseMenuGrid2.this, "item added", Toast.LENGTH_LONG).show();
        }
        return img1;
    }

    private ArrayList<Expense> getExpense()
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


    private void addImg() {
        Intent intent=new Intent(this,AdaugaImgExp.class);
        startActivity(intent);
    }



}
