package com.example.transportation;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.transportation.Models.RouteModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class EnterRoutesActivity2 extends AppCompatActivity {
    EditText edFrom,edTo,edRoute1,edRoute2,edRoute3;
    DatabaseReference mDatabase;
    private ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_routes2);
        loadingBar = new ProgressDialog(this);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("routes");
        edFrom=findViewById(R.id.edStart);
        edTo=findViewById(R.id.edEnd);
        edRoute1=findViewById(R.id.edRoute1);
        edRoute2=findViewById(R.id.edRoute2);
        edRoute3=findViewById(R.id.edRoute3);
    }
    void  addPost(){
        loadingBar.setMessage("من فضلك انتظر");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        RouteModel routeModel= new RouteModel();
        routeModel.setFrom(edFrom.getText().toString());
        routeModel.setTo(edTo.getText().toString());
        List<String> routes=new ArrayList<>();
        routes.add(edRoute1.getText().toString());
        if(!edRoute2.getText().toString().isEmpty())
        {
            routes.add(edRoute2.getText().toString());
        }
        if(!edRoute3.getText().toString().isEmpty())
        {
            routes.add(edRoute3.getText().toString());
        }
        routeModel.setRoutes(routes);
        mDatabase.push().setValue(routeModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //   but.setVisibility(View.GONE);
                loadingBar.dismiss();
                edRoute1.setText("");
                edRoute2.setText("");
                edRoute3.setText("");
                Toast.makeText(EnterRoutesActivity2.this, "تم الاضافة  بنجاح ", Toast.LENGTH_SHORT).show();

                //  algeha.setText("");
                // img.setImageBitmap(null);

            }
        });
    }

    public void add(View view) {
addPost();
    }
}