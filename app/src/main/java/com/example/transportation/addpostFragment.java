package com.example.transportation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.transportation.Models.Users;
import com.example.transportation.Models.post;
import com.example.transportation.Prevalent.Prevalent;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class addpostFragment extends Fragment implements View.OnClickListener {
    private Button save_btn;
    private EditText postdet;
    private ProgressDialog loadingbar;
    post posts;
    String myFormat;
    SimpleDateFormat sd;
    String userphone,name;
    View v;
    DatabaseReference mDatabase,Root;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for
         v = inflater.inflate(R.layout.fragment_addpost, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        save_btn = v.findViewById(R.id.save_btn);
        postdet = v.findViewById(R.id.postdetials);
        loadingbar = new ProgressDialog(getContext());
        posts = new post();
        save_btn.setOnClickListener(this);
        myFormat = "dd/MM/yyyy";
        userphone = getArguments().getString("phone");

//        userphone= Prevalent.currentOnlineUser.getPhone();
       // name= Prevalent.currentOnlineUser.getName();
        sd = new SimpleDateFormat(myFormat, Locale.US);
        Root = FirebaseDatabase.getInstance().getReference().child("post");
        return  v;



    }

    @Override
    public void onClick(View view) {
        if (TextUtils.isEmpty(postdet.getText().toString())) {
            Toast.makeText(getContext(), "من فضلك ادخل تفاصيل المنشور ", Toast.LENGTH_SHORT).show();
        }  else {
            {
                loadingbar.setTitle("أضافة المنشور");
                loadingbar.setMessage("من فضلك انتظر حتى يتم حفظ البيانات");
                loadingbar.setCanceledOnTouchOutside(false);
                loadingbar.show();
                DatabaseReference postsRef = FirebaseDatabase.getInstance().getReference().child("Users");
                postsRef.child(userphone).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            Users u = snapshot.getValue(Users.class);
                            posts.setName(u.getName());
                            posts.setPhone( getArguments().getString("phone"));
                            posts.setPost(postdet.getText().toString());
                            posts.setDate(sd.format(new Date()));
                            Calendar calendar = Calendar.getInstance();
                            SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                            String saveCurrentDate = currentDate.format(calendar.getTime());

                            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                            String saveCurrentTime = currentTime.format(calendar.getTime());

                            String productRandomKey = saveCurrentDate + saveCurrentTime;

                            DatabaseReference postsRef = FirebaseDatabase.getInstance().getReference().child("post");

                            Root.child(productRandomKey).setValue(posts).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    //   but.setVisibility(View.GONE);

                                    Toast.makeText(getContext(), "تم اضافة المنشور بنجاح ", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(), com.example.transportation.afterlogin.class);
                                    startActivity(intent);
                                    postdet.setText("");
                                    //  algeha.setText("");
                                    // img.setImageBitmap(null);

                                }
                            });
                        }






                        }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                    }


                };

            }}




