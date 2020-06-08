package com.example.licenta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdaugaImgExp extends AppCompatActivity {
   Integer[] imagini={R.mipmap.cosmetice_round,R.mipmap.masina_round,R.drawable.animals,R.drawable.button_right,R.drawable.electric,R.drawable.car,R.drawable.bill,R.drawable.film,R.drawable.gym,R.mipmap.adauga_round};
    GridView gridView;
    AdapterImg adapter;
    ArrayList<Integer> img1 ;//=new ArrayList<Integer>();
    String[] mobileValues={"a","b","c","d"};
   ArrayList <String> mobileValues1;

    int nr=0;
    ArrayList<Expense> expense=new ArrayList<Expense>();
    ArrayList<Expense> expense1=new ArrayList<Expense>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_img_exp);
       img1=new ArrayList<Integer>();
        for(int i=0;i<imagini.length;i++){
            img1.add(imagini[i]);
        }
        gridView=(GridView)findViewById(R.id.grid_view1);
        adapter=new AdapterImg(AdaugaImgExp.this,get_imagini());
        gridView.setAdapter(adapter);

      gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Integer img= imagini[position];
                Intent intent= new Intent(getApplicationContext(),ExpenseMenuGrid.class);
                intent.putExtra("img",imagini[position]);
               startActivity(intent);
          /* img1.add(img);
            adapter.notifyDataSetChanged();
            Integer ad =img1.get(img1.size()-1);
                Toast.makeText(AdaugaImgExp.this,"items removed", Toast.LENGTH_LONG).show();
*/
           }
       });
    }

    private ArrayList<Integer> get_imagini() {

        ArrayList<Integer> img1=new ArrayList<Integer>();
        for(int i=0;i<imagini.length;i++){
            img1.add(imagini[i]);
        }
        return img1;
    }
}
