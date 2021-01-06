package com.example.proiectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainMenuActivity extends AppCompatActivity {

    Button btnProfile, btnAddItem, btnShowCollection, btnMuseums, btnLogOut;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,(WindowManager.LayoutParams.FLAG_FULLSCREEN));
        setContentView(R.layout.activity_main_menu);

        btnProfile = findViewById(R.id.btnProfile);
        btnAddItem = findViewById(R.id.btnAddItem);
        btnShowCollection = findViewById(R.id.btnShowCollection);
        btnMuseums = findViewById(R.id.btnMuseums);
        btnLogOut = findViewById(R.id.btnLogOut);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, UserProfileActivity.class));
            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, AddItemActivity.class));
            }
        });

        btnShowCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, CollectionActivity.class));
            }
        });

        btnMuseums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, MuseumsActivity.class));
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(MainMenuActivity.this, MainActivity.class));
            }
        });


    }
}