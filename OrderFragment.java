package com.example.bakeryapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bakeryapp.adapter.OrdersAdapter;
import com.example.bakeryapp.api.RequestApi;
import com.example.bakeryapp.api.RetrofitClass;
import com.example.bakeryapp.data.OrderResponseFetching;
import com.example.bakeryapp.data.OrderResponseFetching.OrderDetails;
import com.example.bakeryapp.data.OrderResponseStoring;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link OrderFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class OrderFragment extends Fragment {
RecyclerView recyclerView;
List<OrderResponseFetching.OrderDetails> list=new ArrayList<>();
OrdersAdapter ordersAdapter;
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public OrderFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment OrderFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static OrderFragment newInstance(String param1, String param2) {
//        OrderFragment fragment = new OrderFragment();
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
//        return inflater.inflate(R.layout.fragment_order, container, false);
//    }
//}


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View view=inflater.inflate(R.layout.fragment_order,container,false);
                recyclerView=view.findViewById(R.id.orderrecyclerview);
                OrderFetchingApi();
                show();

                return view;
    }

    private void show() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ordersAdapter=new OrdersAdapter(list,getContext());
        recyclerView.setAdapter(ordersAdapter);

    }

    private void OrderFetchingApi() {
        RequestApi requestApi= RetrofitClass.getClient().create(RequestApi.class);
        Call<OrderResponseFetching> call=requestApi.orderFetching();
        call.enqueue(new Callback<OrderResponseFetching>() {
            @Override
            public void onResponse(Call<OrderResponseFetching> call, Response<OrderResponseFetching> response) {
                //progressBar.setVisibility(View.GONE);

                if(response.isSuccessful()) {
                   // Toast.makeText(getContext(),"response ",Toast.LENGTH_LONG).show();

//                    // try{
                    list.clear();
                    list.addAll(response.body().orderDetailsList);
                   show();
//                    list.clear();
                }
                    //}
//                    catch (Exception e){
//                        Toast.makeText(getContext(),e.toString(),Toast.LENGTH_LONG).show();
//
//                    }

//                            if (response.body().error.equalsIgnoreCase("0")) {
                    //Toast.makeText(getContext(),response.body().message,Toast.LENGTH_LONG).show();
//
//                            }

                else {
                    //list.clear();
                    Toast.makeText(getContext(),"error",Toast.LENGTH_LONG).show();

                }
//                               // Snackbar.make(relativeLayout, response.body().message, Snackbar.LENGTH_LONG).show();
//                            }
                }

            @Override
            public void onFailure(Call<OrderResponseFetching> call, Throwable t) {
                //progressBar.setVisibility(View.GONE);

                //Snackbar.make(relativeLayout,t.getMessage(),Snackbar.LENGTH_LONG).show();

                 Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();


            }
        });


    }
}