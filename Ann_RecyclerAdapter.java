package com.example.bakeryapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Ann_RecyclerAdapter extends RecyclerView.Adapter<Ann_RecyclerAdapter.Holder> {
    ArrayList<Model> list;


    public Ann_RecyclerAdapter(ArrayList<Model> list) {
        this.list = list;

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.ann_single_row,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.rupees.setText(list.get(position).getRupees());
        holder.image.setImageResource(list.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public class Holder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name,rupees;

        public Holder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.anni_image);
            name=itemView.findViewById(R.id.ann_name);
            rupees=itemView.findViewById(R.id.ann_rupees);
        }
    }
}
