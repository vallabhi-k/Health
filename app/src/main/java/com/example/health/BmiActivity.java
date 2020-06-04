package com.example.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BmiActivity extends AppCompatActivity {

    DatabaseReference reff;

    TextView showbmi,height,weight;

    Button showb;
    int h11=0;
    int h22=0;
    double bmi=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        showbmi=findViewById(R.id.showbmi);
        height=findViewById(R.id.height);
        weight=findViewById(R.id.weight);
        showb=findViewById(R.id.showb);



        reff= FirebaseDatabase.getInstance().getReference().child("Member").child("1");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String h1=dataSnapshot.child("height").getValue().toString();
                String h2=dataSnapshot.child("weight").getValue().toString();

                 h11= Integer.parseInt(h1);
                 h22= Integer.parseInt(h2);

                height.setText("Height \n"+ h1);
                weight.setText("Weight \n"+ h2);

                 bmi=h22/(h11*h11*1.0);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        showb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showbmi.setText(" "+bmi);



            }
        });
    }
}
