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

public class ProfileActivity extends AppCompatActivity {
    TextView tvName,tvEmail,tvAge,tvHeight,tvWeight,tvPhone;

    Button btnShow;

    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tvName=findViewById(R.id.tvName);
        tvEmail=findViewById(R.id.tvEmail);
        tvAge=findViewById(R.id.tvAge);
        tvHeight=findViewById(R.id.tvHeight);
        tvWeight=findViewById(R.id.tvWeight);
        tvPhone=findViewById(R.id.tvPhone);
        btnShow=findViewById(R.id.btnShow);


        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff= FirebaseDatabase.getInstance().getReference().child("Member").child("1");

                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String name=dataSnapshot.child("name").getValue().toString();
                        String email=dataSnapshot.child("email").getValue().toString();
                        String age=dataSnapshot.child("age").getValue().toString();
                        String phone=dataSnapshot.child("phone").getValue().toString();
                        String height=dataSnapshot.child("height").getValue().toString();
                        String weight=dataSnapshot.child("weight").getValue().toString();

                        tvName.setText(name);
                        tvAge.setText(age);
                        tvEmail.setText(email);
                        tvHeight.setText(height);
                        tvWeight.setText(weight);
                        tvPhone.setText(phone);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });


    }
}
