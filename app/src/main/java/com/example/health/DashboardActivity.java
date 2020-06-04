package com.example.health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    Button btnProfile,btnBmi,btnWater,btnSleep,btndiet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnProfile=findViewById(R.id.btnProfile);
        btnBmi=findViewById(R.id.btnBmi);
        btnWater=findViewById(R.id.btnwater);
        btnSleep=findViewById(R.id.btnSleep);
        btndiet=findViewById(R.id.btndiet);



        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(DashboardActivity.this,ProfileActivity.class);
                startActivity(a);
            }
        });

        btnBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent b=new Intent(DashboardActivity.this,BmiActivity.class);
                      startActivity(b);

                    //open in play store app directly
                } catch (ActivityNotFoundException e) {
                    //open in browser
                }
              //  startActivity(b);
               // Toast.makeText(DashboardActivity.this, "hello", Toast.LENGTH_SHORT).show();
            }
        });


        btnWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(DashboardActivity.this,WaterActivity.class);
                startActivity(a);
            }
        });


        btnSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(DashboardActivity.this,SleepActivity.class);
                startActivity(a);
            }
        });

        btndiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(DashboardActivity.this,DietActivity.class);
                startActivity(a);
            }
        });



    }
}
