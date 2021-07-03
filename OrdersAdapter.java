package com.example.bakeryapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bakeryapp.Ann_RecyclerAdapter;
import com.example.bakeryapp.R;
import com.example.bakeryapp.data.OrderResponseFetching;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.Holder> {
    List<OrderResponseFetching.OrderDetails> list;
    Context context;

    public OrdersAdapter(List<OrderResponseFetching.OrderDetails> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.single_row_orderfetching,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.prize.setText(list.get(position).p_prize1);
        holder.name.setText(list.get(position).p_name1);
        Glide.with(context).load(list.get(position).url).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name ,prize;


        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.order_image);
            name=itemView.findViewById(R.id.order_name);
            prize=itemView.findViewById(R.id.order_prize);
        }
    }
}
