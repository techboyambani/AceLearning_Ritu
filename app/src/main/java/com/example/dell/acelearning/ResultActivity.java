package com.example.dell.acelearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView t1,t2,t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        t1=(TextView)findViewById(R.id.textView4);
        t2=(TextView)findViewById(R.id.textView5);
        t3=(TextView)findViewById(R.id.textView6);
        Intent i=getIntent();
        String question=i.getStringExtra("total");
        String right=i.getStringExtra("correct");
        String wr=i.getStringExtra("wrong");
        t1.setText(question);
        t2.setText(right);
        t3.setText(wr);
    }
}