package com.example.transportation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.transportation.Models.Users;
import com.example.transportation.Models.post;
import com.example.transportation.adapter.postviewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PostListFragment extends Fragment {
    RecyclerView recyclerView;
    List<post> postList;
    postviewholder adapter;
    Users user;
    DatabaseReference mDatabase;
    RecyclerView.LayoutManager layoutManager;
View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_post_list, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("post");
        recyclerView = v.findViewById(R.id.rec_post);

        //   recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<post> options =
                new FirebaseRecyclerOptions.Builder<post>()
                        .setQuery(mDatabase, post.class)
                        .build();
        FirebaseRecyclerAdapter<post, postviewholder> adapter =
                new FirebaseRecyclerAdapter<post, postviewholder>(options) {

                    @NonNull
                    @Override
                    public postviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postsitems, parent, false);
                        postviewholder holder = new postviewholder(view);
                        return holder;                    }

                    @Override
                    protected void onBindViewHolder(@NonNull postviewholder holder, int i, @NonNull post model) {
                       holder.username.setText(model.getName());
                        holder.postdet.setText(model.getPost());
                        holder.date.setText(model.getDate());



                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();


        return v;


    }


}
