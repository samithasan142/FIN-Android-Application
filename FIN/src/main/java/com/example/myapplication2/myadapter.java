package com.example.myapplication2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder>
{
    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model)
    {
        holder.name.setText(model.getName());
        holder.location.setText(model.getLocation());
        holder.email.setText(model.getEmail());
        holder.phone.setText(model.getContact());
        String blood = model.getBlood();
        Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);
        //Glide.with(holder.img.getContext()).load("https://thebloodconnection.org/wp-content/uploads/2020/02/Blood-Types-v01_A-Positive-1.png").into(holder.img);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        TextView name,location,phone,email;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img = (CircleImageView)itemView.findViewById(R.id.img1);
            name = (TextView)itemView.findViewById(R.id.nametext);
            location = (TextView)itemView.findViewById(R.id.locationtext);
            email = (TextView)itemView.findViewById(R.id.emailtext);
            phone = (TextView)itemView.findViewById(R.id.phonetext);
        }
    }
}
