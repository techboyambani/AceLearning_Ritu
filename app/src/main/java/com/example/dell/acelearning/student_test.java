package com.example.dell.acelearning;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class student_test extends AppCompatActivity {
    Button b1,b2,b3,b4;
    TextView t1_question,timertxt;
    String answer, option1, option2;
    int total=0;
    int no_of_questions ;
    int correct=0;
    int wrong=0;
    DatabaseReference db, mFirebaseDatabase;
    DatabaseReference reference;
    int num_ques;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_test);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        t1_question=(TextView)findViewById(R.id.questionsTxt);
        timertxt=(TextView)findViewById(R.id.timerTxt);
        reverseTimer(30,timertxt);
        db= FirebaseDatabase.getInstance().getReference().child("Test");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    Log.e(dataSnapshot.getKey(),dataSnapshot.getChildrenCount() + "helooooooo");
                    num_ques = (int) dataSnapshot.getChildrenCount();
                updateQuestion(num_ques);
                    Toast.makeText(student_test.this,String.valueOf(num_ques)+"1",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private void updateQuestion(int num_ques) {
        total++;
        no_of_questions=num_ques;
        Toast.makeText(student_test.this, String.valueOf(no_of_questions)+"inside upd questions", Toast.LENGTH_SHORT).show();
        if(total >no_of_questions) {
            Intent i=new Intent(student_test.this,ResultActivity.class);
            i.putExtra("total",String.valueOf(no_of_questions));
            i.putExtra("correct",String.valueOf(correct));
            i.putExtra("wrong",String.valueOf(wrong));
            startActivity(i);
            // result activity;
        }
        else
        {
            ///////////////////////////////////////







            ///////////////////////////////////////






            reference= FirebaseDatabase.getInstance().getReference().child("Test").child(String.valueOf(total));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    final Question question= dataSnapshot.getValue(Question.class);
                    Map<String, String> questions;
                        questions= (Map) dataSnapshot.getValue();
                        t1_question.setText(questions.get("question"));
                        b1.setText(String.valueOf(questions.get("option1")));
                        b2.setText(String.valueOf(questions.get("option2")));
                        b3.setText(String.valueOf(questions.get("option3")));
                        b4.setText(String.valueOf(questions.get("option4")));
                        answer=String.valueOf(questions.get("answer"));
                        b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(b1.getText().toString().equals(answer))
                            {
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestion(no_of_questions);

                                    }
                                },1500);
                            }
                            else {
                                wrong++;
                                b1.setBackgroundColor(Color.RED);

                                if (b2.getText().toString().equals(answer))
                                {
                                    b2.setBackgroundColor(Color.GREEN);
                                }
                                else if(b3.getText().toString().equals(answer))
                                {
                                    b3.setBackgroundColor(Color.GREEN);
                                }
                                else if(b4.getText().toString().equals(answer))
                                {
                                    b4.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b1.setBackgroundColor(Color.parseColor("#03a9f4"));
                                        b2.setBackgroundColor(Color.parseColor("#03a9f4"));
                                        b3.setBackgroundColor(Color.parseColor("#03a9f4"));
                                        b4.setBackgroundColor(Color.parseColor("#03a9f4"));
                                    }
                                },1500);
                            }
                        }
                    });
                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(b2.getText().toString().equals(answer))
                            {
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestion(no_of_questions);
                                    }
                                },1500);
                            }
                            else {
                                wrong++;
                                b2.setBackgroundColor(Color.RED);
                                if (b1.getText().toString().equals(answer))
                                {
                                    b1.setBackgroundColor(Color.GREEN);
                                }
                                else if(b3.getText().toString().equals(answer))
                                {
                                    b3.setBackgroundColor(Color.GREEN);
                                }
                                else if(b4.getText().toString().equals(answer))
                                {
                                    b4.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b1.setBackgroundColor(Color.parseColor("#03a9f4"));
                                        b2.setBackgroundColor(Color.parseColor("#03a9f4"));
                                        b3.setBackgroundColor(Color.parseColor("#03a9f4"));
                                        b4.setBackgroundColor(Color.parseColor("#03a9f4"));
                                    }
                                },1500);
                            }
                        }
                    });
                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(b3.getText().toString().equals(answer))
                            {
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestion(no_of_questions);
                                    }
                                },1500);
                            }
                            else {
                                wrong++;
                                b3.setBackgroundColor(Color.RED);
                                if (b1.getText().toString().equals(answer))
                                {
                                    b1.setBackgroundColor(Color.GREEN);
                                }
                                else if(b2.getText().toString().equals(answer))
                                {
                                    b2.setBackgroundColor(Color.GREEN);
                                }
                                else if(b4.getText().toString().equals(answer))
                                {
                                    b4.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b1.setBackgroundColor(Color.parseColor("#03a9f4"));
                                        b2.setBackgroundColor(Color.parseColor("#03a9f4"));
                                        b3.setBackgroundColor(Color.parseColor("#03a9f4"));
                                        b4.setBackgroundColor(Color.parseColor("#03a9f4"));
                                    }
                                },1500);
                            }
                        }
                    });
                    b4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(b4.getText().toString().equals(answer))
                            {
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestion(no_of_questions);
                                    }
                                },1500);
                            }
                            else {
                                wrong++;
                                b4.setBackgroundColor(Color.RED);
                                if (b1.getText().toString().equals(answer))
                                {
                                    b1.setBackgroundColor(Color.GREEN);
                                }
                                else if(b3.getText().toString().equals(answer))
                                {
                                    b3.setBackgroundColor(Color.GREEN);
                                }
                                else if(b2.getText().toString().equals(answer))
                                {
                                    b2.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b1.setBackgroundColor(Color.parseColor("#03a9f4"));
                                        b2.setBackgroundColor(Color.parseColor("#03a9f4"));
                                        b3.setBackgroundColor(Color.parseColor("#03a9f4"));
                                        b4.setBackgroundColor(Color.parseColor("#03a9f4"));
                                    }
                                },1500);
                            }
                        }
                    });
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
    }
    public void reverseTimer(int second,final TextView tv)
    {
        new CountDownTimer(second * 1000+1000,1000)
        {
            public  void onTick(long millisUntilFinished){
                int second=(int)(millisUntilFinished/1000);
                int minutes=second/60;
                second=second%60;
                tv.setText(String.format("%02d",minutes)+":"+String.format("%02d",second));
            }
            public void onFinish()
            {
                tv.setText("Complete");
                Intent in=new Intent(student_test.this,ResultActivity.class);
                in.putExtra("Total",String.valueOf(total));
                in.putExtra("Total",String.valueOf(correct));
                in.putExtra("Total",String.valueOf(wrong));
                startActivity(in);
            }
        }.start();
    }
}


