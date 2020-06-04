package com.example.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
Button btnSave;
EditText etName,etEmail,etPhone,etAge,etHeight,etWeight;
//RadioGroup rdGender;
//RadioButton rdGenderMale,rdGenderFemale;

DatabaseReference reff;
Member member;
long maxid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName=findViewById(R.id.etName);
        etEmail=findViewById(R.id.etEmail);
        etPhone=findViewById(R.id.etPhone);
        etAge=findViewById(R.id.etAge);
        etHeight=findViewById(R.id.etHeight);
        etWeight=findViewById(R.id.etWeight);

        btnSave=findViewById(R.id.btnSave);

        member=new Member();

        reff=FirebaseDatabase.getInstance().getReference().child("Member");

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

                int age1=Integer.parseInt(etAge.getText().toString().trim());
                int phone1=Integer.parseInt(etPhone.getText().toString().trim());
                float height1=Float.parseFloat(etHeight.getText().toString().trim());
                float weight1=Float.parseFloat(etWeight.getText().toString().trim());
                String name1=etName.getText().toString().trim();
                String email1=etEmail.getText().toString().trim();
                member.setName(name1);
                member.setAge(age1);
                member.setEmail(email1);
                member.setHeight(height1);
                member.setWeight(weight1);
                member.setPhone(phone1);

                reff.child(String.valueOf(maxid+1)).setValue(member);


               // reff.push().setValue(member);
                Toast.makeText(RegisterActivity.this, "put data", Toast.LENGTH_SHORT).show();





                Intent a=new Intent(RegisterActivity.this,DashboardActivity.class);
                startActivity(a);
            }
        });
    }
    /*
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.r1,menu);
        return super.onCreateOptionsMenu(menu);
    }*/
}
