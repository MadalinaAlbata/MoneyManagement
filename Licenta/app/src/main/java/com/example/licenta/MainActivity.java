package com.example.licenta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button button_money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_money=(Button) findViewById(R.id.moneyapp);
        button_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMoneyApp();
            }
        });
    }

    private void openMoneyApp() {

        Intent intent=new Intent(this,MoneyApp.class);
        startActivity(intent);

    }

}
