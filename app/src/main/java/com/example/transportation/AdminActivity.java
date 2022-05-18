package com.example.transportation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);
    }

    public void enterPoint(View view) {
        Intent intent = new Intent(AdminActivity.this, EnterRoutesActivity2.class);
        startActivity(intent);
    }

    public void seePoints(View view) {
        Intent intent = new Intent(AdminActivity.this, RoutesActivity2.class);
        startActivity(intent);
    }
}