package com.example.transportation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.transportation.Interface.ItemClickLister;
import com.example.transportation.Models.RouteModel;
import com.example.transportation.Models.post;
import com.example.transportation.adapter.Routeviewholder;
import com.example.transportation.adapter.postviewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RoutesActivity2 extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference mDatabase;
    EditText edSearch1,edSearch2;
    List<RouteModel> routeModelList=new ArrayList<RouteModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes2);
        recyclerView = findViewById(R.id.rec_routes);
        edSearch1=findViewById(R.id.edSearch1);

        edSearch2=findViewById(R.id.edSearch2);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("routes");


        RecyclerView.LayoutManager   layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        FirebaseRecyclerOptions<RouteModel> options =
                new FirebaseRecyclerOptions.Builder<RouteModel>()
                        .setQuery(mDatabase, RouteModel.class)
                        .build();
        FirebaseRecyclerAdapter<RouteModel, Routeviewholder> adapter =
                new FirebaseRecyclerAdapter<RouteModel, Routeviewholder>(options) {

                    @NonNull
                    @Override
                    public Routeviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.routeitems, parent, false);
                        Routeviewholder holder = new Routeviewholder(view);
                        return holder;                    }

                    @Override
                    protected void onBindViewHolder(@NonNull Routeviewholder holder, int i, @NonNull RouteModel model) {
                        Log.d("zzzzzzzzzzzzz", model.getFrom());
                        routeModelList.add(model);

                            holder.txtFrom.setText(model.getFrom());
                            holder.txtTo.setText(model.getTo());


                        holder.myV.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                StringBuilder routes=new StringBuilder();
                                for(String item : model.getRoutes())
                                {

                                    routes.append(item);
                                    routes.append("\n-------------------------------------------------------------------\n\n");

                                }
                                Log.d("ccccccccccccccccccc", model.getRoutes().toString());
                                Intent intent = new Intent(RoutesActivity2.this, RouteDetailsActivity2.class);
                                intent.putExtra("userModel",routes.toString());
                                startActivity(intent);
                            }
                        });




                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    public void filter(View view) {

        for(RouteModel r:routeModelList)
        {
            Log.d("cccccccccccccccccccccccccccc", (edSearch1.getText().toString().trim().equals("بنها"))+"");
            Log.d("cccccccccccccccccccccccccccc", (
                    edSearch2.getText().toString().trim()+""));
            Log.d("cccccccccccccccccccccccccccc", (edSearch1.getText().toString().trim()==r.getFrom()&&
                    edSearch2.getText().toString().trim()==r.getTo())+"");
            if(edSearch1.getText().toString().trim().equals(r.getFrom())&&
            edSearch2.getText().toString().trim().equals(r.getTo()))
            {
                StringBuilder routes=new StringBuilder();
                for(String item : r.getRoutes())
                {

                    routes.append(item);
                    routes.append("\n-------------------------------------------------------------------\n\n");

                }

                Intent intent = new Intent(RoutesActivity2.this, RouteDetailsActivity2.class);
                intent.putExtra("userModel",routes.toString());
                startActivity(intent);
                return;
            }
        }
        Toast.makeText(this, "لا تتوفر طرق سير لهذين المنطقتين", Toast.LENGTH_SHORT).show();


    }
}
//package com.example.transportation.adapter;
//
//        import android.content.Context;
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//        import android.widget.TextView;
//
//        import androidx.annotation.NonNull;
//        import androidx.recyclerview.widget.RecyclerView;
//
//        import com.example.transportation.Interface.ItemClickLister;
//        import com.example.transportation.Models.RouteModel;
//        import com.example.transportation.R;
//
//        import java.util.List;
//
//public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.Routeviewholder> {
//
//    private List<RouteModel> mData;
//    private LayoutInflater mInflater;
//
//
//    // data is passed into the constructor
//    MyRecyclerViewAdapter(Context context, List<RouteModel> data) {
//        this.mInflater = LayoutInflater.from(context);
//        this.mData = data;
//    }
//
//    // inflates the row layout from xml when needed
//    @Override
//    public Routeviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = mInflater.inflate(R.layout.routeitems, parent, false);
//        return new Routeviewholder(view);
//    }
//
//    // binds the data to the TextView in each row
//    @Override
//    public void onBindViewHolder(Routeviewholder holder, int position) {
//        RouteModel model = mData.get(position);
//        holder.txtFrom.setText(model.getFrom());
//        holder.txtTo.setText(model.getTo());
//    }
//
//    // total number of rows
//    @Override
//    public int getItemCount() {
//        return mData.size();
//    }
//
//
//    public  static  class Routeviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        public ItemClickLister listner;
//        public TextView txtFrom,txtTo;
//        public  View myV;
//
//
//        public Routeviewholder(@NonNull View itemView) {
//            super(itemView);
//            myV=itemView;
//            txtFrom = (TextView) itemView.findViewById(R.id.txt_f);
//            txtTo = (TextView) itemView.findViewById(R.id.txt_f2);
//
//
//
//
//        }
//
//        public void setItemClickListner(ItemClickLister listner) {
//            this.listner = listner;
//        }
//
//
//        public void onClick(View v) {
//            listner.onClick(v, getAdapterPosition(), false);
//
//        }}}
//

