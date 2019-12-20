package com.example.androidproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class signUpActivity extends AppCompatActivity {

    TextView exitTV,signInTV;
    FirebaseAuth mAuth;
    EditText emailEt ,passwordEt;
    Button signUpBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user!=null)
        {
            Intent intent = new Intent(signUpActivity.this , homeActivity.class);
            startActivity(intent);
        }

        emailEt = findViewById(R.id.emailEt);
        passwordEt = findViewById(R.id.passwordEt);
        signUpBtn = findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String email  = emailEt.getText().toString();
                String password = passwordEt.getText().toString();

                if(email.equals("")){
                    emailEt.setError("You Must to Enter Email");
                    return;
                }

                if(password.equals("")){
                    passwordEt.setError("You Must to Enter Password");
                    return;
                }

                doSignUp(emailEt.getText().toString(), passwordEt.getText().toString());
            }
        });

    }

    private void doSignUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();
                        String emailF = user.getEmail();
                        String uid = user.getUid();
                        Map<String,Object> data = new HashMap<>();
                        data.put("uid",uid);
                        data.put("email",emailF);
                        data.put("createdAt",new Date().getTime());

                        FirebaseDatabase.getInstance().getReference().child("User").child(uid).setValue(data)
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(signUpActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        Log.d("error",e.getLocalizedMessage());
                                    }
                                })
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Intent intent = new Intent(signUpActivity.this , homeActivity.class);
                                        startActivity(intent);
                                    }
                                });



                    }
                    else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(signUpActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }

                });
    }



    public void onClickSI(View v) {
        signInTV = findViewById(R.id.signInTV);
        signInTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signUpActivity.this , signInActivity.class);
                startActivity(intent);

            }
        });
    }

    public void onClickExit(View v) {
        exitTV = findViewById(R.id.exitTV);
        exitTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signUpActivity.this , MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
