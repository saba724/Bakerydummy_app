package com.example.bakeryapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bakeryapp.api.RequestApi;
import com.example.bakeryapp.api.RetrofitClass;
import com.example.bakeryapp.data.AddToCartResponseFetching;
import com.example.bakeryapp.data.LoginRespone;
import com.example.bakeryapp.data.ProductRemoveResponse;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Holder> {
    ArrayList<AddToCartResponseFetching.CartDEtalis>list;
    Context context;
   // View view;
   RecyclerView recyclerView;
   ProgressDialog dialogue;

    public CartAdapter(ArrayList<AddToCartResponseFetching.CartDEtalis> list, Context context, RecyclerView recyclerView) {
        this.list = list;
        this.context = context;
        this.recyclerView = recyclerView;
    }
//    ProductRemoveResponse productRemoveResponse;
//    String completeJSONdata = "";



    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.addtocart_singlerow,parent,false);
       // view.getRootView();

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.productname.setText(list.get(position).p_name1);
        holder.prize.setText(list.get(position).p_prize1);
       // holder.grandprize.setText(list.get(position).t_prise1);

        Glide.with(context).load(list.get(position).url1).into(holder.imageView);
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, list.get(position).p_id1, Toast.LENGTH_LONG).show();
               // Toast.makeText(context,"heelo",Toast.LENGTH_LONG).show();
                //removing items from recycler view
//                list.remove(holder.getAdapterPosition());
//                 notifyItemRemoved(holder.getAdapterPosition());
//                notifyItemChanged(holder.getAdapterPosition(), list.size());
//            removing the items from api

                dialogue = new ProgressDialog(context);
                dialogue.setMessage("Removing from cart...!");
                dialogue.setContentView(R.layout.progress_bar);
                dialogue.setCancelable(false);
                dialogue.show();
                Snackbar.make(recyclerView,"Product Removed Successfully!",Snackbar.LENGTH_LONG).show();


                RequestApi requestApi= RetrofitClass.getClient().create(RequestApi.class);
                Call<ProductRemoveResponse> call=requestApi.productRemove(list.get(position).card_id1,"2",
                        SharedPreference.getInstance(context).getString(SharedPreference.USER_ID));
                call.enqueue(new Callback<ProductRemoveResponse>() {
                    @Override
                    public void onResponse(Call<ProductRemoveResponse> call, Response<ProductRemoveResponse> response) {
                        //progressBar.setVisibility(View.GONE);
                        dialogue.dismiss();
                        if(response.isSuccessful()) {
                            if (response.body().error.equalsIgnoreCase("0")) {

//                                Gson gson = new Gson();
//                                productRemoveResponse = gson.fromJson(completeJSONdata, ProductRemoveResponse.class);
//                                Toast.makeText(context,"response.body().message",Toast.LENGTH_LONG).show();
//
////                                Snackbar snackbar = Snackbar.make(view, response.body().message, Snackbar.LENGTH_SHORT);
//
//                                list.remove(holder.getAdapterPosition());
//                                notifyItemRemoved(holder.getAdapterPosition());
//                                notifyItemChanged(holder.getAdapterPosition(), list.size());
//                                notifyDataSetChanged();
//                                list.clear();
//
//                                View sbView = snackbar.getView();
                               try {

                                }
                                catch (Exception e){
                                    Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                                }



                              // Snackbar.make(view, response.body().message, Snackbar.LENGTH_LONG).show();
//                                Intent intent=new Intent(LoginPage.this,HomePage.class);
//                                SharedPreference.getInstance(getApplicationContext()).saveString(SharedPreference.USER_ID, email.getText().toString());
//                                startActivity(intent);
//                                finishAffinity();
                            } else {
                               Toast.makeText(context,response.body().message,Toast.LENGTH_LONG).show();
                                //Snackbar.make(view, response.body().message, Snackbar.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ProductRemoveResponse> call, Throwable t) {
                       // progressBar.setVisibility(View.GONE);

//                        Snackbar.make(view,t.getMessage(),Snackbar.LENGTH_LONG).show();

//                       Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
//                                Snackbar snackbar = Snackbar.make(view, response.body().message, Snackbar.LENGTH_SHORT);
                        try {
                           // Toast.makeText(context,"Removed",Toast.LENGTH_LONG).show();
                            list.remove(holder.getAdapterPosition());
                            notifyItemRemoved(holder.getAdapterPosition());
                            notifyItemChanged(holder.getAdapterPosition(), list.size());
                            notifyDataSetChanged();
                            //list.get(position).t_prize1.
                            Intent i = new Intent(context, HomePage.class);
                            i.putExtra("cart","1");
                            context.startActivity(i);
                            ((Activity)context).finish();

                            
                        }
                        catch (Exception e){

                        }



//                        list.clear();



                    }
                });
            }
        });
        holder.onekg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.prize.setText("150");
            }
        });
        holder.twokg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.prize.setText("300");
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        ImageView imageView;
        Button remove,onekg,twokg;
        TextView productname,prize,tprice;


        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.cart_image);
            remove=itemView.findViewById(R.id.cart_removebutton);
            productname=itemView.findViewById(R.id.cart_name);
            prize=itemView.findViewById(R.id.cart_rupees);
            onekg=itemView.findViewById(R.id.kgbutton1);
            twokg=itemView.findViewById(R.id.kgbutton2);
            //tprice=itemView.findV
           // grandprize=itemView.findViewById(R.id.grandtotal);
            //relativeLayout=itemView.findViewById(R.id.relativeaddtocart);
        }
    }
}
