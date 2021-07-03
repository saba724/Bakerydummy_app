package com.example.bakeryapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakeryapp.api.RequestApi;
import com.example.bakeryapp.api.RetrofitClass;
import com.example.bakeryapp.data.AddToCartResponseFetching;
import com.example.bakeryapp.data.AddToCartResponseStore;
import com.example.bakeryapp.data.GrandTotalFetchingResponse;
import com.example.bakeryapp.data.LoginRespone;
import com.example.bakeryapp.data.OrderResponseStoring;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link CartFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class CartFragment extends Fragment {
    RecyclerView recyclerView;
    Button order;
    TextView grandtotal;
    String email = SharedPreference.getInstance(getContext()).getString(SharedPreference.USER_ID);
    ArrayList<AddToCartResponseFetching.CartDEtalis> list=new ArrayList<>();

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public CartFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment CartFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static CartFragment newInstance(String param1, String param2) {
//        CartFragment fragment = new CartFragment();
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
//        return inflater.inflate(R.layout.fragment_cart, container, false);
//    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cart,container,false);
        recyclerView=view.findViewById(R.id.addtocartrecyclerview);
        order=view.findViewById(R.id.orderbutton);
        grandtotal=view.findViewById(R.id.grandtotal);
        order.setOnClickListener(new View.OnClickListener() {         //order api
            @Override
            public void onClick(View v) {
                RequestApi requestApi= RetrofitClass.getClient().create(RequestApi.class);
                Call<OrderResponseStoring> call=requestApi.orderStoring(SharedPreference.getInstance(getContext()).getString(SharedPreference.USER_ID));
                call.enqueue(new Callback<OrderResponseStoring>() {
                    @Override
                    public void onResponse(Call<OrderResponseStoring> call, Response<OrderResponseStoring> response) {
                        //progressBar.setVisibility(View.GONE);

                        if (response.isSuccessful()) {
//                            if (response.body().error.equalsIgnoreCase("0")) {
                           // Toast.makeText(getContext(), response.body().message, Toast.LENGTH_LONG).show();
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                           // builder.setCancelable(true);
                            builder.setTitle("Congratulation!");
                            builder.setMessage(response.body().message);
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                                    list.clear();
                                    Intent i = new Intent(getContext(), HomePage.class);
                                    i.putExtra("cart", "1");
                                    startActivity(i);
                                    getActivity().finish();
                                }
                            });

                            builder.show();
//
//                            }
                        } else {
                            Toast.makeText(getContext(), response.body().message, Toast.LENGTH_LONG).show();
//                               // Snackbar.make(relativeLayout, response.body().message, Snackbar.LENGTH_LONG).show();
//                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<OrderResponseStoring> call, Throwable t) {
                        //progressBar.setVisibility(View.GONE);

                        //Snackbar.make(relativeLayout,t.getMessage(),Snackbar.LENGTH_LONG).show();

                       Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();


                    }
                });

            }
        });
       addToCartFetchingApi();
       grandTotalApi();
        Toast.makeText(getContext(), email, Toast.LENGTH_LONG).show();
        show();

        return view;
    }

    private void grandTotalApi() {
        RequestApi requestApi= RetrofitClass.getClient().create(RequestApi.class);
        Call<GrandTotalFetchingResponse> call=requestApi.grandTotal(SharedPreference.getInstance(getContext()).getString(SharedPreference.USER_ID ));
        call.enqueue(new Callback<GrandTotalFetchingResponse>() {
            @Override
            public void onResponse(Call<GrandTotalFetchingResponse> call, Response<GrandTotalFetchingResponse> response) {



                // progressBar.setVisibility(View.GONE);
               // Toast.makeText(getContext(),response.body().t_prize,Toast.LENGTH_LONG).show();


                if(response.isSuccessful()) {
                    grandtotal.setText(response.body().t_prize);

//                    Intent i=new Intent(getContext(),CartFragment.class);
//                    startActivity(i);



//                    if (response.body().error.equalsIgnoreCase("0")) {
//                        //Toast.makeText(LoginPage.this,response.body().message,Toast.LENGTH_LONG).show();
//                        Snackbar.make(relativeLayout, response.body().message, Snackbar.LENGTH_LONG).show();
//                        Intent intent=new Intent(LoginPage.this,HomePage.class);
//                        SharedPreference.getInstance(getApplicationContext()).saveString(SharedPreference.USER_ID, email.getText().toString());
//                        startActivity(intent);
//                        finishAffinity();
                    }
                else {
                       // Toast.makeText(getContext(),response.body().message,Toast.LENGTH_LONG).show();
                      //  Snackbar.make(relativeLayout, response.body().message, Snackbar.LENGTH_LONG).show();
                    }
                }


            @Override
            public void onFailure(Call<GrandTotalFetchingResponse> call, Throwable t) {
              //  progressBar.setVisibility(View.GONE);

                //Snackbar.make(relativeLayout,t.getMessage(),Snackbar.LENGTH_LONG).show();

//                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();


            }
        });
    }



    private void show() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      com.example.bakeryapp.CartAdapter cartAdapter=new com.example.bakeryapp.CartAdapter(list,getContext(),recyclerView);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setHasFixedSize(true);

    }

    private void addToCartFetchingApi() {
        RequestApi requestApi= RetrofitClass.getClient().create(RequestApi.class);
        Call<AddToCartResponseFetching> call=requestApi.addToCartFetching(email);

        call.enqueue(new Callback<AddToCartResponseFetching>() {
            @Override
            public void onResponse(Call<AddToCartResponseFetching> call, Response<AddToCartResponseFetching> response) {
                //progressBar.setVisibility(View.GONE);

                if(response.isSuccessful()) {
                    try {
                        list.clear();
                        recyclerView.setVisibility(View.VISIBLE);
                        list.addAll(response.body().cartDEtalisList);
                        show();
                    } catch (Exception e){

                    }
//                    if (response.body().error.equalsIgnoreCase("0")) {
//                        Toast.makeText(context,response.body().message,Toast.LENGTH_LONG).show();
//                        Snackbar.make(relativeLayout, response.body().message, Snackbar.LENGTH_LONG).show();
//                        Intent intent=new Intent(LoginPage.this,HomePage.class);
//                        SharedPreference.getInstance(getApplicationContext()).saveString(SharedPreference.USER_ID, email.getText().toString());
//                        startActivity(intent);
//                        finishAffinity();
                } else {
                    list.clear();
                    show();
                   // Toast.makeText(getContext(),response.body().message,Toast.LENGTH_LONG).show();
                    //Snackbar.make(relativeLayout, response.body().message, Snackbar.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<AddToCartResponseFetching> call, Throwable t) {
                list.clear();
                recyclerView.setVisibility(View.GONE);
                //progressBar.setVisibility(View.GONE);

                // Snackbar.make(relativeLayout,t.getMessage(),Snackbar.LENGTH_LONG).show();

                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();


            }
        });

    }


}



