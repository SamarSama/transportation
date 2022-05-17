package com.example.transportation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable(){
            public void run() {
                Intent mainIntent = new Intent(MainActivity.this, com.example.transportation.HomeActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 2000);
    }
}