package com.example.transportation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Registration extends AppCompatActivity {
    private Button CreateAccountButton;
    TextView wagha;
    private EditText InputName, InputPassword, InputPhoneNumber,InputAdess;
    private ProgressDialog loadingbar;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        CreateAccountButton = (Button) findViewById(R.id.signup);
        InputName = (EditText) findViewById(R.id.name);
        InputPassword = (EditText) findViewById(R.id.password);
        InputPhoneNumber = (EditText) findViewById(R.id.phone);
        InputAdess = (EditText) findViewById(R.id.adress);

        loadingbar = new ProgressDialog(this);


        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }


        });
    }
    private void createAccount() {
        String name = InputName.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();
        String adresss=InputAdess.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "please write your name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "please write your phone", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "please write your password", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(adresss)) {
            Toast.makeText(this, "please write your adress", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingbar.setTitle("Your Account");
            loadingbar.setMessage("please wait,while we are checking the data");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();
            ValidatePhoneNumber(name, phone, password,adresss);
        }
    }

    private void ValidatePhoneNumber(String name, String phone, String password, String adresss) {
        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Users").child(phone).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", phone);
                    userdataMap.put("password", password);
                    userdataMap.put("name", name);
                    userdataMap.put("adress", adresss);



                    RootRef.child("Users").child(phone).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Registration.this, "Congratulation your account is established", Toast.LENGTH_SHORT).show();
                                loadingbar.dismiss();
                                Intent intent = new Intent(Registration.this, com.example.transportation.Login.class);

                                startActivity(intent);
                            } else {
                                loadingbar.dismiss();
                                Toast.makeText(Registration.this, "Network Error:Please try again", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                } else {
                    Toast.makeText(Registration.this, "this" + phone + "is already exist", Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();
                    Toast.makeText(Registration.this, "please try with anther number", Toast.LENGTH_SHORT).show();

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }


    public void onNothingSelected(AdapterView<?> parent) {

    }
}