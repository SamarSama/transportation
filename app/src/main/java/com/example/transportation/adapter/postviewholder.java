package com.example.transportation.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transportation.Interface.ItemClickLister;
import com.example.transportation.R;

public class postviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ItemClickLister listner;
    public  TextView username,postdet,date,phone;

    public postviewholder(@NonNull View itemView) {
        super(itemView);
        username = (TextView) itemView.findViewById(R.id.txtFromtest);
        postdet = (TextView) itemView.findViewById(R.id.post);
        date = (TextView) itemView.findViewById(R.id.date);
        phone = (TextView) itemView.findViewById(R.id.phone);



    }
    public void setItemClickListner(ItemClickLister listner) {
        this.listner = listner;
    }


    public void onClick(View v) {
        listner.onClick(v, getAdapterPosition(), false);

    }
}

