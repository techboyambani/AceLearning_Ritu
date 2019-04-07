package com.example.dell.acelearning;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
public class select_subject_learn extends AppCompatActivity {
    RecyclerView recyclerView;
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_subject_for_learn);
        home=findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(select_subject_learn.this, home_student.class );
                startActivity(i);
                finish();
            }
        });
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("subject");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // for individual subjects
                String subject_name=dataSnapshot.getKey();
                String subject_value=dataSnapshot.getValue(String.class);
                ((subject_learn_adapter)recyclerView.getAdapter()).update(subject_name, subject_value);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(select_subject_learn.this));
        subject_learn_adapter adapter=new subject_learn_adapter(recyclerView, select_subject_learn.this, new ArrayList<String>(), new ArrayList<String>());
        recyclerView.setAdapter(adapter);

    }
}
