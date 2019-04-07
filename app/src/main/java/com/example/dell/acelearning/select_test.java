package com.example.dell.acelearning;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//myrecyclerviewactivity
public class select_test extends AppCompatActivity {
    RecyclerView recyclerView;
    String subject;
    TextView info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.sample);
       setContentView(R.layout.select_test);
        info=findViewById(R.id.info);
        subject=getIntent().getStringExtra("subject");
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("study_material").child(subject).child("tests");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot childsnapshot: dataSnapshot.getChildren())
                {

//               String test_name=dataSnapshot.getKey();
               Toast.makeText(select_test.this, childsnapshot.getKey(), Toast.LENGTH_SHORT).show();
//                String subject_value=dataSnapshot.getValue(String.class);
                      ((select_test_adapter)recyclerView.getAdapter()).update((String) childsnapshot.getKey(),subject);
               // info.setText(childsnapshot.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(select_test.this));
        select_test_adapter adapter=new select_test_adapter(recyclerView, select_test.this, new ArrayList<String>(),new ArrayList<String>());
        recyclerView.setAdapter(adapter);

    }
}
