package com.example.transportation.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transportation.Interface.ItemClickLister;
import com.example.transportation.R;

public class Routeviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ItemClickLister listner;
    public  TextView txtFrom,txtTo;
    public  View myV;

    public Routeviewholder(@NonNull View itemView) {
        super(itemView);
        myV=itemView;
        txtFrom = (TextView) itemView.findViewById(R.id.txt_f);
        txtTo = (TextView) itemView.findViewById(R.id.txt_f2);




    }

    public void setItemClickListner(ItemClickLister listner) {
        this.listner = listner;
    }


    public void onClick(View v) {
        listner.onClick(v, getAdapterPosition(), false);

    }
}

