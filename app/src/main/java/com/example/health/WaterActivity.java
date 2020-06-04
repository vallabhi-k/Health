package com.example.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WaterActivity extends AppCompatActivity implements TimePickerFragment.TimePickerListener {

     TextView tvDisplayTime;
     TextView tvDisplayTime2;
     DatabaseReference reff;
     long maxid=0;
     SeekBar seekBar;
     Button btnSave;

    boolean flag=true;

     WaterJava waterobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        tvDisplayTime = findViewById(R.id.tvDisplayTime);
        tvDisplayTime2 = findViewById(R.id.tvDisplayTime2);
        seekBar=findViewById(R.id.sbInterval);

        btnSave=findViewById(R.id.btnSave);


        Button btnShowTimePicker = findViewById(R.id.btnShowTimePicker);
        Button btnSleep= findViewById(R.id.btnSleep);

        btnShowTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.setCancelable(false);
                timePickerFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });

        btnSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.setCancelable(false);
                timePickerFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                Toast.makeText(getApplicationContext(),"interval: "+progress, Toast.LENGTH_SHORT).show();
            waterobj.setSb(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"seekbar touch started!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
               // Toast.makeText(getApplicationContext(),"seekbar touch stopped!", Toast.LENGTH_SHORT).show();
            }
        });
        waterobj=new WaterJava();

        reff= FirebaseDatabase.getInstance().getReference().child("Water");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    maxid=dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff.child(String.valueOf(maxid+1)).setValue(waterobj);

                Intent a=new Intent(WaterActivity.this,WaterPart2Activity.class);
                startActivity(a);

            }
        });



    }


    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        int p=0;

        if(flag==true)
        {
            tvDisplayTime.setText(  hour + " : " + minute);

            waterobj.setH1(hour);
            waterobj.setM1(minute);
          //  Toast.makeText(this, "wake", Toast.LENGTH_SHORT).show();
        flag=false;
        }
        else
        {
            tvDisplayTime2.setText(  hour + " : " + minute);
           // Toast.makeText(this, "sleep", Toast.LENGTH_SHORT).show();
            waterobj.setH2(hour);
            waterobj.setM2(minute);



        }



    }

}
