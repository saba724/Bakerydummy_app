package com.example.bakeryapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link HomeFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class HomeFragment extends Fragment {

    ViewFlipper viewFlipper;
    CardView aniversary,creamcake,cookies,breads,pizza,imagecake;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public HomeFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment HomeFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static HomeFragment newInstance(String param1, String param2) {
//        HomeFragment fragment = new HomeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View v = inflater.inflate(R.layout.fragment_home, container, false);
//        return  v;
//
//        viewFlipper = v.findViewById(R.id.viewFlipper);
//        //auto slider
//        int images[]={R.drawable.cake1,R.drawable.cake4,R.drawable.cake5,R.drawable.cake3,R.drawable.chocolate,R.drawable.fruitecake};
//        for(int image:images)
//        {
//            //showImages(image);
//
//        }
//
//    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        creamcake=v.findViewById(R.id.cream_cake);
        creamcake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),CreameCake.class);
                intent.putExtra("cat_id", "1");

                startActivity(intent);
            }
        });
        cookies=v.findViewById(R.id.cookies_card);
        cookies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),CreameCake.class);
                intent.putExtra("cat_id","3");
                startActivity(intent);

            }
        });
        breads=v.findViewById(R.id.breads);
        breads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),CreameCake.class);
                intent.putExtra("cat_id","2");
                startActivity(intent);
            }
        });
        pizza=v.findViewById(R.id.pizza_cv);
        pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),CreameCake.class);
                intent.putExtra("cat_id","5");
                startActivity(intent);

            }
        });
        imagecake=v.findViewById(R.id.imagecake);
        imagecake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),CreameCake.class);
                intent.putExtra("cat_id","4");
                startActivity(intent);
            }
        });

        viewFlipper = v.findViewById(R.id.viewFlipper);
        aniversary=v.findViewById(R.id.aniversery_card);
        aniversary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),AniversaryCatoegry.class);
                intent.putExtra("name","Anniversary cakse from-intent");
                startActivity(intent);
            }
        });
        //auto slider
        int images[]={R.drawable.cake1,R.drawable.cake4,R.drawable.cake5,R.drawable.cake3,R.drawable.chocolate,R.drawable.fruitecake};
        for(int image:images)
        {
            showImages(image);

        }
        return  v;
    }

    public void showImages(int images){//auto slider
        ImageView imageView=new ImageView(getContext());
        imageView.setBackgroundResource(images);
        viewFlipper.addView(imageView);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(2000);
        //animation
        viewFlipper.setInAnimation(getContext(), android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getContext(), android.R.anim.slide_out_right);
    }
}