package com.example.proiectandroid;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {

    protected boolean active = true;
    protected int splashTimeMillis = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,(WindowManager.LayoutParams.FLAG_FULLSCREEN));
        setContentView(R.layout.activity_splash_screen);

        Thread splashThread = new Thread(){
            @Override
            public void run(){
                try{
                    int waited = 0;
                    while(active && (waited < splashTimeMillis)){
                        sleep(100);
                        if(active){
                            waited += 100;
                        }
                    }
                }catch (Exception e){

                }finally{
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                    finish();
                }
            }
        };
        splashThread.start();
    }
}