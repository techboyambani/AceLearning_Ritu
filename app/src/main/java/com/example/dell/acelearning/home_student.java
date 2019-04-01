package com.example.dell.acelearning;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class home_student extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    public TextView t1,t2;
    Button learn , test, doubt , livelec;
    private DatabaseReference myRef;
    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        learn =findViewById(R.id.learn);
        test=findViewById(R.id.tests);
        doubt=findViewById(R.id.doubts);
        livelec=findViewById(R.id.livelec);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View view =navigationView.inflateHeaderView(R.layout.nav_header_home_student);
        t1=view.findViewById(R.id.u_name);
        t2=view.findViewById(R.id.u_email);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("users");
        final FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        myRef.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, String> userinfo = (Map) dataSnapshot.getValue();
                String u_name = userinfo.get("name");
                String u_email = userinfo.get("email");
                t1.setText(u_name);
                t2.setText(u_email);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        ///////////////////////////////////



        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent i= new Intent(home_student.this,stud_livelec.class);
                //startActivity(i);
                //finish();
            }
        });
        doubt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i= new Intent(home_student.this,stud_livelec.class);
                //startActivity(i);
                //finish();
            }
        });
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i= new Intent(home_student.this,student_test.class);
                startActivity(i);
                finish();

            }
        });
        livelec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           Intent i= new Intent(home_student.this,stud_livelecture.class);
           startActivity(i);
           finish();
            }
        });
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_student, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.student_home) {
            Intent i= new Intent(home_student.this,home_student.class);
            startActivity(i);
            finish();
        } else if (id == R.id.student_doubt) {
            Intent i= new Intent(home_student.this,student_doubt.class);
            startActivity(i);
            finish();
        } else if (id == R.id.student_testscore) {
            Intent i= new Intent(home_student.this,student_testscore.class);
            startActivity(i);
            finish();
        } else if (id == R.id.logout) {
            Intent i=new Intent(home_student.this,logout.class);
            startActivity(i);
            finish();
        } else if (id == R.id.changepassword) {
               Intent i=new Intent(home_student.this,changepassword.class);
               startActivity(i);
               finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
