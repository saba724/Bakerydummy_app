package com.example.bakeryapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.manager.SupportRequestManagerFragment;
import com.example.bakeryapp.api.RequestApi;
import com.example.bakeryapp.api.RetrofitClass;
import com.example.bakeryapp.data.AddToCartResponseStore;
import com.example.bakeryapp.data.LoginRespone;
import com.example.bakeryapp.data.ProductListRep;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreamAdapter extends RecyclerView.Adapter<CreamAdapter.Holder> {
    ArrayList<ProductListRep.ProductList> list;
    Context context;
   // FragmentManager fragmentManager;
    RecyclerView recyclerView;

    public CreamAdapter(ArrayList<ProductListRep.ProductList> list, Context context, RecyclerView recyclerView) {
        this.list = list;
        this.context = context;
       // this.fragmentManager = fragmentManager;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.creame_single_row, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
     //  holder.image.setImageResource(Integer.parseInt(list.get(position).image_url));

//        try {
//            URL newurl = new URL(list.get(position).image_url);
//            Bitmap bmp = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
//            holder.image.setImageBitmap(bmp);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        holder.name.setText(list.get(position).p_name1);
        holder.rupees.setText(list.get(position).product_prize);
        //for image loading from rest api using glide library
        Glide.with(context)
                .load(list.get(position).image_url)
                .into(holder.image);
        holder.addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  BottomSheet bottomSheet=new BottomSheet();
               // bottomSheet.show(getSupportFragmentManager,"BottomSHeetExample");
                //addTocartStoreApi();




               // Toast.makeText(context,"hello ",Toast.LENGTH_LONG).show();

                RequestApi requestApi= RetrofitClass.getClient().create(RequestApi.class);
                Call<AddToCartResponseStore> call=requestApi.addToCartStore(SharedPreference.getInstance(context).getString(SharedPreference.USER_ID),
                        list.get(position).product_id);

                call.enqueue(new Callback<AddToCartResponseStore>() {
                    @Override
                    public void onResponse(Call<AddToCartResponseStore> call, Response<AddToCartResponseStore> response) {
                        //progressBar.setVisibility(View.GONE);

                        if(response.isSuccessful()) {
                            try {
                                Snackbar.make(recyclerView,"Added To Cart!",Snackbar.LENGTH_LONG).show();


                            }
                            catch (Exception e){

                            }

                            // if (response.body().error.equalsIgnoreCase("0")) {

//                        Snackbar.make(relativeLayout, response.body().message, Snackbar.LENGTH_LONG).show();
//                        Intent intent=new Intent(LoginPage.this,HomePage.class);
//                        SharedPreference.getInstance(getApplicationContext()).saveString(SharedPreference.USER_ID, email.getText().toString());
//                        startActivity(intent);
//                        finishAffinity();
                            } else
                                {
                                Toast.makeText(context,response.body().message,Toast.LENGTH_LONG).show();
                                //Snackbar.make(relativeLayout, response.body().message, Snackbar.LENGTH_LONG).show();
                            }
                        }
                   // }

                    @Override
                    public void onFailure(Call<AddToCartResponseStore> call, Throwable t) {
                        //progressBar.setVisibility(View.GONE);

                        // Snackbar.make(relativeLayout,t.getMessage(),Snackbar.LENGTH_LONG).show();

                        Toast.makeText(context,"Added",Toast.LENGTH_LONG).show();
                        //Toast.makeText(context,"added",Toast.LENGTH_LONG).show();


                    }
                });



            }
        });



    }

   // private void addTocartStoreApi() {


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name,rupees;
        Button addcart;

        public Holder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.cream_image);
            name=itemView.findViewById(R.id.cream_name);
            rupees=itemView.findViewById(R.id.cream_rupees);
            addcart=itemView.findViewById(R.id.addcartbutton);

        }
    }

}
