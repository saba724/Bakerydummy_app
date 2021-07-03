package com.example.bakeryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakeryapp.api.RequestApi;
import com.example.bakeryapp.api.RetrofitClass;
import com.example.bakeryapp.data.LoginRespone;
import com.example.bakeryapp.data.ProductListRep;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreameCake extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    com.example.bakeryapp.CreamAdapter creamAdapter;
    TextView t;
    String cat_id;
    ArrayList<ProductListRep.ProductList> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creame_cake);
        recyclerView=findViewById(R.id.cream_recyclerview);

        t = findViewById(R.id.cream_tv);

        Intent i = getIntent();
        t.setText(i.getStringExtra("cat_id"));
        cat_id = i.getStringExtra("cat_id");

        get_productApi();

        showItems();
    }
    public void showItems() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        com.example.bakeryapp.CreamAdapter creamAdapter = new com.example.bakeryapp.CreamAdapter(list,this,recyclerView);
        recyclerView.setAdapter(creamAdapter);
    }

    private void get_productApi(){
       // Toast.makeText(getApplicationContext(),"list.size()",Toast.LENGTH_LONG).show();
        RequestApi requestApi= RetrofitClass.getClient().create(RequestApi.class);
        Call<ProductListRep> call=requestApi.get_product(cat_id);
        call.enqueue(new Callback<ProductListRep>() {
            @Override
            public void onResponse(Call<ProductListRep> call, Response<ProductListRep> response) {
                if (response.isSuccessful()) {
//                    //if (response.body().error.equalsIgnoreCase("0")) {
                        list.clear();
                        list.addAll(response.body().productlist);
                        showItems();

                        Snackbar.make(linearLayout,"list.size()",Snackbar.LENGTH_LONG).show();

                        //Toast.makeText(getApplicationContext(),"list.size()",Toast.LENGTH_LONG).show();

////                        Log.d("nagu","Hello");
//                    //} else {
//                        list.clear();
//                        showItems();
//                        recyclerView.setVisibility(View.VISIBLE);
////                        Log.d("nagu","Hello_error");
//                    }
                }
                else {
                    list.clear();
                    //showItems();
                    recyclerView.setVisibility(View.VISIBLE);
                    Toast.makeText(CreameCake.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }
//                        list.addAll(response.body().productlist);
//                        CreamAdapter creamAdapter=new CreamAdapter(list);
//                        recyclerView.setAdapter(creamAdapter);
//
//                       // showItems();
//
//                        Toast.makeText(CreameCake.this, response.body().message, Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(CreameCake.this, response.body().message, Toast.LENGTH_LONG).show();
//                    }
//                }
//            }

            @Override
            public void onFailure(Call<ProductListRep> call, Throwable t) {

                //Toast.makeText(CreameCake.this,t.getMessage(),Toast.LENGTH_LONG).show();
                list.clear();
               //showItems();
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                Toast.makeText(CreameCake.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });
    }
}