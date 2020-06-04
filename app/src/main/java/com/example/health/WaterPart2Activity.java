package com.example.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WaterPart2Activity extends AppCompatActivity {

    TextView set1,set2;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_part2);

        set1=findViewById(R.id.set1);
        set2=findViewById(R.id.set2);



        reff= FirebaseDatabase.getInstance().getReference().child("Water").child("1");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String h1=dataSnapshot.child("h1").getValue().toString();
                String h2=dataSnapshot.child("h2").getValue().toString();

                int h11= Integer.parseInt(h1);
                int h22= Integer.parseInt(h2);

                set1.setText("Duration from "+ h1 + " to "+(h22-1) + " \n  Alarm will ring at  "+ (h11+1) );
                set2.setText("Duration from "+ (h11+1) + " to "+ h2 + "\n  Alarm will ring at  "+ (h11+2) );

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
