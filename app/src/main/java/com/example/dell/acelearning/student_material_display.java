package com.example.dell.acelearning;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


public class student_material_display extends AppCompatActivity {
    private Toolbar mToolbar;
    private ViewPager myviewPager;
    private TabLayout mytabLayout;
    String subject;
    private TabsAccessorAdapter mytabsAccessorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_material_display);
        Intent i=getIntent();
        subject=i.getStringExtra("subject");
        mToolbar=findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Ace Learning");
        myviewPager= findViewById(R.id.main_tabs_pager);
        mytabsAccessorAdapter=new TabsAccessorAdapter(getSupportFragmentManager());
        myviewPager.setAdapter(mytabsAccessorAdapter);
        mytabLayout=findViewById(R.id.main_tabs);
        mytabLayout.setupWithViewPager(myviewPager);
        mytabsAccessorAdapter.setSubject(subject);
    }
}
