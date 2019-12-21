package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class signInActivity extends AppCompatActivity {
    EditText emailEt , passwordEt ;
    Button signInBtn;
    TextView signUpTV , exitTV ,forgetPasswordTV;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user!=null)
        {
            Intent intent = new Intent(signInActivity.this , homeActivity.class);
            startActivity(intent);
        }

        emailEt = findViewById(R.id.emailEt);
        passwordEt = findViewById(R.id.passwordEt);
        signInBtn = findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(v->{
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
            doSignIn(emailEt.getText().toString() , passwordEt.getText().toString());
        });
    }


    private void doSignIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();


                        Map<String,Object> data = new HashMap<>();
                        data.put("lastSignIn",new Date().getTime());


                        FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid()).updateChildren(data)
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(signInActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        Log.d("error",e.getLocalizedMessage());
                                    }
                                })
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Intent intent = new Intent(signInActivity.this , homeActivity.class);
                                        startActivity(intent);
                                    }
                                });






                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(signInActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }

                });
    }

    public void onClickSU(View v) {
        signUpTV = findViewById(R.id.signUpTV);
        signUpTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signInActivity.this , signUpActivity.class);
                startActivity(intent);

            }
        });
    }

    public void onClickExite(View v) {
        exitTV = findViewById(R.id.exitTV);
        exitTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signInActivity.this , MainActivity.class);
                startActivity(intent);

            }
        });
    }

    public void onClickForget(View v) {
        forgetPasswordTV = findViewById(R.id.forgetPasswordTV);
        forgetPasswordTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signInActivity.this , forgetPasswordActivity.class);
                startActivity(intent);

            }
        });
    }


}
