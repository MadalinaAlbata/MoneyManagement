package com.example.licenta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IncomeMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
    }

    public void sendMessage(View view){
        Intent intent =new Intent(this, AddCategoryIncome.class);
        switch(view.getId()){
            case R.id.button_salary:{
                Button button=(Button) findViewById(R.id.button_salary);
                String message=button.getText().toString();
                intent.putExtra("INCOME",message);
                startActivity(intent);
                break;
            }
            case R.id.button_gift:
            {
                Button button=(Button)findViewById(R.id.button_gift);
                String message=button.getText().toString();
                intent.putExtra("INCOME",message);
                startActivity(intent);
                break;
            }
            case R.id.button_other:{
                Button button=(Button) findViewById(R.id.button_other);
                String message=button.getText().toString();
                intent.putExtra("INCOME",message);
                startActivity(intent);
                break;
            }
        }

    }
}
