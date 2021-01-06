package com.example.proiectandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEmail, editTextPassword;
    ProgressBar signUpProgressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,(WindowManager.LayoutParams.FLAG_FULLSCREEN));
        setContentView(R.layout.activity_sign_up);

        editTextEmail = findViewById(R.id.editTextSignUpEmailAddress);
        editTextPassword = findViewById(R.id.editTextSignUpPassword);

        signUpProgressBar = findViewById(R.id.signUpProgressBar);

        findViewById(R.id.btnRegister).setOnClickListener(this);

        Button btnRegister2 = findViewById(R.id.btnRegister);
        btnRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();

            }
        });
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }

    private void registerUser(){
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if(email.isEmpty()){
            editTextEmail.setError("Email required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPassword.setError("Minimum password length is 6");
            editTextPassword.requestFocus();
            return;
        }

        signUpProgressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                signUpProgressBar.setVisibility(View.VISIBLE);
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"User Registration Succesful", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(SignUpActivity.this, MainMenuActivity.class));
                }else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(SignUpActivity.this, "User already registered!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(SignUpActivity.this, "User Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /*case R.id.btnSignUp:
                registerUser();
                break;*/
        }
    }
}