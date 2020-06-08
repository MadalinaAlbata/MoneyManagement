package com.example.licenta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExpenseMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_menu);
    }

    public void sendMessege(View view)
    {
        Intent intent= new Intent(this, AddCategoryExpense.class);
        switch (view.getId()){
            case R.id.button_animal:

            {
                Button buton=(Button) findViewById(R.id.button_animal);
                String message=buton.getText().toString();
                intent.putExtra("EXTRA_MESSAGE",message);
                startActivity(intent);
                break;
            }

            case R.id.button_food:
            { Button buton=(Button) findViewById(R.id.button_food);
                String message=buton.getText().toString();
                intent.putExtra("EXTRA_MESSAGE",message);
                startActivity(intent);
                break; }
        }}
}
