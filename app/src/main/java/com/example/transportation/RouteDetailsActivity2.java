package com.example.transportation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.transportation.Models.RouteModel;

public class RouteDetailsActivity2 extends AppCompatActivity {

TextView txtV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_details2);
        txtV=findViewById(R.id.txtRR);

        if(getIntent().hasExtra("userModel"))
        {
            txtV.setText(getIntent().getStringExtra("userModel"));
        }else
        {
            txtV.setText("لا تتوفر اى طرق بعد");
        }

    }
}