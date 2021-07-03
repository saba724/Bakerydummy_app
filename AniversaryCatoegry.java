package com.example.bakeryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AniversaryCatoegry extends AppCompatActivity {
    RecyclerView recyclerView;
    com.example.bakeryapp.Ann_RecyclerAdapter recyclerAdapter;
    TextView header;
    ArrayList<Model> list=new ArrayList<Model>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniversary_catoegry);
        header=findViewById(R.id.header);
        recyclerView=findViewById(R.id.ann_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        showImage();
       list=new ArrayList<>();
        recyclerAdapter=new com.example.bakeryapp.Ann_RecyclerAdapter((ArrayList<Model>) showImage());
        recyclerView.setAdapter(recyclerAdapter);
        Intent intent=getIntent();
        header.setText(intent.getStringExtra("name"));

    }
    public List<Model> showImage(){
        List<Model> list=new ArrayList<>();
       list.add(new Model("chocolate cake","200.00",R.drawable.imacake2));
        list.add(new Model("Rose cake cake","200.00",R.drawable.cake1));
        list.add(new Model("Fruite cake","200.00",R.drawable.cake2));
        list.add(new Model("Creame cake","200.00",R.drawable.cake5));
        list.add(new Model(" Dark chocolate cake","200.00",R.drawable.cartoon));
        list.add(new Model("pinaple cake","200.00",R.drawable.fruitecake));
        list.add(new Model("Black Forest cake","200.00",R.drawable.imagecake4jpeg));
        list.add(new Model("Venella cake","200.00",R.drawable.cream));
        list.add(new Model("ButterScotch cake","200.00",R.drawable.fruitecake));
        list.add(new Model("White Forest cake","200.00",R.drawable.cartoon));

        return list;
    }
}
