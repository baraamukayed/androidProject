package com.example.androidproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class forgetPasswordActivity extends AppCompatActivity {

    ImageButton returnIB;
    Button recoverPasswordBtn;
    EditText emailEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        returnIB = findViewById(R.id.returnIB);
        returnIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailEt = findViewById(R.id.emailEt);
                FirebaseAuth mAuth = FirebaseAuth.getInstance();



                String email  = emailEt.getText().toString();

                if(email.equals("")){
                    emailEt.setError("You Must to Enter Email");
                    return;
                }

                mAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                             @Override
                                                                             public void onSuccess(Void aVoid) {
                                                                                 Toast.makeText(forgetPasswordActivity.this, "Please check your email", Toast.LENGTH_SHORT).show();
                                                                                 Intent intent = new Intent(forgetPasswordActivity.this , checkMailActivity.class);
                                                                                 startActivity(intent);
                                                                             }
                                                                         }


                );

                Intent intent = new Intent(forgetPasswordActivity.this, signInActivity.class);
                startActivity(intent);

            }
        });

        recoverPasswordBtn = findViewById(R.id.recoverPasswordBtn);
        recoverPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                Intent intent = new Intent(forgetPasswordActivity.this, checkMailActivity.class);
                startActivity(intent);

            }
        });
    }
}
