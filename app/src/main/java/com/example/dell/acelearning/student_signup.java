package com.example.dell.acelearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ProgressBar;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

public class student_signup extends AppCompatActivity {


    String usertype;
    private String userID;
    private EditText inputEmail, inputPassword, uname, phn;
    private Button btnSignIn, btnSignUp;
    private FirebaseAuth auth;
    private FirebaseDatabase mFirebaseDatabase;
    DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);
        auth = FirebaseAuth.getInstance();
        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        phn=findViewById(R.id.phn_no);
        uname=findViewById(R.id.uname);
        inputPassword = (EditText) findViewById(R.id.password);



        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(student_signup.this,login.class);
                startActivity(i);
                finish();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phn1 = phn.getText().toString().trim();
                final String email = inputEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();
                final String name= uname.getText().toString().trim() ;
                String MobilePattern = "[0-9]{10}";
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phn1)) {
                    Toast.makeText(getApplicationContext(), "Enter mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!(phn1.matches(MobilePattern)))
                {   Toast.makeText(getApplicationContext(), "Please enter 10 digit mobile number!", Toast.LENGTH_SHORT).show();
                    return;
                }


                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(student_signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(student_signup.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                if (!task.isSuccessful()) {
                                    Toast.makeText(student_signup.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {

                                    mFirebaseDatabase = FirebaseDatabase.getInstance();
                                    myRef = mFirebaseDatabase.getReference();
                                    FirebaseUser user = auth.getCurrentUser();
                                    userID = user.getUid();
                                    UserInformation userInformation = new UserInformation(name ,email, "student",phn1);
                                    FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
                                    DatabaseReference myRef = mFirebaseDatabase.getReference();
                                    myRef.child("users").child(userID).setValue(userInformation);
                                    Intent i= new Intent(student_signup.this,login.class);
                                    startActivity(i);
                                    finish();
                                }
                            }
                        });

            }
        });
    }


}

