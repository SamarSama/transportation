package com.example.transportation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button login, signup;
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        login.setOnClickListener(this);
        signup.setOnClickListener( this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Login.class);
              //  intent.putExtra("src","Motbraa");

                startActivity(intent);

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Registration.class);
                //  intent.putExtra("src","Motbraa");

                startActivity(intent);

            }
        });



    }

    @Override
    public void onClick(View view) {

    }
}